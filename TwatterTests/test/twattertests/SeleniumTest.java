/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twattertests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.UUID;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Jesse
 */
public class SeleniumTest
{
    private static WebDriver driver;
    private static long WAIT_TIME = 3;

    @BeforeClass
    public static void setup()
    {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.get("http://localhost:8080/Twatter-war/");

    }

    @AfterClass
    public static void teardown()
    {
        driver.quit();
    }

    @Test
    public void teststuf()
    {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twat-holder")));
    }

    @Test
    public void testmeerstuf()
    {
        final String twat = UUID.randomUUID().toString();
        
        WebElement us = driver.findElement(By.name("titlebox"));
        WebElement pw = driver.findElement(By.name("contentsbox"));
        WebElement btn = driver.findElement(By.id("submitbutton"));
        us.sendKeys(twat);
        pw.sendKeys("een random stuf");
        btn.click();

        new WebDriverWait(driver, WAIT_TIME, 100).until(new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver f)
            {
                try
                {
                    driver.findElement(By.xpath("//h3[contains(text(), '" + twat + "')]/.."));
                    return true;
                }
                catch (Exception e)
                {
                }
                return false;
            }
        });
    }
}
