package br.cs.fsilva.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static br.cs.fsilva.core.DriverFactory.getDriver;

// aqui ficarão os métodos a serem usados pela classe de teste
public class DSL {

  /********** TextField e TextArea ****************/

  public void escrever(By by, String texto) {
    getDriver().findElement(by).clear();
    getDriver().findElement(by).sendKeys(texto);
  }

  public void escreveNoCampo(String id_campo, String texto) {
    escrever(By.id(id_campo), texto);
  }

//  public void escrever(String id_campo, String texto){
//    escrever(By.id(id_campo), texto);
//  }

  public String obterValorCampo(String id_campo) {
    return getDriver().findElement(By.id(id_campo)).getAttribute("value");
  }

  /********** Radio e Check ****************/

  public void clicarRadioEcheckbox(By by) {
    getDriver().findElement(by).click();
  }

  public void clicarRadioEcheckbox(String id_campo) {
    clicarRadioEcheckbox(By.id(id_campo));
  }

  public boolean checandoRadioEcheckboxMarcado(String id_campo) {
    return getDriver().findElement(By.id(id_campo)).isSelected();
  }

  /****** Dropdown ******/

  public void selecionarCombo(String id, String valor) {
    // 1� encontre o emelento e guarde em uma vari�vel
    WebElement element = getDriver().findElement(By.id(id));
    // use o select para manipular o dropdown
    Select combo = new Select(element);
    // forma de selecionar as opções do dropdown
    // combo.selectByIndex(2);
    //combo.selectByValue("superior");
    combo.selectByVisibleText(valor);
  }

  public void deselecionarCombo(String id, String valor) {
    WebElement element = getDriver().findElement(By.id(id));
    Select combo = new Select(element);
    combo.deselectByVisibleText(valor);
  }

  public String obterValorCombo(String id) {
    WebElement element = getDriver().findElement(By.id(id));
    Select combo = new Select(element);
    return combo.getFirstSelectedOption().getText();
  }

  public List<String> obterValoresCombo(String id) {
    WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
    Select combo = new Select(element);
    List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
    List<String> valores = new ArrayList<String>();
    for (WebElement opcao : allSelectedOptions) {
      valores.add(opcao.getText());
    }
    return valores;
  }

  public int obterQuantidadeOpcoesCombo(String id) {
    WebElement element = getDriver().findElement(By.id(id));
    Select combo = new Select(element);
    List<WebElement> options = combo.getOptions();
    return options.size();
  }

  public boolean verificarOpcaoCombo(String id, String opcao) {
    WebElement element = getDriver().findElement(By.id(id));
    Select combo = new Select(element);
    List<WebElement> options = combo.getOptions();
    for (WebElement option : options) {
      if (option.getText().equals(opcao)) {
        return true;
      }
    }
    return false;
  }

  public void selecionarComboPrime(String radical, String valor) {
    clicarRadioEcheckbox(By.xpath("//*[@id='"+radical+"']/../..//span"));
    clicarRadioEcheckbox(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
  }

  /****** Botão ******/

  public void clicarBotao(String id) {
    getDriver().findElement(By.id(id)).click();
  }

  public String obterValueElemento(String id) {
    return getDriver().findElement(By.id(id)).getAttribute("value");
  }

  /****** Link ******/
  public void clicarLink(String link) {
    getDriver().findElement(By.linkText(link)).click();
  }

  /****** Textos ******/

  public String obterTexto(By by) {
    return getDriver().findElement(by).getText();
  }

  // esse metodo esta usando o método acima
  public String obterTexto(String id) {
    return obterTexto(By.id(id));
  }

  /********** Alertas **********/

  public String alertaObterTexto() {
    Alert alert = getDriver().switchTo().alert();
    return alert.getText();
  }

  public String focoAlertaPegaTextoEAceita() {
    Alert alert = getDriver().switchTo().alert();
    String valor = alert.getText();
    alert.accept();
    return valor;
  }

  public String alertaObterTextoENega() {
    Alert alert = getDriver().switchTo().alert();
    String valor = alert.getText();
    alert.dismiss();
    return valor;

  }

  public void alertaEscrever(String valor) {
    Alert alert = getDriver().switchTo().alert();
    alert.sendKeys(valor);
    alert.accept();
  }

  /********* Frames e Janelas ************/

  public void entrarFrame(String id) {
    getDriver().switchTo().frame(id);
  }

  public void sairFrame() {
    getDriver().switchTo().defaultContent();
  }

  public void trocarJanela(String id) {
    getDriver().switchTo().window(id);
  }

  /******************** JS *******************/
  public Object executarJS(String cmd, Object... param) {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    return js.executeScript(cmd, param);
  }

  /******************** metodos para percorrer tabela******************/
  public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
    //procurar coluna do registro
    WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
    int idColuna = obterIndiceColuna(colunaBusca, tabela);

    //encontrar a linha do registro
    int idLinha = obterIndiceLinha(valor, tabela, idColuna);

    //procurar coluna do botao
    int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

    //clicar no botao da celula encontrada
    WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
    celula.findElement(By.xpath(".//input")).click();

  }

  protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
    List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
    int idLinha = -1;
    for(int i = 0; i < linhas.size(); i++) {
      if(linhas.get(i).getText().equals(valor)) {
        idLinha = i+1;
        break;
      }
    }
    return idLinha;
  }

  protected int obterIndiceColuna(String coluna, WebElement tabela) {
    List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
    int idColuna = -1;
    for(int i = 0; i < colunas.size(); i++) {
      if(colunas.get(i).getText().equals(coluna)) {
        idColuna = i+1;
        break;
      }
    }
    return idColuna;
  }


}







