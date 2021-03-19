package br.cs.fsilva.core;

import com.sun.deploy.cache.Cache;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

public class BaseTest {

  // a partir daqui vai tirar o screenchots antes de fechar o browser
  @Rule
  public TestName testName= new TestName();

  // metodo que será chamado depois de cada teste
    @After
    public void FinalizaBrowser() throws IOException {
        // para tirar screnchot da tela
        TakesScreenshot ss  = (TakesScreenshot) getDriver();
       File arquivo = ss.getScreenshotAs(OutputType.FILE);
       Cache.copyFile(arquivo, new File("target" +File.separator+ "screenshots"
               + File.separator +  testName.getMethodName() + "screenshot.jpg"));


       // aqui vai fechar o browser
        if (Propriedades.FECHAR_BROWSER){
            killDriver();
        }

    }
}
