package br.com.contmatic.fixture.factory;

import br.com.contmatic.constantes.Constante;
import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.HashSet;

import static br.com.contmatic.constantes.Constante.EMAIL_WITHOUT_PONTO_COM;
import static br.com.contmatic.constantes.Constante.*;
import static br.com.contmatic.fixture.factory.GeradorEmail.*;
import static br.com.six2six.fixturefactory.Fixture.of;

public class FixtureFactoryEmpresa implements TemplateLoader {

    @Override
    public void load() {
        final String VALID = "valid";
        final String NOME = "nome";
        final String EMAIL = "email";
        final String CNPJ = "cnpj";
        final String PRODUTO = "produtos";
        final String FUNCIONARIO = "funcionarios";
        final String CLIENTE = "clientes";
        of(Empresa.class).addTemplate(VALID, new Rule() {
            {
                add(NOME, name());
                add(EMAIL, new Faker().internet().emailAddress());
                add(CNPJ, cnpj());
                final String ENDERECO = "enderecos";
                add(ENDERECO, new HashSet<>());
                final String TELEFONE = "telefones";
                add(TELEFONE, new HashSet<>());
                add(PRODUTO, new ArrayList<>());
                add(FUNCIONARIO, new ArrayList<>());
                add(CLIENTE, new ArrayList<>());
            }
        });
        of(Empresa.class).addTemplate(NOME_NULL).inherits(VALID, new Rule() {
            {
                add(NOME, null);
            }
        });
        of(Empresa.class).addTemplate(NOME_EMPTY).inherits(VALID, new Rule() {
            {
                add(NOME, "");
            }
        });
        of(Empresa.class).addTemplate(NOME_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(NOME, " ");
            }
        });
        of(Empresa.class).addTemplate(NOME_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("zz"));
            }
        });
        of(Empresa.class).addTemplate(NOME_GREATER_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("asdfeuhufhdusfhusdfuisdghuifgsduiogfusdgfuisdguifg"));
            }
        });
        of(Empresa.class).addTemplate(NOME_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(NOME, regex("fdsfsdfds%%"));
            }
        });
        of(Empresa.class).addTemplate(EMAIL_NULL).inherits(VALID, new Rule() {
            {
                add(EMAIL, null);
            }
        });
        of(Empresa.class).addTemplate(EMAIL_EMPTY).inherits(VALID, new Rule() {
            {
                add(EMAIL, "");
            }
        });
        of(Empresa.class).addTemplate(EMAIL_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(EMAIL, " ");
            }
        });
        of(Empresa.class).addTemplate(EMAIL_INVALID).inherits(VALID, new Rule() {
            {
                add(EMAIL, null);
            }
        });
        of(Empresa.class).addTemplate(EMAIL_LESS_10_CARACTERES).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_LESS_10_CARACTER));
            }
        });
        of(Empresa.class).addTemplate(EMAIL_GREATER_100_CARACTERES).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_GREATER_100_CARACTER));
            }
        });
        of(Empresa.class).addTemplate(EMAIL_WITH_BLANK_SPACE_IN_WORD).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITH_BLANK_SPACE));
            }
        });
        of(Empresa.class).addTemplate(Constante.EMAIL_WITH_NUMBER_AFTER_ARROBA).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITH_NUMBER_AFTER_ARROBA));
            }
        });
        of(Empresa.class).addTemplate("emailWithoutArroba").inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(EMAIL_WITHOUT_ARROBA_CARACTER));
            }
        });
        of(Empresa.class).addTemplate(EMAIL_WITHOUT_PONTO_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_PONTO_COM));
            }
        });
        of(Empresa.class).addTemplate(Constante.EMAIL_WITHOUT_COM).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITHOUT_COM));
            }
        });
        of(Empresa.class).addTemplate(Constante.EMAIL_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(EMAIL, regex(GeradorEmail.EMAIL_WITH_SPECIAL_CARACTER));
            }
        });
        of(Empresa.class).addTemplate(CNPJ_NULL).inherits(VALID, new Rule() {
            {
                add(CNPJ, null);
            }
        });
        of(Empresa.class).addTemplate(CNPJ_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(CNPJ, " ");
            }
        });
        of(Empresa.class).addTemplate(CNPJ_CONTAINS_WORD).inherits(VALID, new Rule() {
            {
                add(CNPJ, "dsd454sd5s");
            }
        });
        of(Empresa.class).addTemplate(CNPJ_WRONG_SIZE).inherits(VALID, new Rule() {
            {
                add(CNPJ, "123456879");
            }
        });
        of(Empresa.class).addTemplate(ENDERECO_NULL).inherits(VALID, new Rule() {
            {
                add("endereco", null);
            }
        });
        of(Empresa.class).addTemplate(PRODUTO_NULL).inherits(VALID, new Rule() {
            {
                add(PRODUTO, null);
            }
        });
        of(Empresa.class).addTemplate(FUNCIONARIO_NULL).inherits(VALID, new Rule() {
            {
                add(FUNCIONARIO, null);
            }
        });
        of(Empresa.class).addTemplate(CLIENTE_NULL).inherits(VALID, new Rule() {
            {
                add(CLIENTE, null);
            }
        });

    }

}
