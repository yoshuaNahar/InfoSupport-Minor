package nl.infosupport.moj.dukerace;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DukeRacerTest {

  private DukeRacer dukeRacer = new DukeRacer();

  @Test
  public void test1() {
    int position[] = dukeRacer.racers(10, new int[] {2, 2}, new int[] {2, 4});

    assertThat(position, is(new int[] {2, 1}));
  }

  @Test
  public void test2() {
    int position[] = dukeRacer.racers(10, new int[] {2, 4}, new int[] {2, 2});

    assertThat(position, is(new int[] {2, 1}));
  }

  @Test
  public void test3() {
    int position[] = dukeRacer.racers(10, new int[] {1, 5}, new int[] {2, 1});

    assertThat(position, is(new int[] {2, 1}));
  }

  @Test
  public void test4() {
    int position[] = dukeRacer.racers(10, new int[] {1, 2, 3, 4, 5}, new int[] {2, 2, 2, 2, 2});

    assertThat(position, is(new int[] {5, 4, 3, 2, 1}));
  }

}
