package br.cs.fsilva.suites;

import br.cs.fsilva.test.TesteCadastro;
import br.cs.fsilva.test.TesteCampoTreinamento;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

// colocar as classes eu quero que sejam executadas
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteCadastro.class,
        TesteCampoTreinamento.class
})
public class SuiteTeste {


}
