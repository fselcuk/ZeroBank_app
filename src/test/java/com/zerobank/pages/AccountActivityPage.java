package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountActivityPage extends BasePage {


    @FindBy(xpath = "//div/select[@id=\"aa_accountId\"]")
    public WebElement dropDown;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/thead[1]/tr[1]/th")
    public List<WebElement> transactionTableColumns;
}
