package com.qa.hs.keyword.engine;

import com.qa.hs.keyword.base.TestBase;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Engine {
    public WebDriver driver;
    public Properties prop;
    public TestBase base;
    public WebElement element;

    XSSFWorkbook book;
    //XSSFSheet sheet;


    public final String Scenario_path_sheet = "C:\\Users\\Bhavna PC\\IdeaProjects\\KeywordDrivenProject\\"
            + "src\\main\\java\\com\\qa\\hs\\keyword\\Scenarios\\hubspotScenarios.xlsx";


    public void startExecution(String sheetName) throws IOException {

        String findBy = null;
        String Locator = null;
        String action = null;
        String value = null;

        FileInputStream file = null;

        file = new FileInputStream(Scenario_path_sheet);

        book = new XSSFWorkbook(file);

        Sheet sheet = book.getSheet(sheetName);

        int k = 0;

        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            findBy = sheet.getRow(i + 1).getCell(k + 1).toString();

            if (!findBy.equalsIgnoreCase("NA")) {

                Locator = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
            }

            action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
            value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();


            switch (action) {

                case "open browser":

                    base = new TestBase();
                    prop = base.init_Properties();

                    if (value.equals("NA")) {

                        base.init_Driver(prop.getProperty("browser"));
                    }
                    driver = base.init_Driver(value);


                    break;

                case "enter url":
                    if (value.equals("NA")) {
                        driver.get(prop.getProperty("url"));
                    } else

                        driver.get(value);


                    break;

                case "quit":
                    driver.quit();

            }
                    switch (findBy) {

                        case "id":

                            element = driver.findElement(By.id(Locator));
                            if (action.equalsIgnoreCase("Sendkeys")) {
                                element.sendKeys(value);
                            }
                            if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }

                            break;

                        case "xpath":

                            element = driver.findElement(By.xpath(Locator));
                            if (action.equalsIgnoreCase("Sendkeys")) {
                                element.sendKeys(value);
                            }
                            if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }


                    }


            }
        }
}

