package br.com.contmatic.controller;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.fixture.factory.GeradorCpf;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class DemitirFuncionarioTest {

    private Funcionario funcionario;

    private static Empresa loja;
    
    private String armazenarCpf;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
    }

    @Before
    public void add_dados_funcionario() {
        funcionario = Fixture.from(Funcionario.class).gimme("valid");
        funcionario.setCargo(Cargo.RH);
        armazenarCpf = GeradorCpf.gerardorRandomCpf();
        ContratarFuncionario.contratarFuncionario("lucas", "lucas@gmail.com", Cargo.REPOSITOR, new BigDecimal(2500.00), new DateTime(), loja, TipoContrato.PJ,
            Fixture.from(Endereco.class).gimme("valid"), armazenarCpf, funcionario);
    }

    @Test
    public void deve_demitir_um_funcionario() {
        DemitirFuncionario.demitirFuncionario(armazenarCpf, funcionario, loja);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_lancar_exception_caso_informe_um_cpf_invalido() {
        DemitirFuncionario.demitirFuncionario("4156461651515", funcionario, loja);
    }

}
