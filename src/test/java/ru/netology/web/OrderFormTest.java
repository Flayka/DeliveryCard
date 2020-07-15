package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderFormTest {

    @Test
    void shouldFillCorrectRegister() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 11000);
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
        $("[placeholder='Город']").setValue("Париж");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).isDisplayed();
    }

    @Test
    void shouldNoDate() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Абакан");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Неверно введена дата")).isDisplayed();
    }

    @Test
    void shouldWrongName() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("456211");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).isDisplayed();
    }

    @Test
    void shouldwrongPhone() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("89998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).isDisplayed();
    }

    @Test
    void shouldNotmarkCheckbox() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("+79998887766");
        $(byText("Забронировать")).click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldClosePopup() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Барнаул");
        $(".menu-item_type_block").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        $("[placeholder='Дата встречи']").setValue(format.format(cal.getTime()));
        $("[name='name']").setValue("Петров Николай");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 11000);
        $(".notification__closer").click();
    }
}
