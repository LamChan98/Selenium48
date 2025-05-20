package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaisicAuthenticationTest {
    @Test
    void verifyLoginSuccessWithValidCredentials(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String welcomeMessage = driver.findElement(By.tagName("p")).getText();
        Assert.assertTrue(welcomeMessage.contains("Congratulations! You must have the proper credentials."));
        driver.quit();
    }
}
