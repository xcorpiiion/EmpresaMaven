package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The funcionarios. */
    private List<Funcionario> funcionarios;

    /** The loja. */
    private static Empresa loja;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setCliente(new ArrayList<>());
        loja.setFuncionario(new ArrayList<>());
    }

    /**
     * Add dados funcionario.
     */
    @Before
    public void add_dados_funcionario() {
        funcionarios = new ArrayList<>();
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
    }

    /**
     * Deve ter salario maior do que zero.
     */
    @Test
    public void deve_ter_salario_maior_do_que_zero() {
        funcionarios.add(Fixture.from(Funcionario.class).gimme("salarioLess1"));
        funcionarios.get(3).setSalario(funcionarios.get(3).getSalario());
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(3), Constante.PRECISA_SER_UM_VALOR_MAIOR));
    }

    /**
     * Nao deve existir funcionarios iguais.
     */
    @Test
    public void nao_deve_existir_funcionarios_iguais() {
        String cpf = funcionarios.get(1).getCpf();
        funcionarios.get(0).setCpf(cpf);
        assertEquals(cpf, funcionarios.get(0).getCpf());
    }

    /**
     * Deve ser igual caso tenha mesmo hashcode.
     */
    @Test
    public void deve_ser_igual_caso_tenha_mesmo_hashcode() {
        funcionarios.get(0).setCpf(funcionarios.get(1).getCpf());
        assertEquals("Os funcionarios são iguais", funcionarios.get(0).hashCode(), funcionarios.get(1).hashCode());
    }

    @Test
    public void deve_ser_igual_caso_tenha_mesmo_equals() {
        funcionarios.get(0).setCpf(funcionarios.get(1).getCpf());
        System.out.println(funcionarios.get(1).getCpf());
        System.out.println(funcionarios.get(0).getCpf());
        assertTrue(funcionarios.get(0).equals(funcionarios.get(1)));
    }

    /**
     * Mostrar endereco.
     */
    @After
    public void mostrarEndereco() {
        System.out.println(funcionarios.get(0).getEndereco());
    }

    /**
     * Mostrar dados.
     */
    @After
    public void mostrarDados() {
        System.out.println(funcionarios.get(0));
    }

}
