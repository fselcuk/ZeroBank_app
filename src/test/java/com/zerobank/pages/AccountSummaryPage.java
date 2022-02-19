package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountSummaryPage extends BasePage {
    public WebElement a;

    public WebElement optionsOfAccountSummary(String option) {
        return Driver.get().findElement(By.xpath("//div/h2[.=\"" + option + "\"]"));  //div/h2[.="Cash Accounts"]
    }

    public WebElement columnsOfCreditAccounts(String option, int columnNum) {
        if (option.equals("Credit Accounts")) {

            a = Driver.get().findElement(By.xpath("//div[3]//div[1]//table[1]//thead[1]/tr/th[" + columnNum + "]"));
        }
        return a;
    }
}

