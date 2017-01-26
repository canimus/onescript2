import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class NSHomeTest {
  private WebDriver driver;

  @Before
  public void initBrowser() {
    // TODO: This needs to parameterised at runtime with a configuration file
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\drivers\\chromedriver.exe");

    // TODO: This is another element that requires driver to be a parameter
    driver = new ChromeDriver();

  }

  @Test
  public void launchSite() {
    try {

      NSHomePO page = new NSHomePO(driver, "http://www.ns.nl");
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
