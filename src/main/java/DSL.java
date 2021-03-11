import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {
  private WebDriver driver;

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

  public void clicarRadioButton(String id_campo){
    driver.findElement(By.id(id_campo)).click();
  }

  public boolean checandoRadioButtonMarcado(String id_campo){
    return driver.findElement(By.id(id_campo)).isSelected();
  }


}
