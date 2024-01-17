package com.univr.pump.insulinpump.system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatisticsPage extends PageObject{

    @FindBy(xpath = "/html/body/div/div/div[1]\n")
    private WebElement statisticsTitle;

    @FindBy(xpath = "//*[@id=\"start\"]")
    private WebElement startDate;

    @FindBy(xpath = "//*[@id=\"end\"]")
    private WebElement endDate;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[3]/button")
    private WebElement showCharts;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[4]/button")
    private WebElement clearCharts;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[5]/a")
    private WebElement backHome;

    @FindBy(xpath = "//*[@id=\"SvgjsPath1563\"]")
    private WebElement chartLine;



    public StatisticsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){
        return this.statisticsTitle.getText();
    }

    public HomePage backHome(){
        this.backHome.click();
        return new HomePage(driver);
    }

}
