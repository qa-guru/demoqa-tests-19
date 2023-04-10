package com.demoqa.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithPageObjectsTests extends TestBase {

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                        .setFirstName("Alex")
                        .setLastName("Egorov")
                        .setUserEmail("alex@egorov.com")
                        .setGender("Other")
                        .setNumber("1234567890")
                        .setBirthDate("30", "July", "2008");

        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        registrationPage.verifyRegistrationResultsModalAppears()
                .verifyResult("Full name", "Alex Egorov")
                .verifyResult("Email", "alex@egorov.com")
                .verifyResult("Phone", "1234567890");
    }
}
