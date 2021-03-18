package br.cs.fsilva.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import br.cs.fsilva.core.DSL;

import static br.cs.fsilva.core.DriverFactory.getDriver;
import static br.cs.fsilva.core.DriverFactory.killDriver;

public class  TesteFramesEjanelas {

    private DSL dsl;

    @Before
    public void inicializaAntesDosTestes(){

        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    // metodo que será chamado depois de cada teste
    @After
    public void inicializaDepoisDosTestes(){
       killDriver();
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
    public void deveInteragirComFrameEscondido(){
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);//codigo js
        dsl.entrarFrame("frame2");
        dsl.clicarBotao("frameButton");
        String msg = dsl.focoAlertaPegaTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);

    }

    @Test
    public void InteragirComJanelas() {

        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"), "e agora?");

    }


    @Test
    public void InteragirComJanelasSemtitulo() {
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");


    }


}


