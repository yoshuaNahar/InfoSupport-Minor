package nl.yoboid.web.controllers;

import nl.yoboid.domain.entities.Maintenance;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.web.interfaces.MaintenanceService;
import nl.yoboid.web.interfaces.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onderhoud")
public class MaintenanceController {

  private static final Logger logger = LoggerFactory.getLogger(MaintenanceController.class);

  private final MaintenanceService maintenanceService;
  private final VehicleService vehicleService;

  @Autowired
  public MaintenanceController(MaintenanceService maintenanceService,
    VehicleService vehicleService) {
    this.maintenanceService = maintenanceService;
    this.vehicleService = vehicleService;
  }

  @GetMapping
  public String getMaintenanceOverviewPage(Model model) {
    model.addAttribute("maintenanceViewEntities", maintenanceService.getMaintenanceViews());

    return "maintenance_overview";
  }

  @GetMapping("/{id}")
  public String getMaintenanceDetailPage(@PathVariable int id, Model model) {
    Maintenance maintenance = maintenanceService.getMaintenanceById(id);
    Vehicle vehicle = vehicleService.getVehicleById(maintenance.getVehicleId());

    model.addAttribute("vehicle", vehicle);
    model.addAttribute("maintenance", maintenance);

    return "maintenance_detail";
  }

  @PostMapping
  public String changeMaintenanceStatus(Maintenance maintenance) {
    logger.info("changeMaintenanceStatus: {}", maintenance);

    maintenanceService.changeStateOfMaintenance(maintenance);

    return "redirect:onderhoud";
  }
}
