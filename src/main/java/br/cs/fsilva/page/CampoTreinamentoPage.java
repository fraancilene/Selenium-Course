package br.cs.fsilva.page;

import br.cs.fsilva.core.BasePage;
import org.openqa.selenium.By;

public class CampoTreinamentoPage extends BasePage {

    public void setNome(String nome){
        dsl.escreveNoCampo("elementosForm:nome", nome);
    }


    public void setSobrenome(String sobrenome) {
        dsl.escreveNoCampo("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoFeminino(){
        dsl.clicarBotao("elementosForm:sexo:1");
    }

    public void setComidaPizza() {
        dsl.clicarBotao("elementosForm:comidaFavorita:1");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
    }

    public void setSelecionarEsporte(String... valores) {
        for (String valor: valores)
            dsl.selecionarCombo("elementosForm:esportes", valor);
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
    }

    public String obterSexoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
    }

    public String obterComidaCadastro() {
                return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
    }


    public String obterEsporteCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
    }

    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
    }

    public void setSexoMasculino() {
        dsl.clicarBotao("elementosForm:sexo:0");

    }

    public void setComidaCarne() {
        dsl.clicarBotao("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariana() {
        dsl.clicarBotao("elementosForm:comidaFavorita:3");
    }
}
