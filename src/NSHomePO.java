/**
 * Author: Herminio Vazquez
 * Date: January 25, 2017
 * Project: ING Mortgages
 * Company: http://iovio.com
 */

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NSHomePO {
  WebDriver driver;
  WebDriverWait wait;

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

  public static WebElement filterDropdown(List<WebElement> collection, String item) {
    return collection
        .stream()
        .filter(e -> e.getText().contains(item))  // Filters all items by text
        .collect(Collectors.toList())  // Returns a list of matched items
        .get(0); // Gets the first item of the matching list or
                 // Throws ArrayIndexOutOfBounds exception when no match
  }

  public NSHomePO(WebDriver driver) {
    this.driver = driver;
    // Wait a maximum of 2 seconds in this page
    this.wait = new WebDriverWait(this.driver, 2);

    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

  public NSHomePO acceptCookies() {
    try {
      this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
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