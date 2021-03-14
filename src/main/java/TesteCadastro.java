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
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializaAntesDosTestes(){
        // configurando navegado
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Documents\\drivers-navegadores\\chromedriver.exe");
        driverChrome = new ChromeDriver(); // instanciando um objeto chrome
        driverChrome.manage().window().setSize(new Dimension(1200, 765));
        //driverChrome.get("https://teste-git-main-fraancilene.vercel.app/");
       driverChrome.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driverChrome);
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
        Assert.assertTrue(page.obterResultado().startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Francilene"));
        Assert.assertEquals("Sobrenome: Silva", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Frango", dsl.obterTexto("descComida"));
        Assert.assertEquals("Esportes: Corrida", dsl.obterTexto("descEsportes"));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
    }

    @Test
    public void validarNomeObrigatorio(){
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.focoAlertaPegaTextoEAceita() );
    }


    @Test
    public void validarSobrenomeObrigatorio(){
        dsl.escreveNoCampo("elementosForm:nome", "Nome qualquer");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.focoAlertaPegaTextoEAceita());


    }

    @Test
    public void validarSexoObrigatorio(){
        dsl.escreveNoCampo("elementosForm:nome", "Nome qualquer");
        dsl.escreveNoCampo("elementosForm:sobrenome", "Sobrenome qualquer");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Sexo eh obrigatorio", dsl.focoAlertaPegaTextoEAceita());

    }

    @Test
    public void sexoObrigatorio(){
        dsl.escreveNoCampo("elementosForm:nome", "Nome qualquer");
        dsl.escreveNoCampo("elementosForm:sobrenome", "Nome qualquer");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Sexo eh obrigatorio",  dsl.focoAlertaPegaTextoEAceita());
    }

    @Test
    public void validarComidaVegetariana(){
        // pegando elemento

        dsl.escreveNoCampo("elementosForm:nome", "Nome qualquer");
        dsl.escreveNoCampo("elementosForm:sobrenome", "Nome qualquer");
        dsl.clicarBotao("elementosForm:sexo:1");
        dsl.clicarBotao("elementosForm:comidaFavorita:0");
        dsl.clicarBotao("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?",  dsl.focoAlertaPegaTextoEAceita());
    }

    @Test
    public void validarEsportistsIndeciso(){

        // pegando elemento
        dsl.escreveNoCampo("elementosForm:nome", "Nome qualquer");
        dsl.escreveNoCampo("elementosForm:sobrenome", "Nome qualquer");
        dsl.clicarBotao("elementosForm:sexo:1");
        dsl.clicarBotao("elementosForm:comidaFavorita:0");

        dsl.selecionarCombo("elementosForm:esportes", "Karate");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        dsl.clicarBotao("elementosForm:cadastrar");

        // Assertiva
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.focoAlertaPegaTextoEAceita());


    }
}
