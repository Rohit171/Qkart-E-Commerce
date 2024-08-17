package QKART_SANITY_LOGIN.Module1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogin(String Username, String Password) throws InterruptedException {
        // Find the Username Text Box
        WebElement username_txt_box = this.driver.findElement(By.id("username"));

        // Enter the username
        username_txt_box.sendKeys(Username);

        // Wait for user name to be entered
        Thread.sleep(1000);

        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("password"));

        // Enter the password
        password_txt_box.sendKeys(Password);

        // Find the Login Button
        WebElement login_button = driver.findElement(By.className("button"));

        // Click the login Button
        login_button.click();

        // SLEEP_STMT_13: Wait for Login to Complete
        // Wait for Login action to complete



        
       

    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Duration.ofSeconds(30L)))
                            .pollingEvery(Duration.ofMillis(250)).ignoring(Exception.class);

            


        
                                                         // thrown
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[text()='Logged in successfully']")));

        return this.VerifyUserLoggedIn(Username);
    }

    public Boolean VerifyUserLoggedIn(String Username) {
        try {
            // Find the username label (present on the top right of the page)
            WebElement username_label;
            username_label = this.driver.findElement(By.className("username-text"));
            return username_label.getText().equals(Username);
        } catch (Exception e) {
            return false;
        }

    }

}
