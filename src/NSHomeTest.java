import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hvazquez on 1/26/2017.
 */
public class NSHomeTest {
  WebDriver driver;

  @Before
  public void initBrowser() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.navigate().to("http://www.ns.nl");
  }

  @Test
  public void launchSite() {
    try {

      NSHomePO page = new NSHomePO(driver);
      page.planRide("Utrecht", "Schiphol");
      assertThat(driver.getTitle()).contains("Reis").as("Matching title");
    } catch(Exception e) {
      System.out.println("Error: " + e.toString());
      e.printStackTrace();
    } finally {
      driver.quit();
    }
  }
}
