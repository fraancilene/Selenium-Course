package pages;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    // construtor
    public  CampoTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }

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

    public void setSelecionarEsporte(String valor) {
        dsl.selecionarCombo("elementosForm:esportes", valor);
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultado() {
        return dsl.obterTexto("resultado");
    }
}
