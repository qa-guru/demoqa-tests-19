package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.LoginPage;
import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
//        Configuration.timeout = 3000;
        Configuration.holdBrowserOpen = true;

    }
}
