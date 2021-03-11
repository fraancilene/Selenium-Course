import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteAlert {

  @Test
  public void interagirComAlertSimples() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // manipulando um alert externo
    driverChrome.findElement(By.id("alert")).click();

    // pedindo para o selenium alterar o foco para o alerta para manipulá-lo
    Alert alert = driverChrome.switchTo().alert();
    String textoAlerta = alert.getText(); //capturando o texto do alerta

    // verificações
    Assert.assertEquals("Alert Simples", alert.getText());
    alert.accept(); // aceitando o alerta para fechá-lo

    // escrevendo o texto do alerta no campo nome
    driverChrome.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);

    driverChrome.quit();
  }

  @Test
  public void interagirComAlertConfirm() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

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

    driverChrome.quit();

  }

  @Test
  public void interagirComAlertaPrompt() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

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

    driverChrome.quit();

  }





}
