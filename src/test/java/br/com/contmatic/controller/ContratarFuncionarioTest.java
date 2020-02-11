package br.com.contmatic.controller;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.joda.time.DateTime;
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

public class ContratarFuncionarioTest {

    private static Empresa loja;

    private static Funcionario funcionario;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
        funcionario = Fixture.from(Funcionario.class).gimme("valid");
        funcionario.setCargo(Cargo.RH);
    }

    @Test
    public void deve_contratar_funcionario() {
        ContratarFuncionario.contratarFuncionario("lucas", "lucas@gmail.com", Cargo.RH, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"),
            GeradorCpf.gerardorRandomCpf(), funcionario);
        assertTrue(loja.getFuncionario().get(0).getNome().equalsIgnoreCase("lucas"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retorna_uma_exception_caso_funcionario_ja_esteja_cadastrado() {
        String cpf = GeradorCpf.gerardorRandomCpf();
        ContratarFuncionario.contratarFuncionario("lucas", "lucas@gmail.com", Cargo.RH, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"),
            cpf, funcionario);
        ContratarFuncionario.contratarFuncionario("lucas", "lucas@gmail.com", Cargo.RH, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"),
            cpf, funcionario);
    }

}
