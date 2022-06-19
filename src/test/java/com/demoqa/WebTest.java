package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {


    @ValueSource(strings = {
            "Selenide",
            "JUnit"
    })

    @ParameterizedTest(name = "Проверка поиска Selenide {0}")
    void yaSearchTest(String testData) {
        Selenide.open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$(".serp-item")
                .find(Condition.text(testData))
                .shouldBe(visible);
    }

    @CsvSource(value = {
            "Selenide| is an open source",
            "JUnit| Support JUnit"
    },
    delimiter ='|'
    )
    @ParameterizedTest(name = "Проверка поиска Selenide {0}, ожидаем результат: {1}")
    void yaSearchComplexTest(String testData, String expectedResult) {
        Selenide.open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$(".serp-item")
                .find(Condition.text(expectedResult))
                .shouldBe(visible);
    }

    static Stream<Arguments> methodSourceExampleTest(){
        return Stream.of(
                Arguments.of("first string", List.of(42, 13)),
                Arguments.of("second string", List.of(1, 2))
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String first, List<Integer> second){
    System.out.println(first + "and list" + second);
    }
}
