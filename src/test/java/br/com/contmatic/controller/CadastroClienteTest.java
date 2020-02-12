package br.com.contmatic.controller;

import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Endereco;
import br.com.contmatic.fixture.factory.GeradorCpf;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class CadastroClienteTest {

    private static Empresa loja;

    @BeforeClass
    public static void addDadosIniciais() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.fixture.factory");
        loja = Fixture.from(Empresa.class).gimme("valid");
    }

    @Test
    public void deve_cadastrarCliente() {
        CadastroCliente.cadastrarClienteNaLoja("lucas", "lucas@gmail.com", new DateTime(), Fixture.from(Endereco.class).gimme("valid"), GeradorCpf.gerardorRandomCpf(), loja);
        assertTrue(loja.getCliente().get(0).getNome().equalsIgnoreCase("lucas"));
    }
    
}
