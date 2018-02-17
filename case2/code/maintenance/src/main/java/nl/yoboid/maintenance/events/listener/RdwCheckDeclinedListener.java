package nl.yoboid.maintenance.events.listener;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.maintenance.repositories.MaintenanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RdwCheckDeclinedListener {

  private Logger logger = LoggerFactory.getLogger(RdwCheckDeclinedListener.class);

  private final MaintenanceRepository repo;

  @Autowired
  public RdwCheckDeclinedListener(MaintenanceRepository repo) {
    this.repo = repo;
  }

  @RabbitListener(bindings = @QueueBinding(
    value = @Queue,
    exchange = @Exchange(
      value = "yoboid",
      type = ExchangeTypes.TOPIC,
      durable = "true"),
    key = "rdw.check.declined"))
  public void listen(String id) {
    logger.debug("rdw.check.declined: {}", id);
    Maintenance maintenance = repo.findOne(Long.parseLong(id));
    maintenance.setState(Maintenance.State.RDW_CHECK);
    repo.save(maintenance);
  }

}
