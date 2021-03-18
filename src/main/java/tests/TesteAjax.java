package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DSL;

public class TesteAjax {

  private WebDriver driverChrome;
  private DSL dsl;


  @Before
  public void inicializaAntesDosTestes(){
    // configurando navegado
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    dsl = new DSL(driverChrome);
  }

  @After
  public void inicializaDepoisDosTestes(){
    driverChrome.quit();
  }


  @Test
  public  void testAjax(){
  dsl.escreveNoCampo("j_idt302:name", "Teste");
  }
}
