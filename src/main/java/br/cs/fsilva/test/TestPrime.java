package br.cs.fsilva.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import br.cs.fsilva.core.DSL;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

// Com Xpath
public class TestPrime {

    private DSL dsl;

    @Before
    public void inicializaAntesDosTestes(){
      dsl = new DSL();
    }

    // metodo que será chamado depois de cada teste
    @After
    public void inicializaDepoisDosTestes() {
        killDriver();
    }

  @Test
  public void deveInteragirComRadioPrime(){
    getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=4f0dc");
     dsl.clicarRadioEcheckbox(By.xpath("//input[@id=\"j_idt303:console:0\"]/../..//span"));
    Assert.assertTrue(dsl.checandoRadioEcheckboxMarcado("j_idt303:console:0"));

    dsl.clicarRadioEcheckbox(By.xpath("//label[.='Option2']/..//span"));
    Assert.assertTrue(dsl.checandoRadioEcheckboxMarcado("j_idt303:console:1"));
  }

  @Test
  public void deveInteragirComSelectPrime(){
    getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=5c56c");

    dsl.selecionarComboPrime("j_idt302:option", "Option1");
   // dsl.clicarRadioEcheckbox(By.xpath("//*[@id='j_idt302:option']/../..//span"));
   // dsl.clicarRadioEcheckbox(By.xpath("//*[@id='j_idt302:option_items']//li[.='Option2']"));

    Assert.assertEquals("Option1", dsl.obterTexto("j_idt302:option_label"));
  }
}
