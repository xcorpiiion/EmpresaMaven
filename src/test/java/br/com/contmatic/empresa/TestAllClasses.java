package br.com.contmatic.empresa;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmpresaTest.class, FuncionarioTest.class, ClienteTest.class, EnderecoTest.class, LoginTest.class
	, ProdutoTest.class, CadastroClienteTest.class})
public class TestAllClasses {

}