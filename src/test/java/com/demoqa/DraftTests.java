package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftTests extends TestBase {

    @Test
    void someTest1() {

        /*
        Добрый день!
        Подскажите пожалуйста по поводу загрузки картинок в форму авторизации,
        Я загрузила изображение в директорию resources, обратилась к ней через  uploudfromclasspath(...\image.jpg),
        а как надо было бы обратиться, чтобы задать в скобках полный путь? C:\\Users\\...image.jpg
         */
        open("/automation-practice-form");

        String filPath = "image.jpg";
        $("#uploadPicture").uploadFromClasspath(filPath);

        File file = new File("src/test/resources/image.jpg");
//        File file = new File("/Users/stanislav/IdeaProjects/demoqa-tests-19/src/test/resources/image.jpg"); // BAD PRACTICE
        $("#uploadPicture").uploadFile(file);
    }

    @Test
    void someTest2() {
        /*
        А как лучше кликнуть по чекбоксу? Через .click или булеановский метод .set...
         */
        open("/automation-practice-form");

        $("#hobbies-checkbox-1").setSelected(true);
        // Invalid element state [#hobbies-checkbox-1]: Cannot change invisible element

        $("#hobbies-checkbox-1").click();
        // Invalid element state [#hobbies-checkbox-1]: element click intercepted:
        // Element <input type="checkbox" id="hobbies-checkbox-1" class="custom-control-input" value="1">
        // is not clickable at point (747, 628). Other element would receive the click: <label title=""
        // for="hobbies-checkbox-1" class="custom-control-label">...</label>

        $("#hobbies-checkbox-1").parent().click(); // BEST

        $(byText("Sports")).click();
    }

    @Test
    void someTest3() {
        /*
        привет, не справился с радио батоном Gender
         */
        open("/automation-practice-form");

        $("#hobbies-checkbox-1").click(); // bad
        $("#hobbies-checkbox-1").parent().click(); // good
        $("[for=gender-radio-3]").click(); // good
    }

    @Test
    void someTest4() {
        /*
        Привет! Пытался сделать кликание на форму с автозаполнением так: $("#subjectsInput").setValue(subject1.substring(0,1));
        $(".subjects-auto-complete__control").find(byText(subject1)).click();
        Т.е. вводится первый символ, а затем выбирается значение из выпадающего списка. Но не получается, не находит нужный селектор.
        Как найти этот селектор (ответственный за выпадающий список в форме автозаполнения)? Вроде все перепробовал.
         */
        open("/automation-practice-form");

        $("#subjectsInput").setValue("Math").pressEnter();
    }
}
