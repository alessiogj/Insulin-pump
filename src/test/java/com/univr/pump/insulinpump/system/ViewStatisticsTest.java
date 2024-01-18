package com.univr.pump.insulinpump.system;
import static org.junit.Assert.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class ViewStatisticsTest extends BaseTest{

    @Test
    public void testChartUpdatesWithDateInput() {
        // Accedi alla pagina e naviga alla sezione di statistiche
        driver.get("http://localhost:8080/");
        HomePage page = new HomePage(driver);
        page.waitForCharts();
        StatisticsPage stats = page.showStatistics();
        assertEquals("Should be in statistics page", "Charts by Date Intervals", stats.getTitle());

        int[] initialDataCount = stats.getDataPoints();

        String currentDate = new SimpleDateFormat("yyyy-dd-MM").format(new Date());
        System.out.println(currentDate);
        int[] updatedDataCount = stats.InsertDate(currentDate, currentDate);

        // Confronta i due stati per verificare che il grafico sia stato aggiornato
        assertNotEquals("data points should be different", Arrays.toString(initialDataCount), Arrays.toString(updatedDataCount));

        int[] clearedChart = stats.clearCharts();

        assertArrayEquals("data points should be zero", new int[]{0, 0, 0, 0}, clearedChart);

        // Verifica il titolo della homepage dopo il ritorno
        page = stats.backHome();
        assertEquals("Should be in home page", "Last measurements", page.getTitle());
    }
    @Test
    public void testChartUpdatesAll(){
        // Accedi alla pagina e naviga alla sezione di statistiche
        driver.get("http://localhost:8080/");
        HomePage page = new HomePage(driver);
        page.waitForCharts();
        StatisticsPage stats = page.showStatistics();
        assertEquals("Should be in statistics page", "Charts by Date Intervals", stats.getTitle());

        int[] initialDataCount = stats.getDataPoints();

        int[] updatedDataCount = stats.getChartsAll();

        // Confronta i due stati per verificare che il grafico sia stato aggiornato
        assertNotEquals("data points should be different", Arrays.toString(initialDataCount), Arrays.toString(updatedDataCount));

        int[] clearedChart = stats.clearCharts();

        assertArrayEquals("data points should be zero", new int[]{0,0,0,0}, clearedChart);

        // Verifica il titolo della homepage dopo il ritorno
        page = stats.backHome();
        assertEquals("Should be in home page", "Last measurements", page.getTitle());
    }
}