package nl.yoboid.rdw.services;

import nl.yoboid.domain.entities.RdwRequestObject;
import nl.yoboid.domain.entities.RdwResponse;
import nl.yoboid.domain.entities.Vehicle;
import nl.yoboid.rdw.interfaces.RdwService;
import nl.yoboid.rdw.repositories.RdwRepository;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class RdwServiceImpl implements RdwService {

  @Value("${rdw.rdw-service-ip}")
  private String url;

  @Value("${rdw.sample-key}")
  private String sampleKey;

  @Value("${rdw.rdw-request-file}")
  private String fileLocation;

  private Logger logger = LoggerFactory.getLogger(RdwServiceImpl.class);

  private final RestTemplate template;
  private final RdwRepository repo;

  @Autowired
  public RdwServiceImpl(RestTemplate template, RdwRepository repo) {
    this.template = template;
    this.repo = repo;
  }

  public RdwResponse doRdwRequest(Vehicle vehicle) throws IOException {
    String request;
    try {
      request = prepareXmlRequestFromVehicle(vehicle);
    } catch (IOException exception) {
      logger.error(exception.getMessage());
      throw exception;
    }

    JSONObject jsonResponse = postRdwRequest(request, vehicle.getNumberPlate());
    return parseJsonToRdwResponse(jsonResponse);
  }

  private String prepareXmlRequestFromVehicle(Vehicle vehicle) throws IOException {
    String xml = readXmlFile();

    xml = xml.replace("{numberPlate}", vehicle.getNumberPlate());
    xml = xml.replace("{mileage}", String.valueOf(vehicle.getMileage()));
    xml = xml.replace("{customerId}", String.valueOf(vehicle.getCustomerId()));

    return xml;
  }

  private String readXmlFile() throws IOException {
    Resource resource = new ClassPathResource(this.fileLocation);
    BufferedInputStream stream = new BufferedInputStream(resource.getInputStream());

    return org.apache.commons.io.IOUtils.toString(stream);
  }

  private JSONObject postRdwRequest(String xml, String numberPlate) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
    HttpEntity<String> request = new HttpEntity<>(xml, headers);

    RdwRequestObject obj = new RdwRequestObject(xml, request.getBody(), numberPlate);

    repo.save(obj);

    return XML.toJSONObject(template.postForObject(url, request, String.class));
  }

  private RdwResponse parseJsonToRdwResponse(JSONObject json) {
    JSONObject object = json.getJSONObject("apkKeuringsverzoekResponseMessage").getJSONObject("keuringsregistratie");

    RdwResponse examination = Optional.ofNullable(object)
      .map(input -> RdwResponse.builder()
        .numberPlate(input.getString("kenteken"))
        .examinationDate(input.getString("apk:keuringsdatum"))
        .build())
      .orElseThrow(RuntimeException::new);

    if(object.has(sampleKey) && object.get(sampleKey) instanceof String)
      examination.setSampleExaminationDate(object.getString(sampleKey));

    return examination;
  }
}
