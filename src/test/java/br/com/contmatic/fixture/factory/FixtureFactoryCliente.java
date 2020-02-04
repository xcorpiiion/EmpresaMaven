package br.com.contmatic.fixture.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import br.com.contmatic.empresa.Cliente;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FixtureFactoryCliente implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Cliente.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("email", "teste@gmail.com");
                add("dataNascimento", new Date());
                add("dinheiroCarteira", new BigDecimal(4000.00 + (new Random().nextDouble() * (5000 - 8500))));
                add("carrinhoProduto", new ArrayList<>());
                add("produtosComprados", new ArrayList<>());
                add("endereco", FixtureFactoryEndereco.enderecoValido());
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        Fixture.of(Cliente.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailNull").inherits("valid", new Rule() {
            {
                add("email", null);
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailEmpty").inherits("valid", new Rule() {
            {
                add("email", "");
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailBlankSpace").inherits("valid", new Rule() {
            {
                add("email", " ");
            }
        });
        Fixture.of(Cliente.class).addTemplate("emailInvalid").inherits("valid", new Rule() {
            {
                add("email", firstName());
            }
        });
        Fixture.of(Cliente.class).addTemplate("enderecoNull").inherits("valid", new Rule() {
            {
                add("endereco", null);
            }
        });

    }

}
