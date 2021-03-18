package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    //driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    dsl = new DSL(driverChrome);
  }

  @After
  public void inicializaDepoisDosTestes(){
    driverChrome.quit();
  }


  @Test
  public  void testAjax(){
    driverChrome.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=1cb34");
    dsl.escreveNoCampo("j_idt302:name", "Teste");
    dsl.clicarBotao("j_idt302:j_idt306");
    WebDriverWait wait = new WebDriverWait(driverChrome, 30);
    //wait.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "Teste")); // aqui o teste passa quando o texto que foi escrito aparece na tela
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt345_start"))); // aqui o teste passa quando a imagem de carregamento some da tela
    Assert.assertEquals("Teste", dsl.obterTexto("j_idt302:display"));
  }
}
