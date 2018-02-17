package nl.infosupport.minor.case1_backend.controller;

import java.util.List;
import nl.infosupport.minor.case1_backend.service.CursusService;
import nl.infosupport.minor.case1_domain.Cursus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CursusController {

  @Autowired
  private CursusService cursusService;

  @RequestMapping(value = {"/cursusen"}, method = RequestMethod.GET)
  public List<Cursus> getCursuses() {
    return cursusService.findCursusen();
  }

}
