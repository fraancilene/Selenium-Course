package br.cs.fsilva.test;

import br.cs.fsilva.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

public class TesteAjax {

  private DSL dsl;


  @Before
  public void inicializaAntesDosTestes(){
    getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=1cb34");
    dsl = new DSL();
  }

  @After
  public void inicializaDepoisDosTestes(){
    killDriver();
  }


  @Test
  public  void testAjax(){
    getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=1cb34");
    dsl.escreveNoCampo("j_idt302:name", "Teste");
    dsl.clicarBotao("j_idt302:j_idt306");
    WebDriverWait wait = new WebDriverWait(getDriver(), 30);
    //wait.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "Teste")); // aqui o teste passa quando o texto que foi escrito aparece na tela
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt345_start"))); // aqui o teste passa quando a imagem de carregamento some da tela
    Assert.assertEquals("Teste", dsl.obterTexto("j_idt302:display"));
  }
}
