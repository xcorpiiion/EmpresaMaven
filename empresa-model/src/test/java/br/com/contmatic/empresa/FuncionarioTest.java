package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.Mensagem;
import br.com.contmatic.constantes.easyrandom.EasyRandomFuncionario;
import br.com.contmatic.constantes.easyrandom.EasyRandomTelefone;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteFuncionario;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteTelefone;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validator.ValidadorAnnotionsMsgErro;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTest {

	/** The funcionarios. */
	private static Funcionario funcionario;

	private static Funcionario funcionario2;

	private static Set<Telefone> telefones;

	/**
	 * Add dados iniciais.
	 */
	@BeforeClass
	public static void addDadosIniciais() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO);
		telefones = new HashSet<>();
		telefones.add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
	}

	@Test
	public void deve_mudar_nome_funcionario() {
		funcionario.setNome("kratos");
		assertEquals("kratos", funcionario.getNome());
	}

	@Test
	public void deve_retornar_true_caso_nome_seja_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.NOME_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_nome_seja_vazio() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.NOME_EMPTY);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_nome_esteja_com_espaco_em_branco() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.NOME_BLANK_SPACE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_nome_seja_possua_menos_3_caracter() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.NOME_INVALID_SIZE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_nome_seja_possua_caracteres_especiais() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.NOME__WITH_SPECIAL_CARACTER);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_cpf_seja_possua_caracteres_especiais() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.CPF_WITH_SPECIAL_CARACTER);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_cpf_seja_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.CPF_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_cpf_esteja_vazio() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.CPF_EMPTY);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_cpf_esteja_espaco_em_branco() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.CPF_BLANK_SPACE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_email_seja_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.EMAIL_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_email_seja_vazio() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.EMAIL_EMPTY);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.EMAIL_BLANK_SPACE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_alterar_email() {
		funcionario.setEmail("kratos@gmail.com");
		assertEquals("kratos@gmail.com", funcionario.getEmail());
	}

	@Test
	public void deve_retornar_true_caso_email_esteja_com_menos_10_caracteres() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.EMAIL_INVALID_SIZE);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

//	@Test
//	public void deve_retornar_true_caso_email_esteja_com_espaco_em_branco_entre_o_email() {
//		funcionario = Fixture.from(Funcionario.class).gimme("emailWithBlankSpaceInWord");
//		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//	}
//
//	@Test
//	public void deve_retornar_true_caso_email_esteja_com_numero_depois_do_arroba() {
//		funcionario = Fixture.from(Funcionario.class).gimme("emailWithNumberAfterArroba");
//		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//	}
//
//	@Test
//	public void deve_retornar_true_caso_email_esteja_sem_arroba() {
//		funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutArroba");
//		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//	}
//
//	@Test
//	public void deve_retornar_true_caso_email_esteja_sem_ponto_com() {
//		funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutPontoCom");
//		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//	}
//
//	@Test
//	public void deve_retornar_true_caso_email_esteja_sem_com() {
//		funcionario = Fixture.from(Funcionario.class).gimme("emailWithoutCom");
//		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(loja, Mensagem.VALOR_NAO_E_VALIDO));
//	}

	@Test
	public void deve_retornar_true_caso_email_esteja_com_caracteres_especiais() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.EMAIL__WITH_SPECIAL_CARACTER);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_NAO_E_VALIDO));
	}

	@Test
	public void deve_retornar_true_se_cargo_for_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.CARGO_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_alterar_cargo_para_repositor() {
		funcionario.setCargo(Cargo.REPOSITOR);
		assertTrue(funcionario.getCargo() == Cargo.REPOSITOR);
	}

	@Test
	public void deve_alterar_cargo_para_rh() {
		funcionario.setCargo(Cargo.RH);
		assertTrue(funcionario.getCargo() == Cargo.RH);
	}

	@Test
	public void deve_retornar_true_se_tipoContrato_for_null() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.TIPO_CONTRATO_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_alterar_tipoContrato_para_clt() {
		funcionario.setTipoContrato(TipoContrato.CLT);
		assertTrue(funcionario.getTipoContrato() == TipoContrato.CLT);
	}

	@Test
	public void deve_alterar_tipoContrato_para_pj() {
		funcionario.setTipoContrato(TipoContrato.PJ);
		assertTrue(funcionario.getTipoContrato() == TipoContrato.PJ);
	}

	@Test
	public void deve_retornar_true_se_dataNascimento_for_null() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.DATA_NASCIMENTO_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_alterar_dataNascimento() {
		funcionario.setDataNascimento(new DateTime());
		assertEquals(new DateTime(), funcionario.getDataNascimento());
	}

	@Test
	public void deve_add_telefone_na_lista_telefones() {
		funcionario.setTelefones(telefones);
		assertTrue(funcionario.getTelefones().size() > 0);
	}

	@Test
	public void deve_mudar_endereco() {
		funcionario2 = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO);
		funcionario.setEndereco(funcionario2.getEndereco());
		assertEquals(funcionario2.getEndereco(), funcionario.getEndereco());
	}

	/**
	 * Deve ter salario maior do que zero.
	 */
	@Test
	public void deve_ter_salario_maior_do_que_zero() {
		funcionario2.setSalario(funcionario.getSalario());
		assertTrue(funcionario2.getSalario().compareTo(BigDecimal.ZERO) > 0);
	}

	@Test
	public void deve_retornar_true_caso_salario_seja_menor_do_que_1000() {
		funcionario = EasyRandomFuncionario
				.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.SALARIO_PRECIA_SER_VALOR_MAIOR);
		assertTrue(
				ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.PRECISA_SER_UM_VALOR_MAIOR));
	}

	@Test
	public void deve_retornar_true_caso_salario_seja_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.SALARIO_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_endereco_seja_null() {
		funcionario = EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.ENDERECO_NULL);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test
	public void deve_retornar_true_caso_telefone_seja_null() {
		funcionario.setTelefones(null);
		assertTrue(ValidadorAnnotionsMsgErro.returnAnnotationMsgError(funcionario, Mensagem.VALOR_ESTA_NULLO));
	}

	@Test()
	public void deve_retornar_true_no_equals_para_serem_iguais() {
		EqualsVerifier.forClass(Funcionario.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS, Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED).verify();
	}

	/**
	 * Mostrar dados.
	 */
	@AfterClass
	public static void mostrarDados() {
		System.out.println(funcionario);
	}

}
