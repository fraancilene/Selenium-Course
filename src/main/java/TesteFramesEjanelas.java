import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DSL;

public class  TesteFramesEjanelas {

    private WebDriver driverChrome;
    private DSL dsl;

    @Before
    public void inicializaAntesDosTestes(){
        // configurando navegado
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        //driverChrome.get("https://teste-git-main-fraancilene.vercel.app/");
        driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driverChrome);
    }

    // metodo que será chamado depois de cada teste
    @After
    public void inicializaDepoisDosTestes(){
        driverChrome.quit();
    }


    @Test
    public void interagirComFrames() {
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.focoAlertaPegaTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escreveNoCampo("elementosForm:nome", msg);

    }

    @Test
    public void InteragirComJanelas() {

        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        driverChrome.close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"), "e agora?");

    }


    @Test
    public void InteragirComJanelasSemtitulo() {
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(driverChrome.getWindowHandle());
        System.out.println(driverChrome.getWindowHandles());
        dsl.trocarJanela((String) driverChrome.getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) driverChrome.getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");


    }
}


