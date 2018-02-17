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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/voertuig")
public class VehicleController {

  private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

  private final VehicleService vehicleService;
  private final MaintenanceService maintenanceService;

  @Autowired
  public VehicleController(VehicleService vehicleService, MaintenanceService maintenanceService) {
    this.vehicleService = vehicleService;
    this.maintenanceService = maintenanceService;
  }

  @GetMapping
  public String getVehicleFormPage(Model model) {
    model.addAttribute("vehicle", new Vehicle());
    return "vehicle_form";
  }

  @PostMapping
  public String addVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes) {
    logger.info("addVehicle: {}", vehicle);
    Vehicle addedVehicle = vehicleService.addVehicle(vehicle);

    redirectAttributes.addFlashAttribute("message", "Voertuig is succesvol toegevoegd.");

    maintenanceService.addMaintenance(Maintenance.builder()
      .vehicleId(addedVehicle.getId())
      .date(LocalDate.now())
      .state(Maintenance.State.NEW)
      .build());

    redirectAttributes.addFlashAttribute("message", "Voertuig is succesvol opgeslagen.");

    return "redirect:/onderhoud";
  }
}
