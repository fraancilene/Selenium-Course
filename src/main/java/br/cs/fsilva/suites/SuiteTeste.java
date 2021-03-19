package br.cs.fsilva.suites;

import br.cs.fsilva.core.DriverFactory;
import br.cs.fsilva.test.TesteCadastro;
import br.cs.fsilva.test.TesteRegrasCadastro;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

// colocar as classes eu quero que sejam executadas
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class,
})
public class SuiteTeste {
  @AfterClass  // vai executar após a execução da classe
  public static void finalizaTudo(){
    DriverFactory.killDriver();
  }


}
