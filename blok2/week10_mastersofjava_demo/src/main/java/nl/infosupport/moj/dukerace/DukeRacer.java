package nl.infosupport.moj.dukerace;

public class DukeRacer {

  int dinstance = 10;
  int accel[] = {2, 2};
  int spd[] = {2, 4};

  public int[] racers(int distance, int[] accel, int[] spd) {
    // x = vxt + 1/2 at^2

    // x = distance

    int amountOfRacers = accel.length;
    int amountOfRacersCheck = spd.length;
    if (amountOfRacers != amountOfRacersCheck) {
      throw new IllegalArgumentException("should be equal");
    }

    for (int i = 0; i < amountOfRacers; i++) {

    }

    return new int[] {2, 1};
  }

}
