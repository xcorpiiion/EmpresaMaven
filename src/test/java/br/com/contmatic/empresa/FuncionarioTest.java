package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaFuncionario;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static java.util.concurrent.TimeUnit.DAYS;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.github.javafaker.Faker;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

	private static Funcionario funcionario;

	private static Endereco endereco;

	private static Faker faker;

	@BeforeClass
	public static void addDadosIniciais() {
		faker = new Faker();
		endereco = criaEndereco();
		funcionario = criaFuncionario(endereco);
	}
	
	/* testa nome */

	@Test
	public void deve_aceitar_nome_valido() {
		final String firstName = faker.name().firstName();
		funcionario.setNome(firstName);
		assertEquals(firstName, funcionario.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_mais_3_caracteres() {
		funcionario.setNome("aa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_apenas_aceitar_nome_com_menos_30_caracteres() {
		funcionario.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
				+ "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_numero() {
		funcionario.setNome(faker.name().firstName() + "1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		funcionario.setNome(faker.name().firstName() + "#$%");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_empty() {
		funcionario.setNome(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_vazio() {
		funcionario.setNome(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_null() {
		funcionario.setNome(null);
	}

	/* Teste cpf */

	@Test
    public void deve_aceitar_cpf_valido() {
        final String cpfValido = gerardorRandomCpf();
       funcionario.setCpf(cpfValido);
        assertEquals(cpfValido, funcionario.getCpf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_menos_11_numeros() {
        String cpf = "1234567890123";
       funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_mais_11_numeros() {
        String cpf = "123456789012345";
       funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_letras() {
        String cpf = "a";
        funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_letras_e_numeros_juntos() {
        String cpf = "1234567890123a";
       funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_espaco_entre_numeros() {
        StringBuilder cpf = new StringBuilder();
        cpf.append(faker.number().numberBetween(1, 10)).append(" ");
       funcionario.setCpf(cpf.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_vazio() {
        String cpf = EMPTY;
       funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_com_espaco_em_branco() {
        String cpf = SPACE;
       funcionario.setCpf(cpf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cpf_null() {
       funcionario.setCpf(null);
    }

	/* Testa email */

	@Test
	public void deve_aceitar_email_valido() {
		final String emailAddress = faker.internet().emailAddress();
		funcionario.setEmail(emailAddress);
		assertEquals(emailAddress, funcionario.getEmail());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_sem_arroba() {
		funcionario.setEmail(faker.name().firstName());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_com_caracteres_especiais() {
		funcionario.setEmail(faker.name().firstName() + "%$");
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_com_espaco_entre_palavras() {
		funcionario.setEmail("aa " + faker.internet().emailAddress());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_email_sem_ponto() {
		funcionario.setEmail("a@gmail");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio_empty() {
		funcionario.setEmail(SPACE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_vazio() {
		funcionario.setEmail(EMPTY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_email_null() {
		funcionario.setEmail(null);
	}

	/* Teste endereço */

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_endereco_null() {
		funcionario.setEndereco(null);
	}

	/* testa data de nascimento */

	@Test
	public void deve_settar_data_nascimento() {
		DateTime past = new DateTime(faker.date().past(1, DAYS));
		funcionario.setDataNascimento(past);
		assertEquals(past, funcionario.getDataNascimento());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_de_nascimento_antes_de_1920() {
		DateTime past = new DateTime(1919, 1, 1, 0, 0);
		funcionario.setDataNascimento(past);
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_nascimento_depois_da_hora_atual() {
		DateTime past = new DateTime(2050, 1, 1, 0, 0);
		funcionario.setDataNascimento(past);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_nascimento_null() {
		funcionario.setDataNascimento(null);
	}

	/* Testa data entrada */

	@Test
	public void deve_settar_data_entrada() {
		DateTime past = new DateTime();
		funcionario.setDataEntrada(past);
		assertEquals(past, funcionario.getDataEntrada());
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_entrada_menor_do_que_data_criacao_empresa() {
		DateTime past = new DateTime(1997, 1, 1, 0, 0);
		funcionario.setDataEntrada(past);
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_entrada_depois_da_hora_atual() {
		DateTime past = new DateTime(2050, 1, 1, 0, 0);
		funcionario.setDataEntrada(past);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_entrada_null() {
		funcionario.setDataEntrada(null);
	}
	
	/* Testa data saida */
	
	@Test
	public void deve_settar_data_saida() {
		DateTime past = new DateTime();
		funcionario.setDataEntrada(past);
		funcionario.setDataSaida(past);
		assertEquals(past, funcionario.getDataEntrada());
	}
	
	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_saida_menor_do_que_data_criacao_empresa() {
		DateTime past = new DateTime(1997, 1, 1, 0, 0);
		funcionario.setDataSaida(past);
	}
	
	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_saida_depois_da_hora_atual() {
		DateTime past = new DateTime(2050, 1, 1, 0, 0);
		funcionario.setDataSaida(past);
	}
	
	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_data_saida_menor_do_que_data_entrada() {
		DateTime past = new DateTime(1900, 1, 1, 0, 0);
		funcionario.setDataSaida(past);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_data_cadastro_null() {
		funcionario.setDataSaida(null);
	}
	
	/* Testa equals e hashcode */
	
	@Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Funcionario.class).usingGetClass().suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
	
	@AfterClass
	public static void imprimirFuncionario() {
		System.out.println(funcionario);
	}
	
}
