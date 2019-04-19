package com.qa.hs.keyword.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public Properties prop;

    public  WebDriver init_Driver(String browser_Name){

        if(browser_Name.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Bhavna PC\\Documents\\IdeaProjects\\chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            //options.addArguments();
            driver = new ChromeDriver(options);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        }
         driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS) ;
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        return driver;
    }
    public Properties  init_Properties(){
        prop = new Properties();
        try{
        FileInputStream fis = new FileInputStream("C:\\Users\\Bhavna PC\\IdeaProjects\\KeywordDrivenProject\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
         prop.load(fis);


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return prop;
        }




}
