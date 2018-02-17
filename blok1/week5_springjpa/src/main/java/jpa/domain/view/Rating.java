package jpa.domain.view;

public class Rating {

  private String authorName;
  private double avgRating;

  public Rating(String authorName, double avgRating) {
    this.authorName = authorName;
    this.avgRating = avgRating;
  }

  public String getAuthorName() {
    return authorName;
  }

  public double getAvgRating() {
    return avgRating;
  }

}
