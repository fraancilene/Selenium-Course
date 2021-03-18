package br.cs.fsilva.core;

import org.junit.After;

import static br.cs.fsilva.core.DriverFactory.killDriver;

public class BaseTest {

    // metodo que será chamado depois de cada teste
    @After
    public void inicializaDepoisDosTestes(){
        killDriver();
    }
}
