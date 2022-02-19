package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
          "html:target/cucumber-report.html",
          "json:target/cucumber.json",
          "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features", //copy path from content root --> feature's path
        glue = "com/zerobank/stepDefinitions", //path from source root-->step definition's path
        dryRun = false,
        tags = "@wip"  //--> ctrl alt L hizalama yapiyor
)
public class CukesRunner {

}
