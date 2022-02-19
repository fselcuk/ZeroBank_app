package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//li/button[@id='signin_button']")
    public WebElement signInButton;

    @FindBy(xpath = "//div/input[@id='user_login']")
    public WebElement logInBox;

    @FindBy(xpath = "//div/input[@id='user_password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//div/input[@name='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//form/div[@class='alert alert-error")
    public WebElement alertForWrongCredentials;


    public void login(String UN, String Pass){
        logInBox.sendKeys(UN);
        passwordBox.sendKeys(Pass);
        submitButton.click();
    }

}
