import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// aqui ficarão os métodos a serem usados pela classe de teste
public class DSL {
  public WebDriver driver;

  // construtor
  public DSL(WebDriver driver){
    this.driver = driver;
  }

  public void escreveNoCampo(String id_campo, String texto){
    driver.findElement(By.id(id_campo)).sendKeys(texto);
  }

  public String obterValorCampo(String id_campo){
    return driver.findElement(By.id(id_campo)).getAttribute("value");
  }

  public void clicarRadioEcheckbox(String id_campo){
    driver.findElement(By.id(id_campo)).click();
  }

  public boolean checandoRadioEcheckboxMarcado(String id_campo){
    return driver.findElement(By.id(id_campo)).isSelected();
  }

  public void selecionarCombo(String id, String valor){
    // 1� encontre o emelento e guarde em uma vari�vel
    WebElement element = driver.findElement(By.id(id));
    // use o select para manipular o dropdown
    Select combo = new Select(element);
    // forma de selecionar as opções do dropdown
    // combo.selectByIndex(2);
    //combo.selectByValue("superior");
    combo.selectByVisibleText(valor);
  }

  public String obterValorCombo(String id){
    WebElement element = driver.findElement(By.id(id));
    Select combo = new Select(element);
    return combo.getFirstSelectedOption().getText();
  }

  public void clicarBotao(String id){
    driver.findElement(By.id(id)).click();
  }

  public void clicarLink(String link){
    driver.findElement(By.linkText(link)).click();
  }

  public String obterTexto(By by){
    return driver.findElement(by).getText();
  }

  // esse metodo esta usando o método acima
  public String obterTexto(String id){
    return obterTexto(By.id(id));
  }

  public String darFocoNoAlertEpegaTexto(){
    return  driver.switchTo().alert().getText();

  }





}
