package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityStepDefs {
    AccountActivityPage activityPage=new AccountActivityPage();

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
     activityPage.navigateToModule(string).click();
        BrowserUtils.waitFor(3);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String string) {
        Assert.assertEquals("title should match but yours not matching",string, Driver.get().getTitle());
    }

    @Then("In the account drop down default option should be {string}")
    public void in_the_account_drop_down_default_option_should_be(String string) {

        String firstOption = new Select(activityPage.dropDown).getFirstSelectedOption().getText();
        Assert.assertEquals("first Option should be Savings",string,firstOption);

    }

    @Then("Account drop down should have following options")
    public void account_drop_down_should_have_following_options(List<String> expectedOptions) {
        List<WebElement> actualOptions = new Select(activityPage.dropDown).getOptions();
        Assert.assertEquals("OPTIONS don't match",expectedOptions,BrowserUtils.getElementsText(actualOptions));
    }

    @Then("Transactions table should have following column names")
    public void transactions_table_should_have_following_column_names(List<String> expectedColumnNames) {
        List<String> actualColumnNames = BrowserUtils.getElementsText(activityPage.transactionTableColumns);
        Assert.assertEquals("COLUMNS don't match",expectedColumnNames,actualColumnNames);


    }





}
