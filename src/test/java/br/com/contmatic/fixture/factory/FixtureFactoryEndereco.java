package br.com.contmatic.fixture.factory;

import static br.com.contmatic.constantes.Constante.BAIRRO_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.BAIRRO_EMPTY;
import static br.com.contmatic.constantes.Constante.BAIRRO_GREATER_50_CARACTER;
import static br.com.contmatic.constantes.Constante.BAIRRO_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.BAIRRO_NULL;
import static br.com.contmatic.constantes.Constante.BAIRRO_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.CEP_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.CEP_CONTAINS_WORD;
import static br.com.contmatic.constantes.Constante.CEP_EMPTY;
import static br.com.contmatic.constantes.Constante.CEP_LENGTH_DIFFERENCE_8;
import static br.com.contmatic.constantes.Constante.CEP_NULL;
import static br.com.contmatic.constantes.Constante.CIDADE_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.CIDADE_CONTAINS_NUMBER;
import static br.com.contmatic.constantes.Constante.CIDADE_EMPTY;
import static br.com.contmatic.constantes.Constante.CIDADE_GREATER_50_CARACTER;
import static br.com.contmatic.constantes.Constante.CIDADE_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.CIDADE_NULL;
import static br.com.contmatic.constantes.Constante.CIDADE_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.ESTADO_NULL;
import static br.com.contmatic.constantes.Constante.NOME_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.NUMERO_RESIDENCIA_LESS_THAN_ZERO;
import static br.com.contmatic.constantes.Constante.RUA_BLANK_SPACE;
import static br.com.contmatic.constantes.Constante.RUA_EMPTY;
import static br.com.contmatic.constantes.Constante.RUA_GREATER_50_CARACTER;
import static br.com.contmatic.constantes.Constante.RUA_LESS_3_CARACTER;
import static br.com.contmatic.constantes.Constante.RUA_NULL;
import static br.com.contmatic.constantes.Constante.RUA_WITH_SPECIAL_CARACTER;
import static br.com.contmatic.constantes.Constante.VALID;
import static br.com.six2six.fixturefactory.Fixture.of;

import java.util.Random;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EstadosBrasil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;


/**
 * The Class FixtureFactoryEndereco.
 */
public class FixtureFactoryEndereco implements TemplateLoader{

    /**
     * Endereco valido.
     *
     * @return the endereco
     */
    public static Endereco enderecoValido() {
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        of(Endereco.class).addTemplate("valid", new Rule() {
            {
                add("rua", name());
                add("bairro", name());
                add("cep", cepRandom.toString());
                add("numeroResidencia", new Random().nextInt(100000) + 1);
                add("cidade", firstName());
                add("estado", EstadosBrasil.values()[new Random().nextInt(EstadosBrasil.values().length)]);
                
            }
        });
        return Fixture.from(Endereco.class).gimme(VALID);
    }
    
    /**
     * Load.
     */
    @Override
    public void load() {
        FixtureFactoryEndereco.enderecoValido();
        StringBuilder cepRandom = new StringBuilder();
        cepRandom.append(new Random().nextInt(90000000) + 10000000);
        int randomEstadoName = new Random().nextInt(EstadosBrasil.values().length);
        final String RUA = "rua";
        final String BAIRRO = "bairro";
        final String CEP = "cep";
        final String NUMERO_RESIDENCIA = "numeroResidencia";
        final String CIDADE = "cidade";
        final String ESTADO = "estado";
        of(Endereco.class).addTemplate(VALID, new Rule() {
            {
                add(RUA, name());
                add(BAIRRO, name());
                add(CEP, cepRandom.toString());
                add(NUMERO_RESIDENCIA, random(Integer.class, range(1, 10000)));
                add(CIDADE, firstName());
                add(ESTADO, EstadosBrasil.values()[randomEstadoName]);
                
            }
        });
        final String VALID = "valid";
        of(Endereco.class).addTemplate(CIDADE_NULL).inherits(VALID, new Rule() {
            {
                add(CIDADE, null);
            }
        });
        of(Endereco.class).addTemplate(CIDADE_EMPTY).inherits(VALID, new Rule() {
            {
                add(CIDADE, "");
            }
        });
        of(Endereco.class).addTemplate(CIDADE_BLANK_SPACE).inherits(VALID, new Rule() {
            {
                add(CIDADE, " ");
            }
        });
        of(Endereco.class).addTemplate(CIDADE_CONTAINS_NUMBER).inherits(VALID, new Rule() {
            {
                add(CIDADE, random(Integer.class, range(1, 100)).toString());
            }
        });
        of(Endereco.class).addTemplate(CIDADE_LESS_3_CARACTER).inherits(VALID, new Rule() {
            {
                add(CIDADE, regex("SD"));
            }
        });
        of(Endereco.class).addTemplate(CIDADE_GREATER_50_CARACTER).inherits(VALID, new Rule() {
            {
                add(CIDADE, regex("dhjfksdfsdfhsdhfjbhsdjkbfjklsdbjfsdblkhfsh"));
            }
        });
        of(Endereco.class).addTemplate(CIDADE_WITH_SPECIAL_CARACTER).inherits(VALID, new Rule() {
            {
                add(CIDADE, regex("%$%"));
            }
        });
        of(Endereco.class).addTemplate(ESTADO_NULL).inherits(VALID, new Rule() {
            {
                add(ESTADO, null);
            }
        });
        of(Endereco.class).addTemplate(NUMERO_RESIDENCIA_LESS_THAN_ZERO).inherits(VALID, new Rule() {
            {
                add(NUMERO_RESIDENCIA, random(Integer.class, range(-10000, -1)));
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
    }  
}
