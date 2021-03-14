import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DSL;

public class TesteAlert {

  // variável global
  private WebDriver driverChrome;
  private DSL dsl;

  @Before
  public void inicializaAntesDosTestes(){
    // configurando navegado
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    //driverChrome.get("https://teste-git-main-fraancilene.vercel.app/");
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    dsl = new DSL(driverChrome);
  }

  // metodo que será chamado depois de cada teste
  @After
  public void inicializaDepoisDosTestes(){
    driverChrome.quit();
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
    driverChrome.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);

  }

  @Test
  public void interagirComAlertConfirm() {

    driverChrome.findElement(By.id("confirm")).click();
    Alert alerta = driverChrome.switchTo().alert();
    Assert.assertEquals("Confirm Simples", alerta.getText());
    alerta.accept();
    Assert.assertEquals("Confirmado", alerta.getText());
    alerta.accept();


    driverChrome.findElement(By.id("confirm")).click();
    alerta = driverChrome.switchTo().alert();
    Assert.assertEquals("Confirm Simples", alerta.getText());
    alerta.dismiss(); //negando o alerta
    Assert.assertEquals("Negado", alerta.getText());
    alerta.dismiss();


  }

  @Test
  public void interagirComAlertaPrompt() {

    driverChrome.findElement(By.id("prompt")).click();
    Alert alert = driverChrome.switchTo().alert();
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
