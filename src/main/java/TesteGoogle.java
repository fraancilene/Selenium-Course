
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {
	
	// para ser reconhecido como teste JUnit
	@Test
	public void teste() { // classes de teste n�o prescisa de metodo main
		
		
		//se n�o colocar a pasta dos drivers no path do S.O, teria que adicionar essa linha
		System.setProperty("webdriver.gecko.driver",  "C:\\Users\\Fran\\Documents\\drivers-navegadores\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\IEDriverServer.exe");
		
		
		// api para os metodos de intera��o com o browser � o WebDriver
		//WebDriver driverFirefox = new FirefoxDriver(); // instanciando um objeto firefox
		WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
		//WebDriver driverExplorer = new InternetExplorerDriver(); // instanciando um objeto internet explorer
		
		// definindo o tamanho e a posi��o do browser
		//driverChrome.manage().window().setPosition(new Point(100, 100));
		//driverChrome.manage().window().setSize(new Dimension(1200, 765));
		driverChrome.manage().window().maximize();
		
		
		// acessando o google
		//driverFirefox.get("http://www.google.com");
		driverChrome.get("http://www.google.com");
		//driverExplorer.get("http://www.google.com");
		
		// imprimindo o titulo da p�gina
		//System.out.println(driver.getTitle();
		
		// verifica��o firefox
		//Assert.assertEquals("Google", driverFirefox.getTitle()); 
		//Assert.assertEquals("Yahoo!", driverFirefox.getTitle()); // teste com erro
		
		// verifica��o chrome
		Assert.assertEquals("Google", driverChrome.getTitle()); 
		
		// verifica��o internet explorer
		//Assert.assertEquals("Google", driverExplorer.getTitle());
		
		driverChrome.quit();
		//driverFirefox.close();
		//driverExplorer.close();
	}
	
	

}
