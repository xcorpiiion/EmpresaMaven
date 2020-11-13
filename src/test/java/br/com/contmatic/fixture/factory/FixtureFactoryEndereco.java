package br.com.contmatic.fixture.factory;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Municipio;
import br.com.contmatic.endereco.Pais;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Random;

import static br.com.contmatic.constantes.Constante.*;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.Fixture.of;

public class FixtureFactoryEndereco implements TemplateLoader{

    public static Endereco enderecoValido() {
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        of(Endereco.class).addTemplate("valid", new Rule() {
            {
                add("rua", name());
                add("bairro", name());
                add("cep", cepRandom.toString());
                add("numero", new Random().nextInt(100000) + 1);
                add("municipio", new Municipio());
                add("pais", new Pais());

            }
        });
        return from(Endereco.class).gimme(VALID);
    }

    @Override
    public void load() {
        FixtureFactoryEndereco.enderecoValido();
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        final String RUA = "rua";
        final String BAIRRO = "bairro";
        final String CEP = "cep";
        final String NUMERO = "numero";
        final String PAIS = "pais";
        final String MUNICIPIO = "municipio";
        of(Endereco.class).addTemplate(VALID, new Rule() {
            {
                add(RUA, name());
                add(BAIRRO, name());
                add(CEP, cepRandom.toString());
                add(NUMERO, random(Integer.class, range(1, 10000)));

            }
        });
        of(Endereco.class).addTemplate(NUMERO_RESIDENCIA_LESS_THAN_ZERO).inherits(VALID, new Rule() {
            {
                add(NUMERO, random(Integer.class, range(-10000, -1)));
            }
        });
        of(Endereco.class).addTemplate(RUA_NULL).inherits(VALID, new Rule() {
            {
                add(RUA, null);
            }
        });
        of(Endereco.class).addTemplate(RUA_EMPTY).inherits(VALID, new Rule() {
            {
                add(RUA, "");
            }
        });
        of(Endereco.class).addTemplate(RUA_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(RUA, " ");
            }
        });
        of(Endereco.class).addTemplate(RUA_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(RUA, regex("AA"));
            }
        });
        of(Endereco.class).addTemplate(RUA_GREATER_50_CARACTER).inherits(VALID, new Rule() {
            {
                add(RUA, regex("FHSDÇFHSDLFKLSDJLFJSDÇFHSDHFSDNFSDLJKFKSDHFJSDFDHLKFJSNFJSFSDFSDFSDFSD"));
            }
        });
        of(Endereco.class).addTemplate(RUA_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(RUA, regex(NOME_WITH_SPECIAL_CARACTER));
            }
        });
        of(Endereco.class).addTemplate(CEP_NULL).inherits(VALID, new Rule() {
            {
                add(CEP, null);
            }
        });
        of(Endereco.class).addTemplate(CEP_EMPTY).inherits(VALID, new Rule() {
            {
                add(CEP, "");
            }
        });
        of(Endereco.class).addTemplate(CEP_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(CEP, " ");
            }
        });
        of(Endereco.class).addTemplate(CEP_LENGTH_DIFFERENCE_8).inherits(VALID, new Rule() {
            {
                add(CEP, cepRandom.append(new Random().nextInt(1) + 1000).toString());
            }
        });
        of(Endereco.class).addTemplate(CEP_CONTAINS_WORD, new Rule() {
            {
                add(CEP, cepRandom.append(new Random().nextInt(1) + 1000).append(firstName()).toString());
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_NULL).inherits(VALID, new Rule() {
            {
                add(BAIRRO, null);
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_EMPTY).inherits(VALID, new Rule() {
            {
                add(BAIRRO, "");
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(BAIRRO, " ");
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(BAIRRO, regex("AA"));
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_GREATER_50_CARACTER).inherits(VALID, new Rule() {
            {
                add(BAIRRO, regex("DASDASDLKASHJAHFHSDKLJFHKSDUFHSDK GHFSDGHIFSDUIFHISDHOFIUSDHOIFU"));
            }
        });
        of(Endereco.class).addTemplate(BAIRRO_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(BAIRRO, regex("DDS$#"));
            }
        });
        of(Endereco.class).addTemplate(PAIS_NULL).inherits(VALID, new Rule() {
            {
                add(MUNICIPIO, null);
            }
        });
        of(Endereco.class).addTemplate(MUNICIPIO_NULL).inherits(VALID, new Rule() {
            {
                add(PAIS, null);
            }
        });
    }
}
