import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEjanelas {

    @Test
    public void interagirComFrames() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driverChrome.switchTo().frame("frame1"); //colocando foco no frame
        driverChrome.findElement(By.id("frameButton")).click();
        Alert alert = driverChrome.switchTo().alert(); //colocando foco no alert
        String msg = alert.getText(); // salvando a mensagem do alert
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driverChrome.switchTo().defaultContent(); // trazendo o foco para a página principal
        // escrevendo a mensagem do alert no cmapo nome
        driverChrome.findElement(By.id("elementosForm:nome")).sendKeys(msg);
        driverChrome.quit();

    }

    @Test
    public void InteragirComJanelas() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driverChrome.findElement(By.id("buttonPopUpEasy")).click();
        driverChrome.switchTo().window("Popup"); // foco no popup

        driverChrome.findElement(By.tagName("textarea")).sendKeys("Deu Certo!"); // escrevendo no textarea da popup
        driverChrome.close(); // fechando a popup
        driverChrome.switchTo().window("");
        driverChrome.findElement(By.tagName("textarea")).sendKeys("E agora?"); // escrevendo no textarea da pagina principal

    }


    @Test
    public void InteragirComJanelasSemtitulo() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driverChrome.findElement(By.id("buttonPopUpHard")).click();
        //System.out.println(driverChrome.getWindowHandle()); // retornando id da popup que não tem nome
        //System.out.println(driverChrome.getWindowHandles()); // retornando os ids das popups que estão sendo gerenciadas
        driverChrome.switchTo().window((String) driverChrome.getWindowHandles().toArray()[1]);
        driverChrome.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driverChrome.switchTo().window((String) driverChrome.getWindowHandles().toArray()[0]);
        driverChrome.findElement(By.tagName("textarea")).sendKeys("E agora?");

        driverChrome.quit();


    }
}


