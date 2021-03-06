package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderFormTest {

    @Test
    void shouldFillCorrectRegister() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").waitUntil(visible, 11000);
    }

    @Test
    void shouldSendEmptyForm() {
        open("http://localhost:9999");
        $(byText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).isDisplayed();
    }

    @Test
    void shouldWrongCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Париж");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).isDisplayed();
    }

    @Test
    void shouldNoDate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Абакан");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Неверно введена дата")).isDisplayed();
    }

    @Test
    void shouldWrongName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("456211");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).isDisplayed();
    }

    @Test
    void shouldwrongPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("89998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).isDisplayed();
    }

    @Test
    void shouldNotmarkCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $(byText("Забронировать")).click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldClosePopup() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        $("[data-test-id='date'] .input__control").setValue(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id='name'] .input__control").setValue("Петров Николай");
        $("[data-test-id='phone'] .input__control").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").waitUntil(visible, 11000);
        $(".notification__closer").click();
    }
}
