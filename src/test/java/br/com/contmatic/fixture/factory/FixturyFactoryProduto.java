package br.com.contmatic.fixture.factory;

import java.math.BigDecimal;
import java.util.Random;
import br.com.contmatic.empresa.Produto;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FixturyFactoryProduto implements TemplateLoader {
    
    public static Produto produtoValido() {
        Fixture.of(Produto.class).addTemplate("valid", new Rule() {
            {
                add("nome", name());
                add("preco", new BigDecimal(50.00 + (new Random().nextDouble() * (100 - 250))));
                add("estoque", 10 + (new Random().nextInt(50)));
            }
        });
        return Fixture.from(Produto.class).gimme("valid");
    }
    
    @Override
    public void load() {
        FixturyFactoryProduto.produtoValido();
        Fixture.of(Produto.class).addTemplate("nomeNull").inherits("valid", new Rule() {
            {
                add("nome", null);
            }
        });
        Fixture.of(Produto.class).addTemplate("nomeEmpty").inherits("valid", new Rule() {
            {
                add("nome", "");
            }
        });
        Fixture.of(Produto.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Produto.class).addTemplate("nomeBlankSpace").inherits("valid", new Rule() {
            {
                add("nome", " ");
            }
        });
        Fixture.of(Produto.class).addTemplate("precoNull").inherits("valid", new Rule() {
            {
                add("preco", null);
            }
        });
        Fixture.of(Produto.class).addTemplate("precoLess1").inherits("valid", new Rule() {
            {
                add("preco", new BigDecimal(-50.00 + (new Random().nextDouble() * (-100 + -250))));
            }
        });
        Fixture.of(Produto.class).addTemplate("estoqueLess0").inherits("valid", new Rule() {
            {
                add("preco", new BigDecimal(-50 + (new Random().nextInt() * (-100 + -250))));
            }
        });

    }
}
