package moatazeldebsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicAuthenticationTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @BeforeMethod
    void reloadPage(){
        driver.get("https://moatazeldebsy.github.io/test-automation-practices/#/auth");
    }

    @Test
    void verifyBasicAuthentiacation() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[.='Basic Authentication']"))));
        driver.findElement(By.cssSelector("[data-test='username-input']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[data-test='password-input']")).sendKeys("admin");
        driver.findElement(By.cssSelector("[data-test=login-button]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[.='Successfully authenticated!']")));
        String sMessage = driver.findElement(By.tagName("h2")).getText();
        Assert.assertTrue(sMessage.contains("Successfully authenticated!"));
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
