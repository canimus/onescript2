import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class NSHomeTest {
  private WebDriver driver;

  @Before
  public void initBrowser() throws Exception {
    // TODO: This needs to parameterised at runtime with a configuration file
    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\drivers\\chromedriver.exe");
    //System.setProperty("webdriver.chrome.driver", "/vagrant/chromedriver");

    // TODO: This is another element that requires driver to be a parameter
    //driver = new ChromeDriver();
    String hubURL = "http://192.168.33.10:4444/wd/hub";


    DesiredCapabilities capability = DesiredCapabilities.chrome();
    //DesiredCapabilities capability = (DesiredCapabilities) DesiredCapabilities.class.getMethod(browserType, String.class).invoke(null);
    //capability.setCapability("takeScreenshoot", true);
    try {
      driver = new RemoteWebDriver(new URL(hubURL), capability);
      //driver = new ChromeDriver();

      //driver.manage().window().maximize();
    } catch (Exception e) {
      System.out.println("Unable to build a proper URL with: " + e.toString());
    }

  }

  @Test
  public void launchSite() {
    try {

      NSHomePO page = new NSHomePO(driver, "http://www.ns.nl");
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
      FileUtils.copyFile(scrFile, new File("screenshot.png"));
      page.planRide("Utrecht", "Schiphol");
      assertThat(driver.getTitle()).contains("Reisplanner | Reisinformatie | NS").as("Matching title");
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
      e.printStackTrace();
    } finally {
      driver.quit();
    }
  }
}
