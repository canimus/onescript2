/**
 * Author: Herminio Vazquez
 * Date: January 25, 2017
 * Project: ING Mortgages
 * Company: http://iovio.com
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NSRidePlanPO {
  WebDriver driver;
  WebDriverWait wait;

  @FindAll({
      @FindBy(xpath = "//ol[@class='rp-reismogelijkhedenList']/li")
  })
  List<WebElement> resultList;


  public NSRidePlanPO(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(this.driver, 2);
    this.wait.until(ExpectedConditions.visibilityOfAllElements(resultList));
  }
}
