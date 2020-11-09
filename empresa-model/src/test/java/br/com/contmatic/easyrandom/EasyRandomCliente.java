package br.com.contmatic.easyrandom;

import br.com.contmatic.easyrandom.enums.EnumStringInvalida;
import br.com.contmatic.easyrandom.enums.TipoDadoParaTesteEndereco;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.endereco.Endereco;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static br.com.contmatic.utils.GeradorCpf.gerardorRandomCpf;
import static io.github.benas.randombeans.FieldPredicates.named;
import static java.util.concurrent.TimeUnit.DAYS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class EasyRandomCliente {

    private static final String DATA_NASCIMENTO = "dataNascimento";

    private static final String EMAIL = "email";

    private static final String ENDERECO = "endereco";

    private static final String CPF = "cpf";

    private static final String NOME = "nome";

    private static final String TODOS = "todos";

    private EasyRandomCliente() {

    }

    public static Cliente camposValido() {
        Faker faker = new Faker();
        EasyRandomParameters parameters = new EasyRandomParameters();
        dadosClienteValidosExceto(parameters, faker);
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(Cliente.class);
    }

    public static Cliente validadorEasyRandomCliente(EnumStringInvalida yourStringFieldToValidate) {
        Faker faker = new Faker();
        EasyRandomParameters parameters = new EasyRandomParameters();
        ValidadorString validadorString = new EasyRandomStringInvalida();
        dadosClienteValidosExceto(parameters, faker);
        nome(parameters, NOME, EMPTY, validadorString, yourStringFieldToValidate);
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(Cliente.class);
    }

    private static void dadosClienteValidosExceto(EasyRandomParameters parameters, Faker faker) {
        cpf(parameters, gerardorRandomCpf());
        parameters.randomize(named(NOME), () -> faker.pokemon().name());
        parameters.randomize(named(CPF), () -> gerardorRandomCpf());
        parameters.randomize(named(EMAIL), () -> faker.internet().emailAddress());
        parameters.randomize(named(DATA_NASCIMENTO), () -> faker.date().future(1996, DAYS));
        endereco(parameters, EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
    }

    private static void nome(EasyRandomParameters parameters, String nomeCampo, String valor,
                             ValidadorString validadorString, EnumStringInvalida stringInvalida) {
        if (StringUtils.isEmpty(nomeCampo)) {
            parameters.randomize(named(NOME), () -> valor);
        } else if (nomeCampo.equalsIgnoreCase(NOME)) {
            parameters.randomize(named(NOME), () -> validadorString.nome(stringInvalida));
        }
    }

    private static void email(EasyRandomParameters parameters, String emailCliente) {
        parameters.randomize(named(EMAIL), () -> emailCliente);
    }

    private static void cpf(EasyRandomParameters parameters, String cpf) {
        parameters.randomize(named(CPF), () -> cpf);
    }

    private static void dataNascimento(EasyRandomParameters parameters, Date dataNascimento) {
        parameters.randomize(Date.class, () -> dataNascimento);
    }

    private static void endereco(EasyRandomParameters parameters, Endereco endereco) {
        parameters.randomize(Endereco.class, () -> endereco);
    }
}
