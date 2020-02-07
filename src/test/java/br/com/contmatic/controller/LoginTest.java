package br.com.contmatic.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.contmatic.constantes.controller.CadastroCliente;
import br.com.contmatic.constantes.controller.ContratarFuncionario;
import br.com.contmatic.constantes.controller.Login;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
import br.com.contmatic.fixture.factory.GeradorCpf;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class LoginTest {

    private Date data;

    private List<Funcionario> funcionarios;

    private static Empresa loja;

    private Login login;

    ContratarFuncionario contrarFuncionario;

    @BeforeClass
    public static void cadastrar_empresa() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setFuncionario(new ArrayList<>());
        loja.setCliente(new ArrayList<>());
    }

    @Before
    public void iniciarLogin() {
        login = new Login();
    }

    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(
            new Funcionario("lucas", "lucas@gmail.com", new BigDecimal(2500.00), Cargo.RH, new Date(), TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf()));
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new Date(), loja, TipoContrato.CLT,
            Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
    }

    @Test
    public void deve_ser_funcionario_para_fazer_login() {
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new Date(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
        assertTrue("Não foi funcionario que fez o login", login.verificaLoginFuncioanrio("matheus", "matheus@gmail.com", loja));
    }

    @Test()
    public void deve_ser_funcionario_para_confirmar_login() {
        ContratarFuncionario.contratarFuncionario("matheus", "matheus@gmail.com", Cargo.REPOSITOR, new BigDecimal(3500.00), new Date(), loja, TipoContrato.CLT, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), funcionarios.get(0));
        assertEquals("Não foi funcionario que fez o login", loja.getFuncionario().get(0),
            login.funcionarioThatDoLogin(loja.getFuncionario().get(0).getNome(), loja.getFuncionario().get(0).getEmail(), loja));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_email_e_login_validos_para_fazer_login_funcionario() {
        assertEquals("Não foi funcionario que fez o login", funcionarios.get(0), login.funcionarioThatDoLogin(funcionarios.get(0).getNome(), "dante@gmail.com", loja));
    }

    @Test
    public void deve_ter_email_e_nome_validas_para_fazer_login_no_clinte() {
        CadastroCliente cadastroCliente = new CadastroCliente(loja);
        cadastroCliente.cadastrarCliente("Matheus", "matheus@gmail.com", new Date(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf());
        assertTrue(login.verificaLoginCliente(loja.getCliente().get(0).getNome(), loja.getCliente().get(0).getEmail(), loja));
    }

    @Test
    public void deve_retornar_cliente_que_fez_login() {
        CadastroCliente cadastroCliente = new CadastroCliente(loja);
        cadastroCliente.cadastrarCliente("a", "a@gmail.com", data, Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf());
        assertEquals("Não foi cliente que fez o login", loja.getCliente().get(0), login.clienteThatDoLogin("matheus", "matheus@gmail.com", loja));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_ter_email_e_login_validos_para_fazer_login_cliente() {
        assertEquals("Não foi cliente que fez o login", funcionarios.get(0), login.clienteThatDoLogin("lucas", "matheus@gmail.com", loja));
    }

}
