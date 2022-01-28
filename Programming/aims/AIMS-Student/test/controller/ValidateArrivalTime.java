package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateArrivalTime {

  PlaceRushOrderController placeRushOrderController;

  @BeforeEach
  void setUp() throws Exception {
    placeRushOrderController = new PlaceRushOrderController();
  }

  @ParameterizedTest
  @CsvSource({ "2-5-2020,2-5-2021,true", ",2-5-2021,false", "2-5-2020,,false", "2-5-2020,2-6-2020,true",
      "1-5-2020,3-5-2020,true", "2-6-2020,3-5-2020,false" })
  void test(String earliestArrivalTime, String latestArrivalTime, boolean expected) {
    // when
    boolean isValid = placeRushOrderController.validateArrivalTime(earliestArrivalTime, latestArrivalTime);

    // then
    assertEquals(expected, isValid);
  }

}
