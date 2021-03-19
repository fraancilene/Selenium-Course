package br.cs.fsilva.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory(){}

    public static WebDriver getDriver(){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\geckodriver.exe");
        System.setProperty("webdriver.ie.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\IEDriverServer.exe");

        if (driver == null){
            switch (Propriedades.browser){
                case CHROME: driver = new ChromeDriver(); break;
                case FIREFOX: driver = new FirefoxDriver(); break;
                case IE_EXPLORER: driver = new InternetExplorerDriver(); break;
            }
            driver.manage().window().setSize(new Dimension(1200, 765));
        }
        return driver;
    }

    public static void killDriver(){

        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }












}