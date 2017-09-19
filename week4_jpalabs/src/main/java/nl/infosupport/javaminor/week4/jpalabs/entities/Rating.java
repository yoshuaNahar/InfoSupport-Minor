package nl.infosupport.javaminor.week4.jpalabs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rating_id", unique = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "author_name", nullable = false)
  private String authorName;

  @Column(name = "average_rating", nullable = false)
  private Double averageRating;

}
