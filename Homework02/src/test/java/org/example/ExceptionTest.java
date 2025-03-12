package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionTest {

    @Test
    void exceptionTest() {
        Exception exception = Assertions.assertThrows(CustomException.class, OtherClass::exceptionThrower, "Incorrect exception");
        Assertions.assertEquals("Custom exception generated!", exception.getMessage(), "Incorrect exception message");
    }
}
