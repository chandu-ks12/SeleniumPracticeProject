package Demo;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRM {
    public WebDriver driver;  // Declare driver at class level
    
    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver (make sure it's in system PATH or set system property)
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/Drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);  // Initialize driver
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test
    public void loginTest() {
        // Locate username and password fields
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        String pageTitle = driver.getTitle();
        if (pageTitle.equals("OrangeHRM")) {
            System.out.println("Login passed");
        } else {
            System.out.println("Login failed");
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);  // Sleep for 10 seconds (not recommended in real scenarios)
        driver.close();
        driver.quit();
    }
}