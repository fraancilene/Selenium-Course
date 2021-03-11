import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

  @Test
  public void testeTextField() {
    // config navegador
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));

    // quando o projeto que est� sendo testado est� na sua m�quina, assim poder� ser executado em qualquer m�quina
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    //System.getProperty("user.dir"); // propriedade retorna o local exato onde o java esta rodando

    // 1� identificar o campo e o que quero fazer com esse elemento, nesse caso, inserir um texto
    driverChrome.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

    // 2� verificando se o campo esta preenchendo
    Assert.assertEquals("Teste de escrita", driverChrome.findElement(By.id("elementosForm:nome")).getAttribute("value"));

    driverChrome.quit();

  }

  @Test
  public void InteracaoComTextArea() {
    // config navegador
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // 1� localizar elemento e o que quero fazer com esse elemento
    driverChrome.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");

    // 2� verificando se o campo esta preenchendo
    Assert.assertEquals("Teste", driverChrome.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

    driverChrome.quit();

  }

  @Test
  public void InteragirComRadioButton() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // 1� localizar elemento e o que quero fazer com esse elemento
    driverChrome.findElement(By.id("elementosForm:sexo:0")).click();

    // verificando se o elemento foi clicado
    Assert.assertTrue(driverChrome.findElement(By.id("elementosForm:sexo:0")).isSelected());

    driverChrome.quit();
  }


  @Test
  public void InteragirComCheckBox() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // 1� localizar elemento e o que quero fazer com esse elemento
    driverChrome.findElement(By.id("elementosForm:comidaFavorita:0")).click();

    // verificando se o elemento foi clicado
    Assert.assertTrue(driverChrome.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());

    driverChrome.quit();

  }

  @Test
  public void interagirComCombo() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // 1� encontre o emelento e guarde em uma vari�vel
    WebElement element = driverChrome.findElement(By.id("elementosForm:escolaridade"));

    // use o select para manipular o dropdown
    Select combo = new Select(element);

    // forma de selecionar as op��es do dropdown
    //combo.selectByIndex(2);
    //combo.selectByValue("superior");
    combo.selectByVisibleText("2o grau completo");

    // fazendo a verifica��o
    Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

    driverChrome.quit();

  }

  @Test
  public void verificaValoresDoCombo() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // 1� encontre o emelento e guarde em uma vari�vel
    WebElement element = driverChrome.findElement(By.id("elementosForm:escolaridade"));

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

    driverChrome.quit();
  }


  @Test
  public void verificaValoresDoComboMultiplo() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    WebElement element = driverChrome.findElement(By.id("elementosForm:esportes"));
    Select combo = new Select(element);

    // selecionando campos multiplos
    combo.selectByVisibleText("Natacao");
    combo.selectByVisibleText("Corrida");
    combo.selectByVisibleText("O que eh esporte?");

    // Verificações
    List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
    Assert.assertEquals(3, allSelectedOptions.size());

    // desmarcando algo desse combo
    combo.deselectByVisibleText("Corrida");
    allSelectedOptions = combo.getAllSelectedOptions();
    Assert.assertEquals(2, allSelectedOptions.size());

    driverChrome.quit();

  }


  @Test
  public void interagirComBotoes() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    // colocando o elemento em uma variavel
    WebElement botao = driverChrome.findElement(By.id("buttonsimple"));
    botao.click();

    // validação
    Assert.assertEquals("Obrigado!", botao.getAttribute("value"));


    driverChrome.quit();

  }

  @Test
  //@Ignore // anotação para não executar esse teste
  public void interagirComLink() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    driverChrome.findElement(By.linkText("Voltar")).click();
    Assert.assertEquals("Voltou!", driverChrome.findElement(By.id("Resultado")).getText());
    //Assert.fail(); // para testes incompletos

    driverChrome.quit();

  }

  @Test
  public void deveBuscarTextosNaPagina() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
    WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
    driverChrome.manage().window().setSize(new Dimension(1200, 765));
    driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    /* //imprimindo todo o texto da tag body
    //System.out.println(driverChrome.findElement(By.tagName("body")).getText());

    // verificando se existe o titulo do texto titulo do texto/ mas essa não é a melhor forma
    Assert.assertTrue(driverChrome.findElement(By.tagName("body"))
            .getText().contains("Campo de Treinamento"));
  */

    Assert.assertEquals("Campo de Treinamento",
            driverChrome.findElement(By.tagName("h3")).getText());

    Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
            driverChrome.findElement(By.className("facilAchar")).getText());

    driverChrome.quit();

  }



}
