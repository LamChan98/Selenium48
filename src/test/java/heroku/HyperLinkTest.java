package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HyperLinkTest {
    /*TC04: Hyper link : Hyperlink - link text
    Open browser
    Navigate to https://the-internet.herokuapp.com/status_codes
    Click on "200"
    Then "200 status code" page appear
    Click on "go here"
    Click on "301"
    Then "301 status code" page appear
    Click on "go here"
    Click on "404"
    Then "404 status code" page appear
    Click on "go here"
    Click on "500"
    Then "500 status code" page appear
    Click on "go here"*/

    @Test
    void tc04() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/status_codes");

//        driver.findElement(By.xpath("//a[@href='status_codes/200']")).click();
//        driver.findElement(By.xpath("//a[.='200']")).click();
//        driver.findElement(By.cssSelector("a[href='status_codes/200']")).click();

        //200 test
        String href = driver.findElement(By.linkText("200")).getDomAttribute("href");
        driver.findElement(By.linkText("200")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains(href));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        String content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 200 status code."));

        //301 test
        driver.navigate().back();
        driver.findElement(By.linkText("301")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/301");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 301 status code."));

        //404 test
        driver.navigate().back();
        driver.findElement(By.linkText("404")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/404");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 404 status code."));

        //500 test
        driver.navigate().back();
        driver.findElement(By.linkText("500")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/500");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 500 status code."));

        driver.quit();
    }
}
