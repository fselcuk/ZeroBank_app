package com.zerobank.stepDefinitions;

//import com.interviewTask.utilities.DBUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("\tthis is coming from BEFORE");
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();

    }

//    @Before("@db")
//    public void setUpdb(){
//        System.out.println("\tconnecting to database...");
//        DBUtils.createConnection();
//    }
//
//    @After("@db")
//    public void closeDb(){
//        System.out.println("\tdisconnecting to database...");
//        DBUtils.destroy();
//    }



}
