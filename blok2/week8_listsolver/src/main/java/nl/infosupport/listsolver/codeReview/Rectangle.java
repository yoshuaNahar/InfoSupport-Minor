package nl.infosupport.listsolver.codeReview;

public class Rectangle {

  private double area;

  public Rectangle(double length, double width) {
    this.area = length * width;
  }

  public double getArea() {
    return area;
  }

  public static class RectangleComparer {

    private Rectangle rectangle1;
    private Rectangle rectangle2;

    public RectangleComparer(Rectangle rectangle1, Rectangle rectangle2) {
      this.rectangle1 = rectangle1;
      this.rectangle2 = rectangle2;
    }

    public void printBiggestRectangle() {
      if (rectangle1.getArea() > rectangle2.getArea()) {
        System.out.println("The area of Rectangle 1 is greater than the area of Rectangle 2");
      } else if (rectangle1.getArea() < rectangle2.getArea()) {
        System.out.println("The area of Rectangle 2 is greater than the area of Rectangle 1");
      } else {
        System.out.println("The areas are equal");
      }
    }

  }

}
