package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateAddressTest {

    private PlaceOrderController placeOrderController;

    @BeforeEach
    void setUp() {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "hanoi,true",
            "hai ba trung ha noi,true",
            "so 15 Hai Ba Trung Ha Noi,false",
            "$# Hanoi,false",
            ",false"
    })
    public void test(String address, boolean expected) {
        // when
        boolean isValid = placeOrderController.validateAddress(address);

        // then
        assertEquals(expected, isValid);
    }

}