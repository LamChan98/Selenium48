package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class Checkboxes {
    @Test
    void tc02(){
/*  TC02: Checkboxes : Check to a box
    Open browser
    Navigate to https://the-internet.herokuapp.com/checkboxes
    Check on checkbox1
    Verify checkbox1 is checked
    Check on checkbox2
    Verify checkbox2 is checked*/
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/checkboxes");
//        wait.until(ExpectedConditions.titleContains("TheInternet"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"), "Checkboxes"));

        //check checkbox1
//        driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).getDomAttribute("checked");
        if (!driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected()){
            driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).click();
//            driver.findElement(By.cssSelector("form#checkboxes input:nth-child(1)")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected());

        //check checkbox2
        if(driver.findElement(By.xpath("//form[@id='checkboxes']//input[2]")).isSelected()){
            driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected());

        driver.quit();
    }
}
