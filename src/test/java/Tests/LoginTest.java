package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {
        test = extent.createTest("Login Test");
        login("standard_user", "secret_sauce");
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("inventory"), "Login failed!");
        test.pass("Login successful and landed on inventory page");
    }
}
