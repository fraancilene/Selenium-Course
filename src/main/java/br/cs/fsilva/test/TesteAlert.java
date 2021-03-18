package br.cs.fsilva.test;

import br.cs.fsilva.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

public class TesteAlert {

  // variável

  private DSL dsl;

  @Before
  public void inicializaAntesDosTestes(){
    getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    dsl = new DSL();
  }

  // metodo que será chamado depois de cada teste
  @After
  public void inicializaDepoisDosTestes(){
    killDriver();
  }


  @Test
  public void interagirComAlertSimples() {
    // manipulando um alert externo
    dsl.clicarBotao("alert");

    // pedindo para o selenium alterar o foco para o alerta para manipulá-lo
    String textoAlerta = dsl.focoAlertaPegaTextoEAceita();

    // verificações
    Assert.assertEquals("Alert Simples", textoAlerta);
   // alert.accept(); // aceitando o alerta para fechá-lo

    // escrevendo o texto do alerta no campo nome
    getDriver().findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);

  }

  @Test
  public void interagirComAlertConfirm() {

    getDriver().findElement(By.id("confirm")).click();
    Alert alerta = getDriver().switchTo().alert();
    Assert.assertEquals("Confirm Simples", alerta.getText());
    alerta.accept();
    Assert.assertEquals("Confirmado", alerta.getText());
    alerta.accept();


    getDriver().findElement(By.id("confirm")).click();
    alerta = getDriver().switchTo().alert();
    Assert.assertEquals("Confirm Simples", alerta.getText());
    alerta.dismiss(); //negando o alerta
    Assert.assertEquals("Negado", alerta.getText());
    alerta.dismiss();


  }

  @Test
  public void interagirComAlertaPrompt() {

    getDriver().findElement(By.id("prompt")).click();
    Alert alert = getDriver().switchTo().alert();
    Assert.assertEquals("Digite um numero", alert.getText());

    // escrevendo no alerta do prompt
    alert.sendKeys("12");
    alert.accept();

    Assert.assertEquals("Era 12?", alert.getText());
    alert.accept();
    Assert.assertEquals(":D", alert.getText());
    alert.accept();

  }

}
