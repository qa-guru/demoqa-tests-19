package com.demoqa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.demoqa.utils.RandomUtils.*;

public class RegistrationWithFakerTests extends TestBase {


    @Test
    void successfulRegistrationTest() {
//        Faker faker = new Faker();
        Faker faker = new Faker(new Locale("fr"));

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                currentAddress = faker.twinPeaks().quote();

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(getRandomGender())).click();
        $("#userNumber").setValue(getRandomInt(111111111, 999999999) + "");
//        $("#userNumber").setValue("+5 (" + getRandomInt(111, 999) + ") " + getRandomInt(1111, 9999) + " - " + getRandomInt(111, 999));
//        $("#userNumber").setValue(getRandomPhone());
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName),
                text(userEmail), text("1234567890"), text(getRandomGender()), text(currentAddress));
    }
}
