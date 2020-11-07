package br.com.contmatic.testeAllClasses;

import br.com.contmatic.empresa.ClienteTest;
import br.com.contmatic.empresa.EmpresaTest;
import br.com.contmatic.empresa.FuncionarioTest;
import br.com.contmatic.empresa.ProdutoTest;
import br.com.contmatic.endereco.EnderecoTest;
import br.com.contmatic.endereco.MunicipioTest;
import br.com.contmatic.endereco.PaisTest;
import br.com.contmatic.telefone.TelefoneTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class TestAllClasses.
 */
@RunWith(Suite.class)
@SuiteClasses({ EmpresaTest.class, FuncionarioTest.class, ClienteTest.class, EnderecoTest.class, ProdutoTest.class, TelefoneTest.class,
        PaisTest.class, MunicipioTest.class})
public class TestAllClasses {

}
