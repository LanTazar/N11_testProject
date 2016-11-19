import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class N11Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ntegi\\Desktop\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://www.n11.com/";

    }

    @Test
    public void testCaseJava() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("Giriş Yap")).click();

        Thread.sleep(10000);

        driver.findElement(By.xpath("//form[@id='loginForm']/div[4]")).click();


        Thread.sleep(10000);


        String mainwindow = driver.getWindowHandle();
        for (String popup : driver.getWindowHandles()){
            driver.switchTo().window(popup);
        }


        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("namiktaha.egilli@stu.bahcesehir.edu.tr");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("hg48a211");


        driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();

        Thread.sleep(10000);


        driver.switchTo().window(mainwindow);

        assertEquals("Namık Taha Eğilli", driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div[2]/div[2]/div[1]/div[1]/a[2]")).getText());


        driver.manage().window().maximize();



        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'Kitap, Müzik, Film, Oyun')]"));

        WebElement subMenu = driver.findElement(By.xpath("//a[contains(text(),'Kitap')]"));

        Actions action = new Actions(driver);

        action.moveToElement(menu).perform();

        Thread.sleep(2000);

        action.click(subMenu).perform();


        assertEquals("Kitap", driver.findElement(By.xpath("(//a[contains(text(),'Kitap, Müzik, Film, Oyun')])[2]")).getText());

        driver.findElement(By.xpath("//*[@id=\"contentListing\"]/div/div/div[2]/section[3]/a")).click();

        Thread.sleep(2000);

        assertEquals("Yazarlar", driver.findElement(By.xpath("//*[@id=\"breadCrumb\"]/ul/li[4]/a/span")).getText());




    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
