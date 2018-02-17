package nl.yoboid.maintenance.events.listeners;


import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.maintenance.configurations.MaintenanceApplicationConfig;
import nl.yoboid.maintenance.events.listener.RdwCheckApprovedListener;
import nl.yoboid.maintenance.repositories.MaintenanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import(MaintenanceApplicationConfig.class)
@RunWith(SpringRunner.class)
public class RdwCheckApprovedListenerTest {

  @Mock
  private MaintenanceRepository repo;

  @Test
  public void listenToRdwApprovedEventWithValidIdExpectStateChangedToDone(){
    Maintenance testMaintenance = new Maintenance(1, 2, LocalDate.now(), Maintenance.State.AWAITING_RDW);
    when(repo.findOne(any(Long.class)))
      .thenReturn(testMaintenance);

    RdwCheckApprovedListener listener = new RdwCheckApprovedListener(repo);
    listener.listen("1");
    testMaintenance.setState(Maintenance.State.DONE);
    verify(repo, times(1)).save(testMaintenance);
  }

  @Test(expected = NullPointerException.class)
  public void listenToRdwApprovedEventWithValidIdExpectNullPointerException(){
    when(repo.findOne(any(Long.class)))
      .thenReturn(null);

    RdwCheckApprovedListener listener = new RdwCheckApprovedListener(repo);
    listener.listen("1");
  }
}
