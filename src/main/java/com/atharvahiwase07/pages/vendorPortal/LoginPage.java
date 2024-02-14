package com.atharvahiwase07.pages.vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.atharvahiwase07.pages.AbstractPage;

public class LoginPage extends AbstractPage {
    
    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement passWord;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.userName));
        return this.userName.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void login(String username, String password) {
        this.userName.sendKeys(username);
        this.passWord.sendKeys(password);
        this.loginButton.click();
    }  
}
