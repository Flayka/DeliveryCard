package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutoCompliteTest {

    @Test
    void shouldFillCorrectRegisterBySearching() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("ба");
        $(".input__menu").sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER));
        $$(".input__box").find(text("Барнаул"));

        $("[placeholder='Дата встречи']").click();

        SelenideElement yearButton = $("[data-step='12']");
        SelenideElement year = $(".calendar__name");
        while (!year.getText().equals("Июль 2025")) {
            yearButton.click();
        }
        SelenideElement monthButton = $("[data-step='1']");
        SelenideElement month = $(".calendar__title");
        while (!month.getText().equals("Октябрь 2025")) {
            monthButton.click();
        }

        $$(".calendar__day").findBy(exactTextCaseSensitive("15")).click();
        $("[placeholder='Дата встречи']").find(byText("15.10.2025"));

    }

    @Test
    void shouldFillCorrectRegisterByClick() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("ба");
        $(".input__menu").sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER));
        $$(".input__box").find(text("Барнаул"));

        $("[placeholder='Дата встречи']").click();
        $("[data-step='12']").click();
        $("[data-step='1']").doubleClick().click();
        $$(".calendar__day").findBy(exactText("16")).click();
        $("[placeholder='Дата встречи']").find(String.valueOf(text("16.10.2021")));
    }
}
