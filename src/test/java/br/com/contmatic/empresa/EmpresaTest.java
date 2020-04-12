package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.easyrandom.EasyRandomCliente;
import br.com.contmatic.easyrandom.EasyRandomEmpresa;
import br.com.contmatic.easyrandom.EasyRandomEndereco;
import br.com.contmatic.easyrandom.EasyRandomFuncionario;
import br.com.contmatic.easyrandom.EasyRandomProduto;
import br.com.contmatic.easyrandom.EasyRandomTelefone;
import br.com.contmatic.easyrandom.TipoDadoParaTesteCliente;
import br.com.contmatic.easyrandom.TipoDadoParaTesteEmpresa;
import br.com.contmatic.easyrandom.TipoDadoParaTesteEndereco;
import br.com.contmatic.easyrandom.TipoDadoParaTesteFuncionario;
import br.com.contmatic.easyrandom.TipoDadoParaTesteProduto;
import br.com.contmatic.easyrandom.TipoDadoParaTesteTelefone;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * The Class EmpresaTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

    /** The produtos. */
    private static List<Produto> produtos;

    /** The funcionarios. */
    private static List<Funcionario> funcionarios;

    /** The loja 2. */
    private static Empresa loja;

    private static Set<Telefone> telefones;

    /**
     * Cadastrar empresa.
     */
    @BeforeClass
    public static void cadastrar_empresa() {
        loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.VALIDO);
        telefones = new HashSet<>();
        telefones.add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        funcionarios = new ArrayList<>();
        funcionarios.add(EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO));
        produtos = new ArrayList<>();
        produtos.add(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO));
    }

    @Test
    public void deve_mudar_nome_empresa() {
        loja.setNome("kratos games");
        assertEquals("kratos games", loja.getNome());
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_null() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_vazio() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME_BLANK_SPACE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME_INVALID_SIZE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_mais_50_caracter() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME_INVALID_SIZE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.NOME__WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_mudar_email_empresa() {
        loja.setEmail("kratos@gmail.com");
        assertEquals("kratos@gmail.com", loja.getEmail());
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_null() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.EMAIL_NULL);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_seja_vazio() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.EMAIL_EMPTY);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.EMAIL_BLANK_SPACE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.EMAIL_INVALID_SIZE);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
        
    }
    
//    @Test
//    public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
//        loja = Fixture.from(Empresa.class).gimme("emailWithBlankSpaceInWord");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//    
//    @Test
//    public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
//        loja = Fixture.from(Empresa.class).gimme("emailWithNumberAfterArroba");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//    
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_arroba() {
//        loja = Fixture.from(Empresa.class).gimme("emailWithoutArroba");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//    
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
//        loja = Fixture.from(Empresa.class).gimme("emailWithoutPontoCom");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//    
//    @Test
//    public void deve_retornar_true_caso_email_esteja_sem_com() {
//        loja = Fixture.from(Empresa.class).gimme("emailWithoutCom");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
    
    @Test
    public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.EMAIL_WITH_SPECIAL_CARACTER);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }
    
    @Test
    public void deve_add_telefone_na_lista_telefones() {
        loja.setTelefones(telefones);
        assertTrue(loja.getTelefones().size() > 0);
    }

    @Test
    public void deve_add_produto_na_loja() {
        loja.setProduto(produtos);
        assertTrue(loja.getProduto().size() > 0);
    }

    /**
     * Nao deve aceitar produto null.
     */
    @Test()
    public void nao_deve_aceitar_produto_null() {
        assertNotNull("O produto esta null", produtos);
    }

    /**
     * Nao deve aceitar cnpj null.
     */
    @Test
    public void nao_deve_aceitar_cnpj_null() {
    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.CNPJ_NULL);
    	assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
    }

    /**
     * Nao deve aceitar cnpj com letras.
     */
//    @Test
//    public void nao_deve_aceitar_cnpj_com_letras() {
//    	loja = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.CNPJ_NULL);
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }
//
//    /**
//     * Nao deve aceitar cnpj null com menos 14 numeros.
//     */
//    @Test
//    public void nao_deve_aceitar_cnpj_null_com_menos_14_numeros() {
//        loja = Fixture.from(Empresa.class).gimme("cnpjWrongSize");
//        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//    }

    /**
     * Nao deve aceitar endereco null.
     */
    @Test
    public void deve_retornar_true_caso_funcionario_seja_null() {
        loja.setFuncionario(null);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_add_funcionario_na_empresa() {
        loja.setFuncionario(funcionarios);
        assertTrue(loja.getFuncionario().size() > 0);
    }
    
    @Test
    public void deve_retornar_true_caso_cliente_seja_null() {
        loja.setCliente(null);
        assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_ESTA_NULLO));
    }
    
    @Test
    public void deve_add_cliente_na_empresa() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.VALIDO));
        loja.setCliente(clientes);
        assertTrue(loja.getCliente().size() > 0);
    }
    
    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(funcionarios.get(0).getEndereco());
    }
    
    @Test
    public void deve_add_endereco_empresa() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
        loja.setEndereco(endereco);
        assertTrue(loja.getEndereco().size() > 0);
    }

    /**
     * Deve ter o mesmo cnpj para serem iguais.
     */
    @Test()
    public void deve_retornar_true_caso_tenham_mesmo_cnpj() {
    	EqualsVerifier.forClass(Empresa.class).usingGetClass()
		.suppress(Warning.NONFINAL_FIELDS, Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED).verify();
    }

    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_nome_no_toString() {
        assertTrue(loja.toString().contains("nome"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_email_no_toString() {
        assertTrue(loja.toString().contains("email"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_cnpj_no_toString() {
        assertTrue(loja.toString().contains("cnpj"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_telefones_no_toString() {
        assertTrue(loja.toString().contains("telefones"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_enderecos_no_toString() {
        assertTrue(loja.toString().contains("enderecos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_produtos_no_toString() {
        assertTrue(loja.toString().contains("produtos"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_funcionarios_no_toString() {
        assertTrue(loja.toString().contains("funcionarios"));
    }
    
    @Test
    public void deve_retornar_true_caso_contenha_a_palavra_clientes_no_toString() {
        assertTrue(loja.toString().contains("clientes"));
    }
    
    /**
     * Mostrar dados empresa.
     */
    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(loja);
    }

}
