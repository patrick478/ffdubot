import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.runner.JUnitCore;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class FFDU {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public static void main(String[] args) {
        JUnitCore jCore = new JUnitCore();
        jCore.run();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://ffduawards.com.au/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testFfdu() throws Exception {
        int i = 0;

        while (true) {
            try {
                Faker faker = new Faker();

                String name = faker.name().fullName();
                String email = faker.internet().emailAddress();


                driver.get(baseUrl + "/");

                WebElement element = driver.findElement(By.id("noms"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(500);


                driver.findElement(By.id("input_3_1")).clear();
                driver.findElement(By.id("input_3_1")).sendKeys(name);
                driver.findElement(By.id("input_3_2")).clear();
                driver.findElement(By.id("input_3_2")).sendKeys(email);
                driver.findElement(By.id("gform_next_button_3_4")).click();

                for (int second = 0; ; second++) {
                    if (second >= 60) break;
                    try {
                        if (driver.findElement(By.id("choice_3_3_5")).isDisplayed()) break;
                    } catch (Exception e) {
                    }
                    Thread.sleep(1000);
                }

                driver.findElement(By.id("choice_3_3_5")).click();
                driver.findElement(By.id("gform_next_button_3_5")).click();
                for (int second = 0; ; second++) {
                    if (second >= 60) break;
                    try {
                        if (driver.findElement(By.id("choice_3_14_1")).isDisplayed()) break;
                    } catch (Exception e) {
                    }
                    Thread.sleep(1000);
                }

                driver.findElement(By.id("choice_3_14_1")).click();
                driver.findElement(By.id("gform_next_button_3_15")).click();

                for (int second = 0; ; second++) {
                    if (second >= 60) break;
                    try {
                        if (driver.findElement(By.id("gforms_confirmation_message")).isDisplayed()) break;
                    } catch (Exception e) {
                    }
                    Thread.sleep(1000);
                }
                System.out.println(++i);
            }
            catch(Exception e){

            }
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
