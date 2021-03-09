import org.junit.Assert;
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
		
		// quando o projeto que está sendo testado está na sua máquina, assim poderá ser executado em qualquer máquina
		driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//System.getProperty("user.dir"); // propriedade retorna o local exato onde o java esta rodando
		
		// 1º identificar o campo e o que quero fazer com esse elemento, nesse caso, inserir um texto
		driverChrome.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		
		// 2º verificando se o campo esta preenchendo
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
				
				// 1º localizar elemento e o que quero fazer com esse elemento
				driverChrome.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");	
				
				// 2º verificando se o campo esta preenchendo
				Assert.assertEquals("Teste", driverChrome.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
				
			driverChrome.quit();

	}
	
	@Test
	public void InteragirComRadioButton() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
		WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
		driverChrome.manage().window().setSize(new Dimension(1200, 765));
		driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// 1º localizar elemento e o que quero fazer com esse elemento
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
		
		// 1º localizar elemento e o que quero fazer com esse elemento
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
		
		// 1º encontre o emelento e guarde em uma variável
		WebElement element = driverChrome.findElement(By.id("elementosForm:escolaridade"));
		
		// use o select para manipular o dropdown
		Select combo = new Select (element);
		
		// forma de selecionar as opções do dropdown
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");

		// fazendo a verificação
		Assert.assertEquals("2o grau completo",combo.getFirstSelectedOption().getText());

		driverChrome.quit();
		
	}

	@Test
	public void verificaValoresDoCombo() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
		WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
		driverChrome.manage().window().setSize(new Dimension(1200, 765));
		driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		// 1º encontre o emelento e guarde em uma variável
		WebElement element = driverChrome.findElement(By.id("elementosForm:escolaridade"));

		// use o select para manipular o dropdown
		Select combo = new Select(element);

		// retornando uma lista de todas as opções do webElemnt
		List<WebElement> options = combo.getOptions();

		// verificações
		Assert.assertEquals(8, options.size()); // quantidade de opções que o dropdown possui

		// checando que alguma possível opção está disponível no combo
		boolean encontrou = false;
		for (WebElement option: options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);

		driverChrome.quit();
	}
}
