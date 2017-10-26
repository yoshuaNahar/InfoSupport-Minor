package nl.infosupport.listsolver.codeReview;

import java.util.Scanner;
import nl.infosupport.listsolver.codeReview.Rectangle.RectangleComparer;

public class App {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    double length1 = getUserValue("length");
    double width1 = getUserValue("width");

    double length2 = getUserValue("length");
    double width2 = getUserValue("width");

    Rectangle rectangle1 = new Rectangle(length1, width1);
    Rectangle rectangle2 = new Rectangle(length2, width2);
    RectangleComparer comparer = new RectangleComparer(rectangle1, rectangle2);

    comparer.printBiggestRectangle();
  }

  private static double getUserValue(String widthOrLength) {
    System.out.println("Please enter the " + widthOrLength + " of the rectangle");
    double result;
    do {
      result = scanner.nextDouble();
      if (result <= 0) {
        System.out.println("Enter a number greater than zero.");
      }
    }
    while (result <= 0);
    return result;
  }

}

