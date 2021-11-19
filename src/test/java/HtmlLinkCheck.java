import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class HtmlLinkCheck {

  public static WebDriver driver;
  static String baseURL = "https://books.google.co.uk/";
  String url = "";
  HttpURLConnection httpUrl ;
  int responseOkCode = 200 ;



  @Test
  public void checkLinkOk(){
    System.setProperty("webdriver.chrome.driver", "src//BrowserDriver//chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    driver.get(baseURL);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);

    List<WebElement> linkTest = driver.findElements(By.tagName("a"));

    Iterator<WebElement> itTest = linkTest.iterator();

    while (itTest.hasNext()){

      url = itTest.next().getAttribute("href");
      System.out.println(url);

      if ( url == null || url.isEmpty()){
        System.out.println("URL is not configure or not working");
        continue;
      }
      if ( ! url.startsWith(baseURL)){
        System.out.println("URL is from another domain");
        continue;
      }

      try{
        httpUrl = (HttpURLConnection) (new URL(url).openConnection());

        httpUrl.setRequestMethod("HEAD");

        httpUrl.connect();

        responseOkCode = httpUrl.getResponseCode();

        if (responseOkCode >= 400 ){
          System.out.println(url +"is a not valid link");
        }
        else{
          System.out.println(url +"is a valid link");
        }

      }
      catch (Exception e){
        e.printStackTrace();

      }


    }
    driver.quit();
  }

}