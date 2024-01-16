package com.univr.pump.insulinpump.system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends PageObject {

    @FindBy(xpath = "/html/body/div/div[1]/div[2]/div[9]/a[1]")
    private WebElement buttonStatistics;

    @FindBy(xpath = "//*[@id=\"buttonModalBattery\"]")
    private WebElement buttonBattery;

    @FindBy(xpath = "//*[@id=\"buttonConfirmBattery\"]")
    private WebElement buttonConfirmBattery;

    @FindBy(xpath = "//*[@id=\"buttonCancelBattery\"]")
    private WebElement buttoncancelBattery;

    @FindBy(xpath = "//*[@id=\"buttonModalInsulin\"]")
    private WebElement buttonInsulin;

    @FindBy(xpath = "//*[@id=\"buttonConfirmInsulin\"]")
    private WebElement buttonConfirmInsulin;

    @FindBy(xpath = "//*[@id=\"buttonCancelInsulin\"]")
    private WebElement buttonCancelInsulin;

    @FindBy(xpath = "//*[@id=\"batterylabel\"]")
    private WebElement batteryLevelLabel;

    @FindBy(xpath = "//*[@id=\"refilllabel\"]")
    private WebElement insulinLevelLabel;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StatisticsPage showStatistics() {
        this.buttonStatistics.click();
        return new StatisticsPage(driver);
    }

    public String replaceBattery() {
        this.buttonBattery.click();
        this.buttonConfirmBattery.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.batteryLevelLabel.getText();
    }

    public String replaceInsulin(){
        this.buttonInsulin.click();
        this.buttonConfirmInsulin.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.insulinLevelLabel.getText();
    }

}
