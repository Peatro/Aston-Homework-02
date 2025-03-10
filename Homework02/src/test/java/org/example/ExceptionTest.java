package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionTest {

    @Test
    void exceptionTest() {
        Assertions.assertThrows(CustomException.class, OtherClass::exceptionThrower);
    }
}
