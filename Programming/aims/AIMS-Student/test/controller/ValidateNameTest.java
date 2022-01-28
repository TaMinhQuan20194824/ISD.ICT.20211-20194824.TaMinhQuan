package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateNameTest {

  private PlaceOrderController placeOrderController;

  @BeforeEach
  void setUp() {
    placeOrderController = new PlaceOrderController();
  }

  @ParameterizedTest
  @CsvSource({ "nguyenlm,true", "nguyen trung,true", "nguyen 012345,false", "nguyen012345,false", "$#nguyen,false",
      ",false" })
  public void test(String name, boolean expected) {
    // when
    boolean isValid = placeOrderController.validateName(name);

    // then
    assertEquals(expected, isValid);
  }

}