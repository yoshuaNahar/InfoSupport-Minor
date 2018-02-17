package nl.yoboid.domain.entities;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RdwResponse {

  private String numberPlate;

  private String examinationDate;

  private String sampleExaminationDate;

}
