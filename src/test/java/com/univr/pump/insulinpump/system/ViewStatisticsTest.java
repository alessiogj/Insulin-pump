package com.univr.pump.insulinpump.system;
import static org.junit.Assert.*;
import org.junit.Test;


public class ViewStatisticsTest extends BaseTest{
    @Test
    public void testStatisticsCharts(){
        driver.get("http://localhost:8080/");
        HomePage page = new HomePage(driver);
        StatisticsPage stats = page.showStatistics();
        assertEquals("Should be in statistics page", "Charts by Date Intervals", stats.getTitle());
        //TODO assert del grafico per verificare charts by date, all e clear charts
        page = stats.backHome();
        assertEquals("Should be in home page", "Last measurements", page.getTitle());
    }
}