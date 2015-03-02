package com.mycompany.app;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Parsh Toora
 * Date: 01/03/15
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public class StepDefs {
    WebDriver driver=null;
    String productName="";

    @Given("^I have added a shirt to my bag$")
    public void I_have_added_a_shirt_to_my_bag() throws Throwable {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://www.marksandspencer.com/");
        WebElement elm = driver.findElement(By.id("global-search"));
        elm.sendKeys("shirts");
        elm = driver.findElement(By.id("goButton"));
        elm.click();
        List<WebElement> elms = driver.findElements(By.className("prodAnchor"));
        elms.get(0).click();
        productName=driver.findElement(By.cssSelector("[itemprop='name']")).getText();
        elm = driver.findElement(By.cssSelector("[for='16DUMMY']"));
        elm.click();
        elm = driver.findElement(By.cssSelector("input.basket"));
        elm.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@class='number-count']//*[contains(text(),'1')]"));
    }

    @When("^I view the contents of my bag$")
    public void I_view_the_contents_of_my_bag() throws Throwable {
        WebElement elm = driver.findElement(By.cssSelector(".basket .header-link"));
        elm.click();
    }

    @Then("^I can see the contents of the bag include a shirt$")
    public void I_can_see_the_contents_of_the_bag_include_a_shirt() throws Throwable {
        driver.findElement(By.id("basket-page"));
        String description = driver.findElement(By.className("product-item")).getText();
       Assert.assertTrue(description.contains(productName));
        driver.close();
    }
}
