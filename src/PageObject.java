import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class PageObject {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public PageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(this.driver, 5);
    PageFactory.initElements(driver, this);
  }

  public PageObject(WebDriver driver, String url) {
    this(driver);
    driver.navigate().to(url);
  }

  public WebElement filterDropdown(List<WebElement> collection, String item) {
    return collection
        .stream()
        .filter(e -> e.getText().contains(item))  // Filters list of items by text
        .collect(Collectors.toList())  // Returns a list of matched items
        .get(0); // Gets the first item of the matching list or
    // Throws ArrayIndexOutOfBounds exception when no match
  }
}
