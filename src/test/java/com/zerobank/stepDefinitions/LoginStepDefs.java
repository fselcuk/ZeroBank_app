package com.zerobank.stepDefinitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class LoginStepDefs {
LoginPage loginpage=new LoginPage();
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

        Driver.get().get(ConfigurationReader.get("url"));
        loginpage.signInButton.click();


    }

    @Given("User logins with username {string} and password {string}")
    public void user_logins_with_username_and_password(String userName, String password) {
        loginpage.login(userName,password);
        BrowserUtils.waitFor(5);
        Driver.get().navigate().back();
        BrowserUtils.waitFor(5);
    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String accountPage) {
        Driver.get().findElement(By.xpath("//div/strong[.=\"Online Banking\"]")).click();
        Driver.get().findElement(By.id("account_summary_link")).click();
        BrowserUtils.waitFor(3);
        Assert.assertTrue("title is not matching",Driver.get().getTitle().contains(accountPage));
    }


}
