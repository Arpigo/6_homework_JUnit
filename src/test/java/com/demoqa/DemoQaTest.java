package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaTest {

    @BeforeAll
    static void setUP () {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void setMod () {
        open ("/text-box");
    }

    @AfterEach
    void close(){
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {
            "Pavel",
            "Andrey",
            "Lina",
            "Vasya"
    })
    @ParameterizedTest(name="Проверка заполнения формы {0}")
    void valueSourceFormTest (String name) {
        String userEmail = "test@mail.ru";
        $("#userName").setValue(name);
        $("#userEmail").setValue(userEmail);
        $("#submit").click();
        $("#output").shouldHave(
                text(name),
                text(userEmail));
    }


    @CsvSource(value = {
            "Pavel | pavel@mail.com | Saint Peterburg | Moscow",
            "Andrey | andrey@mail.com | Moscow | Ekaterenburg",
            "Lina | lina@mail.com | Omsk | Krasnodar",
            "Vasya | vasya@mail.com | No Address |  No Address"
    },
            delimiter ='|'
    )
    @ParameterizedTest(name="Заполнение формы Text Box {0}")
    void csvFillForm(String name, String userEmail, String curAddress, String perAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(curAddress);
        $("#permanentAddress").setValue(perAddress);
        $("#submit").click();
        $("#output").shouldHave(
                text(name),
                text(userEmail),
                text(curAddress),
                text(perAddress));
    }


    @Disabled ("Пример отключенного теста")
    @Test
    void disebledExample () {
        String name = "Pavel";
        String userEmail = "pavel@mail.com";
        String curAddress = "Saint Peterburg";
        String perAddress = "Moscow";
        $("#userName").setValue(name);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(curAddress);
        $("#permanentAddress").setValue(perAddress);
        $("#submit").click();
        $("#output").shouldHave(
                text(name),
                text(userEmail),
                text(curAddress),
                text(perAddress));
    }


    static Stream<Arguments> methodSourceExampleTest() {
        return  Stream.of(
                Arguments.of("Pavel", "pavel@mail.com", "Saint Peterburg", "Moscow"),
                Arguments.of("Andrey", "andrey@mail.com", "Moscow", "Ekaterenburg"),
                Arguments.of("Lina", "lina@mail.com", "Omsk", "Krasnodar"),
                Arguments.of("Vasya", "vasya@mail.com", "No Address", "No Address")
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String name, String userEmail, String curAddress, String perAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(curAddress);
        $("#permanentAddress").setValue(perAddress);
        $("#submit").click();
        $("#output").shouldHave(
                text(name),
                text(userEmail),
                text(curAddress),
                text(perAddress));
    }
}
