package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.enums.TipoContrato;
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
    
    private Set<Telefone> telefones;

    /**
     * Add dados iniciais.
     */
    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        produtos = new ArrayList<>();
        produtos.add(Fixture.from(Produto.class).gimme("valid"));
        loja = Fixture.from(Empresa.class).gimme("valid");
        loja.setFuncionario(new ArrayList<>());
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
    
    @Before
    public void addDadosTelefone() {
        telefones = new HashSet<>();
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        telefones.add(Fixture.from(Telefone.class).gimme("valid"));
    }

    @Test
    public void deve_mudar_nome_funcionario() {
        funcionarios.get(0).setNome("kratos");
        assertEquals("kratos", funcionarios.get(0).getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("nomeNull"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("nomeEmpty"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("nomeBlankSpace"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_mudar_email_funcionario() {
        loja.setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", loja.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("emailNull"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("emailEmpty"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_VAZIO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("emailBlankSpace"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_alterar_email() {
        funcionarios.get(0).setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", funcionarios.get(0).getEmail());
    }
    
    @Test
    public void deve_retornar_true_se_cargo_for_null() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("cargoNull"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_cargo_para_repositor() {
        funcionarios.get(0).setCargo(Cargo.REPOSITOR);
        assertTrue(funcionarios.get(0).getCargo() == Cargo.REPOSITOR);
    }
    
    @Test
    public void deve_alterar_cargo_para_rh() {
        funcionarios.get(0).setCargo(Cargo.RH);
        assertTrue(funcionarios.get(0).getCargo() == Cargo.RH);
    }
    
    @Test
    public void deve_retornar_true_se_tipoContrato_for_null() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("tipoContratoNull"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_clt() {
        funcionarios.get(0).setTipoContrato(TipoContrato.CLT);
        assertTrue(funcionarios.get(0).getTipoContrato() == TipoContrato.CLT);
    }
    
    @Test
    public void deve_alterar_tipoContrato_para_pj() {
        funcionarios.get(0).setTipoContrato(TipoContrato.PJ);
        assertTrue(funcionarios.get(0).getTipoContrato() == TipoContrato.PJ);
    }
    
    @Test
    public void deve_retornar_true_se_dataNascimento_for_null() {
        funcionarios.set(0, Fixture.from(Funcionario.class).gimme("dataNascimentoNull"));
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionarios.get(0), Constante.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_alterar_dataNascimento() {
        funcionarios.get(0).setDataNascimento(new DateTime());
        assertEquals(new DateTime(), funcionarios.get(0).getDataNascimento());
    }
    
    @Test
    public void deve_add_telefone_na_lista_telefones() {
        funcionarios.get(0).setTelefones(telefones);
        assertTrue(funcionarios.get(0).getTelefones().size() > 0);
    }
    
    @Test
    public void deve_mudar_endereco() {
        funcionarios.get(0).setEndereco(funcionarios.get(1).getEndereco());
        assertEquals(funcionarios.get(1).getEndereco(), funcionarios.get(0).getEndereco());   
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

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.get(1).setCpf(funcionarios.get(0).getCpf());
        assertTrue("Os Funcionario são iguais", funcionarios.get(0).equals(funcionarios.get(1)));
    }
    
    @Test()
    public void deve_retornar_true_quando_compara_com_mesmo_objeto() {
        assertTrue(funcionarios.get(0).equals(funcionarios.get(0)));
    }

    @Test()
    public void deve_retornar_false_quando_compara_com_classe_diferente() {
        assertFalse(funcionarios.get(0).equals(new Object()));
    }
    /**
     * Deve ter hash code iguais para serem funcionarios iguais.
     */
    @Test()
    public void deve_ter_hashCode_iguais_para_serem_funcionarios_iguais() {
        funcionarios.add(Fixture.from(Funcionario.class).gimme("valid"));
        funcionarios.get(1).setCpf(funcionarios.get(0).getCpf());
        assertTrue(funcionarios.get(0).hashCode() == funcionarios.get(1).hashCode());
    }

    /**
     * Nao deve ter equals null para comparar funcionarios.
     */
    @Test()
    public void nao_deve_ter_equals_null_para_comparar_funcionarios() {
        assertFalse("Os funcionarios são igauis", funcionarios.get(0).equals(null));
    }

    /**
     * Mostrar dados.
     */
    @After
    public void mostrarDados() {
        System.out.println(funcionarios.get(0));
    }

}
