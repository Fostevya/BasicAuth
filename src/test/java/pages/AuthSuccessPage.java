package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthSuccessPage {

    @FindBy(xpath = "//h1[contains(concat(' ',@class,' '),'title')]")
    private WebElement title;

    public AuthSuccessPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public Boolean titleIsAuthSuccess() {
        String titleText = title.getText();
        return titleText.equals("Auth Success");
    }
}
