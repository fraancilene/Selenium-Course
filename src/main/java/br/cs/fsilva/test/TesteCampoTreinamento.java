package br.cs.fsilva.test;

import br.cs.fsilva.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

public class TesteCampoTreinamento {

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
  public void testeTextField() {

    // quando o projeto que esta sendo testado esta na sua maquina, assim podera ser executado em qualquer maquina
    //driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    //System.getProperty("user.dir"); // propriedade retorna o local exato onde o java esta rodando

    // identificar o campo e o que quero fazer com esse elemento, nesse caso, inserir um texto
    dsl.escreveNoCampo("elementosForm:nome","Teste de escrita");
    Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome")); //  verificando se o campo esta preenchendo
  }

  @Test
  public void InteracaoComTextArea() {

    //  localizar elemento e o que quero fazer com esse elemento
    dsl.escreveNoCampo("elementosForm:sugestoes", "Teste");
    //  verificando se o campo esta preenchendo
    Assert.assertEquals("Teste", dsl.obterValorCampo("elementosForm:sugestoes"));

  }

  @Test
  public void InteragirComRadioButton() {

    // localizar elemento e o que quero fazer com esse elemento
    dsl.clicarRadioEcheckbox("elementosForm:sexo:0");
    // verificando se o elemento foi clicado
    Assert.assertTrue(dsl.checandoRadioEcheckboxMarcado("elementosForm:sexo:0"));

  }


  @Test
  public void InteragirComCheckBox() {
    dsl.clicarRadioEcheckbox("elementosForm:comidaFavorita:0");
    dsl.checandoRadioEcheckboxMarcado("elementosForm:comidaFavorita:0");
  }

  @Test
  public void interagirComCombo() {
    dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
    Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
  }

  @Test
  public void DeveInteragirComCombo() {

    dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
    Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));

    // 1� encontre o emelento e guarde em uma vari�vel
    WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
    // use o select para manipular o dropdown
    Select combo = new Select(element);
    // retornando uma lista de todas as op��es do webElemnt
    List<WebElement> options = combo.getOptions();
    // verifica��es
    Assert.assertEquals(8, options.size()); // quantidade de op��es que o dropdown possui

    // checando que alguma possivel opcaoo esta disponivel no combo
    boolean encontrou = false;
    for (WebElement option : options) {
      if (option.getText().equals("Mestrado")) {
        encontrou = true;
        break;
      }
    }
    Assert.assertTrue(encontrou);
  }

 @Test
  public void deveVerificarValoresCombo(){
    Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
    Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));

  }

  @Test
  public void verificaValoresDoComboMultiplo() {

    dsl.selecionarCombo("elementosForm:esportes", "Natacao");
    dsl.selecionarCombo("elementosForm:esportes", "Corrida");
    dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
    List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
    Assert.assertEquals(3, opcoesMarcadas.size());

    dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
    opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
    Assert.assertEquals(2, opcoesMarcadas.size());
    Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
  }


  @Test
  public void interagirComBotoes() {

    dsl.clicarBotao("buttonsimple");
    Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));

  }

  @Test
  public void interagirComLink() {
    dsl.clicarLink("Voltar");
    Assert.assertEquals("Voltou!", dsl.obterTexto("Resultado"));
    //Assert.fail(); // para testes incompletos

  }

  @Test
  public void deveBuscarTextosNaPagina() {

    /* //imprimindo todo o texto da tag body
    //System.out.println(driverChrome.findElement(By.tagName("body")).getText());
    // verificando se existe o titulo do texto titulo do texto/ mas essa não é a melhor forma
    Assert.assertTrue(driverChrome.findElement(By.tagName("body"))
            .getText().contains("Campo de Treinamento"));
  */
    Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
    Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
  }

  @Test
  public void testTextFieldDuplo(){
    dsl.escreveNoCampo("elementosForm:nome", "Francilene");
    Assert.assertEquals("Francilene", dsl.obterValorCampo("elementosForm:nome"));dsl.escreveNoCampo("elementosForm:nome", "Francilene");

    dsl.escreveNoCampo("elementosForm:nome", "Silva");
    Assert.assertEquals("Silva", dsl.obterValorCampo("elementosForm:nome"));

  }


  @Test
  public void testJavascript(){
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    //js.executeScript("alert('Testando js via Selenium')");
    js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
    js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

    WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
    js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
  }

  // teste que vai usar xpath
  @Test
  public void deveClicarBotaoTabela(){
      dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
  }

}


