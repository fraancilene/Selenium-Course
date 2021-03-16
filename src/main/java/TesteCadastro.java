import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CampoTreinamentoPage;
import pages.DSL;

public class TesteCadastro {

    private WebDriver driverChrome;
    private CampoTreinamentoPage page;

    @Before
    public void inicializaAntesDosTestes(){
        // configurando navegado
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        //driverChrome.get("https://teste-git-main-fraancilene.vercel.app/");
       driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage(driverChrome);
    }

    // metodo que será chamado depois de cada teste
    @After
    public void inicializaDepoisDosTestes(){
        driverChrome.quit();
    }


    @Test
    public void fazerCadastroComSucesso() {

        // PREENCHENDO O FORMULÁRIO
        page.setNome("Francilene");
        page.setSobrenome("Silva");
        page.setSexoFeminino();
        page.setComidaPizza();

        // dropdowns
        page.setEscolaridade("Superior");
        page.setSelecionarEsporte("Corrida");
        page.cadastrar();

        // verificações
        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Francilene",page.obterNomeCadastro());
        Assert.assertEquals("Silva", page.obterSobrenomeCadastro());
        Assert.assertEquals("Feminino", page.obterSexoCadastro());
        Assert.assertEquals("Frango", page.obterComidaCadastro());
        Assert.assertEquals("Corrida", page.obterEsporteCadastro());
        Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
    }

}
