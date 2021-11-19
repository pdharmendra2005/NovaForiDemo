import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class TableRowCount {

  public static WebDriver driver;
  static String baseURL= "https://books.google.co.uk/";

  @Test
  public void openBrowserToCheckRaw(){

    System.setProperty("webdriver.chrome.driver", "src//BrowserDriver//chromedriver.exe");
    driver = new ChromeDriver();

    driver.get(baseURL);
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();

    List<WebElement> raws =  driver.findElements(By.xpath("//table/tbody"));

    int countRaw = raws.size();
    System.out.println("No. of Raws is "+countRaw);

    driver.close();
  }



}

