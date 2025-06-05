package heroku;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebTableTest {
    /*TC05: Web Table: Validate largest due person from a table
    Open browser
    Navigate to https://the-internet.herokuapp.com/tables
    Focus on table 1
    The person who has largest due is "Doe Jacson"*/

    @Test
    void tc05() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/tables");
/*        1. get row index of max due -> get last name/ first name of max due
        due comlumn xpath //table[@id='table1']/tbody/tr/td[4]
        lastname column xpath //table[@id='table1']/tbody/tr[row_index]/td[1]
        firstname column xpath //table[@id='table1']/tbody/tr[row_index]/td[2]*/

        List<Double> dueList = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(cell -> Double.valueOf(cell.getText().replace("$", "")))
                .collect(Collectors.toList());
        double maxDue = Collections.max(dueList);
        int rowIndex = dueList.indexOf(maxDue) + 1;
        String lastName = driver.findElement(
                By.xpath("//table[@id='table1']/tbody/tr[" + rowIndex + "]/td[1]")).getText();
        String firstName = driver.findElement(
                By.xpath("//table[@id='table1']/tbody/tr[" + rowIndex + "]/td[2]")).getText();
        Assert.assertEquals(String.format("%s %s", firstName, lastName), "Jason Doe");


    }

    @Test
    void tc06() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        List<Person> personList = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"))
                .forEach(row -> {
                    String lastName = row.findElement(By.xpath("./td[1]")).getText();
                    String firstName = row.findElement(By.xpath("./td[2]")).getText();
                    double due = Double.parseDouble(row.findElement(By.xpath("./td[4]")).getText().replace("$", ""));
                    personList.add(new Person(lastName, firstName, due));
                });
//        personList.forEach(person -> person.info());
//        personList.forEach(Person::info);

        double maxDue = personList.stream().max(Comparator.comparing(Person::getDue)).get().getDue();
        List<String> listPersonHaveMaxDue = personList.stream()
                .filter(p -> p.getDue() == maxDue).map(Person::getFullname).toList();

        Assert.assertEquals(listPersonHaveMaxDue, List.of("Jason Doe"));

        driver.quit();
    }

    @Test
    void tc07() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        List<Person> personList = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"))
                .forEach(row -> {
                    String lastName = row.findElement(By.xpath("./td[1]")).getText();
                    String firstName = row.findElement(By.xpath("./td[2]")).getText();
                    double due = Double.parseDouble(row.findElement(By.xpath("./td[4]")).getText().replace("$", ""));
                    personList.add(new Person(firstName, lastName, due));
                });

        double minDue = personList.stream().min(Comparator.comparing(Person::getDue)).get().getDue();
        List<String> listPersonHaveMinDue = personList.stream()
                .filter(p -> p.getDue() == minDue).map(Person::getFullname).toList();

        Assert.assertEquals(listPersonHaveMinDue, List.of("John Smith", "Tim Conway"));

        driver.quit();
    }

    @Test
    void verifyBuyOneWayTIcket() throws InterruptedException {
/*      1. open browser
        2. navigate to https://www.vietnamairlines.com/vn/vi/home
        3. select one way
        4. select day 7/4/2025
        5. verify day selected*/

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.vietnamairlines.com/vn/vi/home");
        //accept cookie
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Đồng ý']"))).click();
        //select one way
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='roundtrip-date-depart']"))).click();
        driver.findElement(By.cssSelector("[data-content-title='Một chiều']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("roundtrip-date-depart"))).click();

        //select date
        driver.findElements(By.cssSelector(".ui-datepicker-group-first a"))
                .stream()
                .filter(el -> el.getText().equals("25"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Date 25 not found"))
                .click();

        //verify
        String departDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("roundtrip-date-depart")))
                .getDomProperty("value");
        Assert.assertEquals(departDate, "25/05/2025");
    }

}
