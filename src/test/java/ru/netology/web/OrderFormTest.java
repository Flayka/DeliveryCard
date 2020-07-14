package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderFormTest {

    @Test
    void shouldCorrectRegister() {
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
}
