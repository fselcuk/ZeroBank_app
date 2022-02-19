package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryStepDefs {
    AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
    @And("the page should have following account types")
    public void the_page_should_have_following_account_types(List<String> dataTable) {

        for (String option : dataTable) {
            //System.out.println(accountSummaryPage.optionsOfAccountSummary(option).getText());
            Assert.assertEquals("option is expected list comes feature file, the second one is actual list from webpage",option,accountSummaryPage.optionsOfAccountSummary(option).getText());
        }


    }

    @Then("{string} table must have following columns")
    public void table_must_have_following_columns(String option,List<String> dataTable) {

        for (int i = 1; i < dataTable.size()+1; i++) {

            Assert.assertEquals(dataTable.get(i-1),accountSummaryPage.columnsOfCreditAccounts(option,i).getText());

        }

    }

}
