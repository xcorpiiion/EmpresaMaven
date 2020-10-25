package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.InstanciaClasses.criaCliente;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEmpresa;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaEndereco;
import static br.com.contmatic.empresa.utils.InstanciaClasses.criaProduto;
import static br.com.contmatic.services.utils.GeradorCnpj.gerardorRandomCnpj;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.DAYS;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.github.javafaker.Faker;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

    private static Produto produto;

    private Funcionario funcionario;

    private static Cliente cliente;

    private static Endereco endereco;

    private static Empresa empresa;

    private static Faker faker;

    @BeforeClass
    public static void cadastraEmpresa() {
        faker = new Faker();
        produto = criaProduto();
        endereco = criaEndereco();
        cliente = criaCliente(endereco);
        empresa = criaEmpresa(endereco, false);
    }

    /* testa nome */

    @Test
    public void deve_aceitar_nome_valido() {
        final String firstName = faker.name().firstName();
        empresa.setNome(firstName);
        assertEquals(firstName, empresa.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_apenas_aceitar_nome_com_mais_3_caracteres() {
        empresa.setNome("aa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_apenas_aceitar_nome_com_menos_70_caracteres() {
        empresa.setNome("aawwwwwwwertgfdswqafcvfgtdsyhtru"
                + "aawwwwwwwertgfdswqafcvfgtdsyhtruaawwwwwwwertgfdswqafcvfgtdsyhtru");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_nome_empty() {
        empresa.setNome(EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_nome_vazio() {
        empresa.setNome(SPACE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_nome_null() {
        empresa.setNome(null);
    }

    /* testa cnpj */

    @Test
    public void deve_aceitar_cnpj_valido() {
        final String cnpjValido = gerardorRandomCnpj();
        empresa.setCnpj(cnpjValido);
        assertEquals(cnpjValido, empresa.getCnpj());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_menos_14_numeros() {
        String cnpj = "1234567890123";
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_mais_14_numeros() {
        String cnpj = "123456789012345";
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_letras() {
        String cnpj = "a";
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_letras_e_numeros_juntos() {
        String cnpj = "1234567890123a";
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_espaco_entre_numeros() {
        StringBuilder cnpj = new StringBuilder();
        cnpj.append(faker.number().numberBetween(1, 10)).append(" ");
        empresa.setCnpj(cnpj.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_vazio() {
        String cnpj = EMPTY;
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_com_espaco_em_branco() {
        String cnpj = SPACE;
        empresa.setCnpj(cnpj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_cnpj_null() {
        empresa.setCnpj(null);
    }

    /* testa email */

    @Test
    public void deve_aceitar_email_valido() {
        final String emailAddress = faker.internet().emailAddress();
        empresa.setEmail(emailAddress);
        assertEquals(emailAddress, empresa.getEmail());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_email_sem_arroba() {
        empresa.setEmail(faker.name().firstName());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_email_com_caracteres_especiais() {
        empresa.setEmail(faker.name().firstName() + "%$");
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_email_com_espaco_entre_palavras() {
        empresa.setEmail("aa " + faker.internet().emailAddress());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_email_sem_ponto() {
        empresa.setEmail("a@gmail");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_vazio_empty() {
        empresa.setEmail(SPACE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_vazio() {
        empresa.setEmail(EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_email_null() {
        empresa.setEmail(null);
    }

    /* testa produtos */

    @Test
    public void deve_criar_empresa_com_produtos() {
        empresa = criaEmpresa(endereco, true);
        assertFalse(empresa.getProduto().isEmpty());
    }

    @Test
    public void deve_settar_produto() {
        empresa.setProduto(asList(produto));
        assertFalse(empresa.getProduto().isEmpty());
    }

    @Test()
    public void nao_deve_aceitar_produto_null() {
        assertNotNull("O produto esta null", produto);
    }

    /* testa funcionarios */

    @Test
    public void deve_settar_o_funcionario() {
        empresa.setFuncionario(asList(funcionario));
        assertFalse(empresa.getFuncionario().isEmpty());
    }

    /* testa clientes */

    @Test
    public void deve_settar_o_cliente() {
        empresa.setCliente(asList(cliente));
        assertFalse(empresa.getCliente().isEmpty());
    }

    /* testa endereco */

    @Test
    public void nao_deve_aceitar_endereco_null() {
        assertNotNull(empresa.getEndereco());
    }

    /* testa data */

    @Test
    public void deve_settar_data_criacao() {
        DateTime past = new DateTime(faker.date().past(1, DAYS));
        empresa.setDataCriacao(past);
        assertEquals(past, empresa.getDataCriacao());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_data_criacao_antes_de_1998() {
        DateTime past = new DateTime(1997, 1, 1, 0, 0);
        empresa.setDataAlteracao(past);
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_data_criacao_depois_da_hora_atual() {
        DateTime past = new DateTime(2050, 1, 1, 0, 0);
        empresa.setDataAlteracao(past);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_criacao_null() {
        empresa.setDataAlteracao(null);
    }

    @Test
    public void deve_settar_data_alteracao() {
        DateTime past = new DateTime(faker.date().past(1, DAYS));
        empresa.setDataAlteracao(past);
        assertEquals(past, empresa.getDataAlteracao());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_data_alteracao_antes_de_1998() {
        DateTime past = new DateTime(1997, 1, 1, 0, 0);
        empresa.setDataAlteracao(past);
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_data_alteracao_depois_da_hora_atual() {
        DateTime past = new DateTime(2050, 1, 1, 0, 0);
        empresa.setDataAlteracao(past);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_alteracao_null() {
        empresa.setDataAlteracao(null);
    }

    /* testa equals and hashcode */

    @Test()
    public void deve_retornar_true_no_equals_para_serem_iguais() {
        forClass(Empresa.class).usingGetClass()
                .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    /* mostra tostring */

    @AfterClass
    public static void mostrar_dados_empresa() {
        System.out.println(empresa);
    }

}
