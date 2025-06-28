package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedFrameTest {
    /*    TC06: Frame : Nested frames
        Open browser
        Navigate to https://the-internet.herokuapp.com/nested_frames
        Verify Text present:
        Copy
        LEFT
        RIGHT
        MIDDLE
        BOTTOM*/
    @Test
    void tc06() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        System.out.println(driver.findElement(By.xpath("//html/body")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//html/body")).getText().contains("LEFT"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        System.out.println(driver.findElement(By.id("content")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//html/body")).getText().contains("MIDDLE"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        System.out.println(driver.findElement(By.xpath("//html/body")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//html/body")).getText().contains("RIGHT"));

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        System.out.println(driver.findElement(By.xpath("//html/body")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//html/body")).getText().contains("BOTTOM"));

        driver.quit();
    }

}
