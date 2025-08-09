package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.*;

public class ProductFilterTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testFilterLowToHigh_And_Checkout() throws InterruptedException {
        // Apply filter: Price Low to High


        Select filterDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container"))));
        filterDropdown.selectByValue("lohi");


        // Verify prices sorted
        List<WebElement> pricesElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price")));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElem : pricesElements) {
            prices.add(Double.parseDouble(priceElem.getText().replace("$", "")));
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(prices, sortedPrices, "Products are not sorted by price low to high");

        // Select first product
        WebElement firstAddToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]")));
        firstAddToCartBtn.click();

        // Go to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();

        // Checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // Fill details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();

        // Finish order
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

        // Validate order confirmation
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertTrue(confirmationMsg.getText().contains("THANK YOU"), "Order confirmation not displayed");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
