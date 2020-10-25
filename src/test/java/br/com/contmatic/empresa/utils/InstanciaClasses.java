package br.com.contmatic.empresa.utils;

import static br.com.contmatic.enums.Cargo.REPOSITOR;
import static br.com.contmatic.services.utils.GeradorCnpj.gerardorRandomCnpj;
import static br.com.contmatic.services.utils.GeradorCpf.gerardorRandomCpf;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.DAYS;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;

public final class InstanciaClasses {

	private static Produto produtos;

	private static Endereco endereco;

	private static Faker faker;

	private InstanciaClasses() {
		super();
	}

	private static void criaFaker() {
		faker = new Faker();
	}

	public static Empresa criaEmpresa(Endereco endereco, boolean hasProduto) {
		criaFaker();
		if (hasProduto) {
			return new Empresa(faker.color().name(), faker.internet().emailAddress(), asList(produtos),
					gerardorRandomCnpj(), endereco);
		}
		return new Empresa(faker.pokemon().name(), faker.internet().emailAddress(), gerardorRandomCnpj(), endereco);
	}

	public static Endereco criaEndereco() {
		criaFaker();
		endereco = new Endereco(faker.address().streetName(), faker.address().streetPrefix(),
				String.valueOf(faker.number().numberBetween(00000001, 99999999)),
				faker.number().numberBetween(1, 10000), faker.address().cityName(), faker.address().firstName());
		return endereco;
	}

	public static Produto criaProduto() {
		criaFaker();
		return new Produto(faker.color().name(), BigDecimal.valueOf(faker.number().randomDouble(2, 1, 60)),
				faker.number().numberBetween(1, 30));
	}

	public static Cliente criaCliente(Endereco endereco) {
		criaFaker();
		return new Cliente(faker.name().firstName(), faker.internet().emailAddress(),
				new DateTime(faker.date().past(1, DAYS)), endereco, gerardorRandomCpf());
	}

	public static Funcionario criaFuncionario(Endereco endereco) {
		criaFaker();
		return new Funcionario(faker.name().lastName(), faker.internet().emailAddress(),
				BigDecimal.valueOf(faker.number().randomDouble(2, 500, 1000)), REPOSITOR,
				new DateTime(faker.date().past(1, DAYS)), endereco, gerardorRandomCpf());
	}

}
