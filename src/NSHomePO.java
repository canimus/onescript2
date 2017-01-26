import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class NSHomePO extends PageObject {

  @FindBy(name = "departure")
  @CacheLookup
  private WebElement departureInput;

  @FindBy(name = "arrival")
  @CacheLookup
  private WebElement arrivalInput;

  @FindBy(xpath = "//button[(@type='submit') and (span[contains(text(),'Plannen')])]")
  @CacheLookup
  private WebElement planButton;

  @FindAll(@FindBy(xpath = "//a[contains(text(), 'Accepteer')]"))
  private List<WebElement> acceptCookiesDialogLink;

  @FindAll({@FindBy(xpath = "//input[@name='departure']/following-sibling::div/ul/li")})
  private List<WebElement> suggestedDepartures;

  @FindAll(@FindBy(xpath = "//input[@name='arrival']/following-sibling::div/ul/li"))
  private List<WebElement> suggestedArrivals;

  public NSHomePO(WebDriver driver) {
    super(driver);
  }

  public NSHomePO(WebDriver driver, String url) {
    super(driver, url);
  }

  public NSHomePO acceptCookies() {
    try {
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
      if (!acceptCookiesDialogLink.isEmpty()) {
        acceptCookiesDialogLink.iterator().next().click();
      }
    } catch (TimeoutException e) { // No action if modal not present
    } finally {
      // Return to main window and continue
      driver.switchTo().defaultContent();
    }
    return this;
  }

  public NSHomePO enterDeparture(String city) throws Exception {
    departureInput.sendKeys(city);
    wait.until(ExpectedConditions.visibilityOfAllElements(suggestedDepartures));

    // Action pattern
    // Useful working with hovering and hanging menus
    new Actions(driver)
        .moveToElement(departureInput)
        .moveToElement(filterDropdown(suggestedDepartures,city))
        .click()
        .build()
        .perform();

    return this;
  }

  public NSHomePO enterArrival(String city) throws Exception {
    arrivalInput.sendKeys(city);
    wait.until(ExpectedConditions.visibilityOfAllElements(suggestedArrivals));
    new Actions(driver)
        .moveToElement(arrivalInput)
        .moveToElement(filterDropdown(suggestedArrivals,city))
        .click()
        .build()
        .perform();

    return this;
  }

  public NSRidePlanPO getRideOptions() {
    planButton.click();
    return new NSRidePlanPO(driver);
  }

  public NSRidePlanPO planRide(String start, String end) throws Exception {
    return this.acceptCookies()
        .enterDeparture(start)
        .enterArrival(end)
        .getRideOptions();
  }
}