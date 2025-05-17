package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormAuthenticationTest {
/*    TC01: Form  Authentication :  Login successful with valid credentials
    Open browser
    Navigate to https://the-internet.herokuapp.com/login
    Fill in username with tomsmith
    Fill in the password with SuperSecretPassword!
    Click on Login button
    And the home page is appear*/

    @Test
    void tc01(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
//        driver.findElement(By.tagName("input")).sendKeys("tomsmith");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
//        driver.findElement(By.name("username")).sendKeys("tomsmith");
        //type
//        driver.findElement(By.cssSelector("[type=text]")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("input[type=text]")).sendKeys("tomsmith");
//
//        driver.findElement(By.xpath("//*[@type='text']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("tomsmith");

        //ìd
//        driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("input#username")).sendKeys("tomsmith");

//        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");

        //name
//        driver.findElement(By.cssSelector("[name=username]")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("tomsmith");
//
//        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");

        //password
         driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("SuperSecretPassword!");
//        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        //click login button
//        driver.findElement(By.tagName("button")).click();
//        driver.findElement(By.className("radius")).click();
//        driver.findElement(By.cssSelector(".radius")).click();
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(driver.findElement(By.tagName("h4")).getText().contains("Welcome to the Secure Area. When you are done click logout below."));

        /*//label[.='Username']
        //input[.='Username']/preceding-sibling::label/../input
        //label[.='Username']/following-sibling::input
        // label[.='Password']/../input

        By usernameInput = RelativeLocator.with(By.tagName("input"))
                .below(By.xpath("//label[.='Username']"));
        driver.findElement(usernameInput).sendKeys("tomsmith");*/
    }
}
