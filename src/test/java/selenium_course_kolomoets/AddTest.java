package selenium_course_kolomoets;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAdd() throws Exception {
    driver.get(baseUrl + "/php4dvd/?go=add");
    driver.findElement(By.name("name")).clear();
    //проверка, что появилась подсказка о том, что поле "name" обязательное:
    isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[2]/td[2]/label"));
    //попытка отправить форму с незаполненым обяз.полем. Должны остаться на той же странице(т.е.остальные элементы должны находиться)
    driver.findElement(By.id("submit")).click();
    
    driver.findElement(By.name("name")).sendKeys("test_7");
    driver.findElement(By.name("aka")).clear();
    driver.findElement(By.name("aka")).sendKeys("Test7");
    driver.findElement(By.name("year")).clear();
    
    isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[4]/td[2]/label"));
    driver.findElement(By.id("submit")).click();
    
    driver.findElement(By.name("year")).sendKeys("2001");
    driver.findElement(By.name("plotoutline")).clear();
    driver.findElement(By.name("plotoutline")).sendKeys("Horror");
    driver.findElement(By.id("text_languages_0")).clear();
    driver.findElement(By.id("text_languages_0")).sendKeys("EN");
    driver.findElement(By.name("subtitles")).clear();
    driver.findElement(By.name("subtitles")).sendKeys("RUS");
    driver.findElement(By.name("country")).clear();
    driver.findElement(By.name("country")).sendKeys("USA");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("h1")).click();
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
//    	System.out.println(e.toString());
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
