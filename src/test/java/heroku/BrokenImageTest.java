package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class BrokenImageTest {
    @Test
    void verifyBrokenImage() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/broken_images");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"Broken Images"));

        List<WebElement> images = driver.findElements(By.cssSelector(".example img"));
        images.forEach(image -> {
           String imageUrl = image.getDomAttribute("src");
           String naturalWidth = image.getDomProperty("naturalWidth");
           String naturalHeight = image.getDomProperty("naturalHeight");
            System.out.println("-------------------------");
            System.out.println("Image URL :" + imageUrl);
            System.out.println("Natural Width :" + naturalWidth);
            System.out.println("Natural Height :" + naturalHeight);
        });

        Assert.assertEquals(images.get(0).getDomProperty("naturalWidth"),"0");
        Assert.assertEquals(images.get(1).getDomProperty("naturalWidth"),"0");
//        Assert.assertEquals(images.get(2).getDomProperty("naturalWidth"),"0");

        driver.quit();
    }
}
