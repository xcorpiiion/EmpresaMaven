package br.com.contmatic.testeAllClasses;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.empresa.ClienteTest;
import br.com.contmatic.empresa.EmpresaTest;
import br.com.contmatic.empresa.FuncionarioTest;
import br.com.contmatic.empresa.ProdutoTest;
import br.com.contmatic.endereco.EnderecoTest;
import br.com.contmatic.telefone.TelefoneTest;

/**
 * The Class TestAllClasses.
 */
@RunWith(Suite.class)
@SuiteClasses({ EmpresaTest.class, FuncionarioTest.class, ClienteTest.class, EnderecoTest.class, ProdutoTest.class, TelefoneTest.class })
public class TestAllClasses {

}
