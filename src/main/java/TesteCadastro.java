import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    @Test
    public void fazerCadastro() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        WebDriver driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        // PREENCHENDO O FORMULÁRIO
        driverChrome.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        driverChrome.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driverChrome.findElement(By.id("elementosForm:sexo:1")).click();
        driverChrome.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        // dropdowns
        new Select(driverChrome.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
        new Select(driverChrome.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
        driverChrome.findElement(By.id("elementosForm:cadastrar")).click();

        // verificações
        Assert.assertTrue(driverChrome.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driverChrome.findElement(By.id("descNome")).getText().endsWith("Francilene") );
        Assert.assertEquals("Sobrenome: Silva", driverChrome.findElement(By.id("descSobrenome")).getText() );
        Assert.assertEquals("Sexo: Feminino", driverChrome.findElement(By.id("descSexo")).getText() );
        Assert.assertEquals("Comida: Frango", driverChrome.findElement(By.id("descComida")).getText() );
        Assert.assertEquals("Esportes: Corrida", driverChrome.findElement(By.id("descEsportes")).getText() );
        Assert.assertEquals("Escolaridade: superior", driverChrome.findElement(By.id("descEscolaridade")).getText() );

        driverChrome.quit();
    }
}
