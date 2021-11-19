import net.bytebuddy.build.Plugin;
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
    //set system property to get chrome driver
    System.setProperty("webdriver.chrome.driver", "src//BrowserDriver//chromedriver.exe");

    //Initiate Webdriver Object
    WebDriver driver = new ChromeDriver();

    driver.get(baseURL);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);

    //Identify all links from the page and store them in List
    List<WebElement> linkTest = driver.findElements(By.tagName("a"));

    //Get Iterator to transver through the List which got all tag"a"
    Iterator<WebElement> itTest = linkTest.iterator();

    //Put in a condition to get attribute to check link
    while (itTest.hasNext()){

     //get href of anchor tag & store in url variable
      url = itTest.next().getAttribute("href");
      System.out.println(url);

      //Check url is null or Empty & skip next if condition meets
      if ( url == null || url.isEmpty()){
        System.out.println("URL is not configure or not working");
        continue;
      }
      //check url is third party
      if ( ! url.startsWith(baseURL)){
        System.out.println("URL is from another domain");
        continue;
      }

      try{
        //send http request and collect response code
        httpUrl = (HttpURLConnection) (new URL(url).openConnection());

        httpUrl.setRequestMethod("HEAD");

        httpUrl.connect();

        responseOkCode = httpUrl.getResponseCode();
        //Check responsecode for validity
        if (responseOkCode >= 400 ){
          System.out.println(url +"is a not valid link");
        }
        else{
          System.out.println(url +"is a valid link");
        }

      }
      //catch Exception
      catch (Exception e){
        e.printStackTrace();

      }
    }
    //Exit from browser
    driver.quit();
  }

  @Test
  public void openBrowserToCheckRow(){

    System.setProperty("webdriver.chrome.driver", "src//BrowserDriver//chromedriver.exe");
    driver = new ChromeDriver();

    driver.get(baseURL);
    driver.manage().deleteAllCookies();

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);


    //Obtain elements with table tag and store in List
    List<WebElement> rows =  driver.findElements(By.xpath("//table/tbody"));

    driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);
    //get number of rows
    int countRow = rows.size();
    System.out.println("=====Total No. of Rows is ======="+countRow);

    //Close current browser
    driver.close();
  }

}

