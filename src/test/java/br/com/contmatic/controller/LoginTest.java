package br.com.contmatic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

public class LoginTest {

    private List<Funcionario> funcionarios;

    private static Empresa loja;

    ContratarFuncionario contrarFuncionario;

    @BeforeClass
    public static void cadastrar_empresa() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
    }
    
    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.get(0).setCargo(Cargo.RH);
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT,
            Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
    }

    @Test
    public void deve_ser_funcionario_para_fazer_Login() {
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
        assertTrue("Não foi funcionario que fez o Login", Login.verificaLoginFuncioanrio("matheus", "matheus@gmail.com", loja));
    }

    @Test()
    public void deve_ser_funcionario_para_confirmar_Login() {
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new DateTime(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
        assertEquals("Não foi funcionario que fez o Login", loja.getFuncionario().get(0),
            Login.funcionarioThatDoLogin(loja.getFuncionario().get(0).getNome(), loja.getFuncionario().get(0).getEmail(), loja));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_email_e_Login_validos_para_fazer_Login_funcionario() {
        assertEquals("Não foi funcionario que fez o Login", funcionarios.get(0), Login.funcionarioThatDoLogin(funcionarios.get(0).getNome(), "dante@gmail.com", loja));
    }

    @Test
    public void deve_ter_email_e_nome_validas_para_fazer_Login_no_clinte() {
        CadastroCliente.cadastrarClienteNaLoja("Matheus", "matheus@gmail.com", new DateTime(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), loja);
        assertTrue(Login.verificaLoginCliente(loja.getCliente().get(0).getNome(), loja.getCliente().get(0).getEmail(), loja));
    }

    @Test
    public void deve_retornar_cliente_que_fez_Login() {
        CadastroCliente.cadastrarClienteNaLoja("a", "a@gmail.com", new DateTime(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), loja);
        assertEquals("Não foi cliente que fez o Login", loja.getCliente().get(0), Login.clienteThatDoLogin("matheus", "matheus@gmail.com", loja));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_email_e_Login_validos_para_fazer_Login_cliente() {
        assertEquals("Não foi cliente que fez o Login", funcionarios.get(0), Login.clienteThatDoLogin("lucas", "matheus@gmail.com", loja));
    }

}
