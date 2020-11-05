package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.ESTOQUE_LESS_0;
import static br.com.contmatic.constantes.Constante.NOME_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.NOME_EMPTY;
import static br.com.contmatic.constantes.Constante.NOME_GREATER_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.NOME_NULL;
import static br.com.contmatic.constantes.Constante.NOME_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.PRECO_LESS_1;
import static br.com.contmatic.constantes.Constante.PRECO_NULL;

import java.math.BigDecimal;
import java.util.Random;

import br.com.contmatic.empresa.Produto;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class FixturyFactoryProduto.
 */
public class FixturyFactoryProduto implements TemplateLoader {
    
    /**
     * Produto valido.
     *
     * @return the produto
     */
    public static Produto produtoValido() {
        final String VALID = "valid";
        Fixture.of(Produto.class).addTemplate(VALID, new Rule() {
            {
                final String NOME = "nome";
                add(NOME, name());
                final String PRECO = "preco";
                add(PRECO, new BigDecimal((50 + Math.random() * 250)));
                final String ESTOQUE = "estoque";
                add(ESTOQUE, 10 + (new Random().nextInt(50)));
            }
        });
        return Fixture.from(Produto.class).gimme(VALID);
    }
    
    /**
     * Load.
     */
    @Override
    public void load() {
        FixturyFactoryProduto.produtoValido();
        final String VALID = "valid";
        final String NOME = "nome";
        Fixture.of(Produto.class).addTemplate(NOME_NULL).inherits(VALID, new Rule() {
            {
                add(NOME, null);
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(NOME, " ");
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("AA"));
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_GREATER_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("JHDDFJKSHFLKHSDKJFHKSDHLFKJSDHLFKHSDKFHKSDHFLKJSDKLJFSDJKLL"));
            }
        });
        Fixture.of(Produto.class).addTemplate(NOME_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("NO#¨%¨"));
            }
        });
        final String PRECO = "preco";
        Fixture.of(Produto.class).addTemplate(PRECO_NULL).inherits(VALID, new Rule() {
            {
                add(PRECO, null);
            }
        });
        Fixture.of(Produto.class).addTemplate(PRECO_LESS_1).inherits(VALID, new Rule() {
            {
                add(PRECO, new BigDecimal(( -1 * (50 + Math.random() * 250))));
            }
        });
        Fixture.of(Produto.class).addTemplate(ESTOQUE_LESS_0).inherits(VALID, new Rule() {
            {
                add(PRECO, new BigDecimal( -1 * (50 + (new Random().nextInt() * 100))));
            }
        });

    }
}
