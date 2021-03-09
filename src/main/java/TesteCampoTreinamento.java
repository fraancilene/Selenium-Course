import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
				Assert.assertEquals("Teste", driverChrome.findElement(By.id("elementosForm:nome")).getAttribute("value"));
				
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
	
	
	
	
	
	
	
	
	
	
	

}
