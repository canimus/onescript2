import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NSRidePlanPO extends PageObject {

  @FindAll({
      @FindBy(xpath = "//ol[@class='rp-reismogelijkhedenList']/li")
  })
  List<WebElement> resultList;


  public NSRidePlanPO(WebDriver driver) {
    super(driver);
    this.wait.until(ExpectedConditions.visibilityOfAllElements(resultList));
  }
}
