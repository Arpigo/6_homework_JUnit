package com.demoqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс с тестами")
public class SimpleTest {

    @Disabled("ссылка на тест")
    @DisplayName("Демонстрационный тест")
    @Test
    void firstTest(){
        Assertions.assertTrue(3>2, "2 больше 3");
        Assertions.assertFalse(3>2);
        Assertions.assertEquals("2", "2");
        Assertions.assertAll(
                () -> Assertions.assertTrue(3<2),
                () -> Assertions.assertTrue(3>2)
        );
    }
}
