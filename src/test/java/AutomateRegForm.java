
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomateRegForm {
     WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void registrationForm() throws InterruptedException {
        Random random = new Random();
        int randomNum = 100 + random.nextInt(900);
        String finalNum = String.valueOf(randomNum);
        String emailAccount="emran"+finalNum+"@gmail.com";


        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        WebElement fName = driver.findElement(By.id("first_name"));
        WebElement lName = driver.findElement(By.id("last_name"));
        WebElement email = driver.findElement(By.id("user_email"));
        WebElement gender = driver.findElement(By.id("radio_1665627729_Male"));
        WebElement password = driver.findElement(By.cssSelector("[type=password]"));

        WebElement datePicker = driver.findElement(By.className("ur-flatpickr-field"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='1997-07-17';", datePicker);

        WebElement nationality = driver.findElement(By.id("input_box_1665629217"));
        WebElement phoneNumber = driver.findElement(By.cssSelector("[name=phone_1665627880]"));
        WebElement country = driver.findElement(By.id("country_1665629257"));
        WebElement termsCondition = driver.findElement(By.id("privacy_policy_1665633140"));


        fName.sendKeys("Emran");
        lName.sendKeys("Hasan");
        email.sendKeys(emailAccount);
        gender.click();
        password.sendKeys("EMran@1111+*....");
        driver.findElement(By.tagName("body")).click();
        Thread.sleep(3000);
        nationality.sendKeys("Bangladeshi");
        Utility.scroll(driver, 3000);
        phoneNumber.sendKeys("01900123" + finalNum);
        country.sendKeys("Bangladesh");
        Utility.scroll(driver, 500);
        termsCondition.click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(2000);

        Utility.scroll(driver,20);
        String successMsgExpected = "User successfully registered.";
        String successMsg = driver.findElement(By.className("user-registration-message")).getText();
        Assertions.assertEquals(successMsgExpected,successMsg);

    }
}

