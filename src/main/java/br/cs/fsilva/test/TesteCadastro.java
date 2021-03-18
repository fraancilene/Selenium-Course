package br.cs.fsilva.test;

import br.cs.fsilva.core.BaseTest;
import br.cs.fsilva.page.CampoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.cs.fsilva.core.DriverFactory.getDriver;

public class TesteCadastro  extends BaseTest {

    private CampoTreinamentoPage page;

    @Before
    public void inicializaAntesDosTestes(){
       getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage();
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
