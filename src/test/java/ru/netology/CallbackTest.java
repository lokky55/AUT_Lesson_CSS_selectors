package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll  // запускается один раз перед всеми тестами
    // делаем метод в котором устанавливаем свойства для драйвера (chrome driver из директории driver/win
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();  // подключаем браузер
    }

    @AfterEach
    public void close() {
        driver.quit();  // закрываем браузер
        driver = null;  // обнуляем драйвер после работы
    }

    @Test
    public void test() {
        driver.get("http://localhost:9999"); // открываем браузер
        // набираем костяк теста без описания функции findElement
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Александр");     // находим первый элемент - поле ввода имени, и вводим в него имя Андрей (командой sendkeys)
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79807133080"); // заполняем следующее поле заявки - телефон

        driver.findElement(By.className("checkbox__box")).click(); // следующее поле - чекбокс, командой click ставим в нем галочку
        driver.findElement(By.tagName("button")).click(); // следующее поле - кнопка ОТПРАВИТЬ - нажимаем ее той же командой click

        // теперь необходимо проверить что открылась страница с сообщением об успешной отправке заполненной формы

        String text = driver.findElement(By.className("paragraph")).getText(); // ищем по части выражения

//        Assertions.assertEquals("  Ваша заявка успешно отправлена!", text);
        Assertions.assertEquals("Ваша заявка успешно отправлена!", text.trim());  // или убираем пробелы методом .trim
    }

}

