package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;



import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

    public class BaseTest {
        protected WebDriver driver;
        protected WebDriverWait wait;
        protected static ExtentReports extent;
        protected ExtentTest test;

        @BeforeSuite
        public void startReport() {
            ExtentSparkReporter spark = new ExtentSparkReporter("extentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        @BeforeMethod
        public void setupDriver() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void login(String username, String password) {
            driver.get("https://www.saucedemo.com/v1/");
            driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys(username);
            driver.findElement(org.openqa.selenium.By.id("password")).sendKeys(password);
            driver.findElement(org.openqa.selenium.By.id("login-button")).click();
        }

        @AfterMethod
        public void closeDriver() {
            if (driver != null) driver.quit();
        }

        @AfterSuite
        public void flushReport() {
            extent.flush();
        }
    }


