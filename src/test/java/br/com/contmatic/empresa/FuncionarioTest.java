package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

    private static List<Produto> produtos;

    private List<Funcionario> funcionarios;

    private static Empresa loja;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(new Produto("Tablet", new BigDecimal(2500.00), 50));
        produtos.add(new Produto("Smartphone", new BigDecimal(2500.00), 150));
        produtos.add(new Produto("Fone de Ouvido", new BigDecimal(50.00), 200));
        produtos.add(new Produto("Computador", new BigDecimal(3500.00), 70));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
    }

    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();

        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));

    }

    @Test
    public void deve_ter_salario_maior_do_que_zero() {
        funcionarios.add(Fixture.from(Funcionario.class).gimme("salarioLess1"));
        funcionarios.get(3).setSalario(funcionarios.get(3).getSalario());
        ValidadorAnnotionsMsgErro<Funcionario> validadorAnnotionsMsgErro = new ValidadorAnnotionsMsgErro<>();
        assertEquals("O valor precisa ser um salario minimo", validadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(3)));
    }

    @Test
    public void nao_deve_existir_funcionarios_iguais() {
        assertTrue("Os funcionarios são iguais", funcionarios.get(0).equals(funcionarios.get(0)));
    }

    @Test
    public void nao_deve_existir_funcionarios_iguais_2() {
        assertFalse("Os funcionarios são iguais", funcionarios.get(0).equals(funcionarios.get(1)));
    }

    @Test
    public void nao_deve_existir_funcionarios_iguais_3() {
        assertEquals("Os funcionarios são iguais", funcionarios.get(0).hashCode(), funcionarios.get(0).hashCode());
    }

     @After
     public void mostrarEndereco() {
     System.out.println(funcionarios.get(0).getEndereco());
     }
    
     @After
     public void mostrarDados() {
     System.out.println(funcionarios.get(0));
     }

}
