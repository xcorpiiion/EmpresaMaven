package br.com.contmatic.empresa.utils;

import static br.com.contmatic.services.utils.GeradorCnpj.cnpjGenerator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.enums.Cargo;
import br.com.contmatic.services.utils.GeradorCpf;

public class InstanciaClasses {

	private static Produto produtos;

	private static Endereco endereco;

	private static Faker faker;

	private InstanciaClasses() {

	}

	private static void criaFaker() {
		faker = new Faker();
	}

	public static Empresa criaEmpresa(Endereco endereco, boolean hasProduto) {
		criaFaker();
		if (hasProduto) {
			return new Empresa(faker.cat().name(), faker.internet().emailAddress(), faker.date().past(1, TimeUnit.DAYS),
					Arrays.asList(produtos), cnpjGenerator(), endereco);
		}
		return new Empresa(faker.pokemon().name(), faker.internet().emailAddress(), faker.date().past(1, TimeUnit.DAYS),
				cnpjGenerator(), endereco);
	}

	public static Endereco criaEndereco() {
		criaFaker();
		endereco = new Endereco(faker.address().streetName(), faker.address().streetPrefix(),
				String.valueOf(faker.number().numberBetween(00000001, 99999999)), faker.address().buildingNumber(),
				faker.address().cityName(), faker.address().firstName());
		return endereco;
	}

	public static Produto criaProduto() {
		criaFaker();
		return new Produto(faker.book().genre(), BigDecimal.valueOf(faker.number().randomDouble(2, 1, 60)),
				faker.number().numberBetween(1, 30));
	}

	public static Cliente criaCliente(Endereco endereco) {
		criaFaker();
		return new Cliente(faker.name().firstName(), faker.internet().emailAddress(),
				faker.date().past(1, TimeUnit.DAYS), endereco, GeradorCpf.gerardorRandomCpf());
	}

	public static Funcionario criaFuncionario(Endereco endereco) {
		criaFaker();
		return new Funcionario(faker.name().firstName(), faker.internet().emailAddress(),
				BigDecimal.valueOf(faker.number().randomDouble(2, 500, 1000)), Cargo.REPOSITOR,
				faker.date().past(1, TimeUnit.DAYS), endereco, GeradorCpf.gerardorRandomCpf());
	}

}
