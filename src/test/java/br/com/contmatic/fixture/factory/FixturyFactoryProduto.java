package br.com.contmatic.fixture.factory;

import br.com.contmatic.empresa.Produto;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.util.Random;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.Fixture.of;

public class FixturyFactoryProduto implements TemplateLoader {

    public static Produto produtoValido() {
        of(Produto.class).addTemplate(VALID, new Rule() {
            {
                final String NOME = "nome";
                add(NOME, name());
                final String PRECO = "preco";
                add(PRECO, new BigDecimal((50 + Math.random() * 250)));
                final String ESTOQUE = "estoque";
                add(ESTOQUE, 10 + (new Random().nextInt(50)));
            }
        });
        return from(Produto.class).gimme(VALID);
    }

    @Override
    public void load() {
        FixturyFactoryProduto.produtoValido();
        final String NOME = "nome";
        of(Produto.class).addTemplate(NOME_NULL).inherits(VALID, new Rule() {
            {
                add(NOME, null);
            }
        });
        of(Produto.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        of(Produto.class).addTemplate(NOME_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(NOME, " ");
            }
        });
        of(Produto.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        of(Produto.class).addTemplate(NOME_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("AA"));
            }
        });
        of(Produto.class).addTemplate(NOME_GREATER_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("JHDDFJKSHFLKHSDKJFHKSDHLFKJSDHLFKHSDKFHKSDHFLKJSDKLJFSDJKLL"));
            }
        });
        of(Produto.class).addTemplate(NOME_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("NO#¨%¨"));
            }
        });
        final String PRECO = "preco";
        of(Produto.class).addTemplate(PRECO_NULL).inherits(VALID, new Rule() {
            {
                add(PRECO, null);
            }
        });
        of(Produto.class).addTemplate(PRECO_LESS_1).inherits(VALID, new Rule() {
            {
                add(PRECO, new BigDecimal(( -1 * (50 + Math.random() * 250))));
            }
        });
        of(Produto.class).addTemplate(ESTOQUE_LESS_0).inherits(VALID, new Rule() {
            {
                add(PRECO, new BigDecimal( -1 * (50 + (new Random().nextInt() * 100))));
            }
        });

    }
}
