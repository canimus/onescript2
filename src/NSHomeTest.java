import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class NSHomeTest {
  private WebDriver driver;


  @Parameters({"remoteFlag"})
  @BeforeTest
  public void initBrowser(boolean remoteFlag) throws Exception {
    try {
      if (remoteFlag) {
        String hubURL = "http://192.168.33.10:4444/wd/hub";
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(hubURL), capability);
      } else {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
      }
    } catch (Exception e) {
      System.out.println("Unable to build a proper URL with: " + e.toString());
    }
  }

  @Test()
  public void launchSite() {
    try {
      new NSHomePO(driver, "http://www.ns.nl")
          .acceptCookies()
      .enterArrival("Utrecht")
      .enterDeparture("Schiphol")
      .getRideOptions();

      assertThat(driver.getTitle()).contains("Reisplanner | Reisinformatie | NS").as("Matching title");
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
      e.printStackTrace();
    } finally {
      driver.quit();
    }
  }
}
