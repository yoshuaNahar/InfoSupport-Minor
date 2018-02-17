package nl.yoboid.web.interfaces;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.web.viewentity.MaintenanceViewEntity;

import java.util.List;

public interface MaintenanceService {

  List<MaintenanceViewEntity> getMaintenanceViews();

  void changeStateOfMaintenance(Maintenance maintenance);

  Maintenance getMaintenanceById(long id);

  Maintenance addMaintenance(Maintenance maintenance);
}
