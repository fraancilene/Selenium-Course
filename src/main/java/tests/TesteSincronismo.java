package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DSL;

import java.util.concurrent.TimeUnit;

public class TesteSincronismo {
  private WebDriver driverChrome;
  private DSL dsl;

  // metodo que será chamado antes de qualquer teste
  @Before
  public void inicializaAntesDosTestes() {
    // configurando navegado
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    // toda interação que for feita, já vão estar com essa espera configurada.
    //driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // resolvendo o problema de sincronismo com espera implicita
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    dsl = new DSL(driverChrome);
  }

  // metodo que será chamado depois de cada teste
  @After
  public void inicializaDepoisDosTestes() {
    //driverChrome.quit();
  }

  @Test
  public void deveUtilizarEsperaFixa() throws InterruptedException {
    dsl.clicarBotao("buttonDelay");
    Thread.sleep(5000); // resolvendo o problema de sincronismo com espera fixa
    dsl.escreveNoCampo("novoCampo", "Deu Certo");
  }

  @Test
  public void deveUtilizarEsperaImplicita() throws InterruptedException {
    driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // resolvendo o problema de sincronismo com espera implicita
    dsl.clicarBotao("buttonDelay");
    dsl.escreveNoCampo("novoCampo", "Deu Certo");
    driverChrome.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // retirando a espera

  }

  @Test
  public void deveUtilizarEsperaExplicita() throws InterruptedException {
    dsl.clicarBotao("buttonDelay");
    WebDriverWait wait = new WebDriverWait(driverChrome, 30);// o browser vai parar e esperar  por até 30 segundos
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo"))); // e informando o que ele deve esperar
    dsl.escreveNoCampo("novoCampo", "Deu Certo");
  }

  /** Qual a melhor estratégia para sincronismo*:
   * espera fixa? não
   * espera implicita? deixa implementação mais simples, alguns testes tendem a ficar mais lentos
   * espera explicita? nos da mais controle, de poder definir uma espera mais específica
   *
   * não é uma boa opção usar as duas formas juntas.
   *
   *  a recomendação é utilizar a  explicita - mais controle, testes mais rápidos, etc.
   *
   *
   */

}
