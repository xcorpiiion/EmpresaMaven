import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;

public class PessoaTest {
	
	private String nome;
	
	private String email;
	
	private SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");
	
	@Before
	public void cadastrarDados() {
		nome = "";
		email = "";
		try {
			dataNascimento.parse("10/01/1997");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void nao_deve_aceitar_nome_null_ou_vazio() {
		assertFalse("O nome não é valido", nome == null || nome.isEmpty() || nome.trim().equals(""));
	}
	
	@Test
	public void nao_deve_aceitar_email_null_ou_vazio() {
		assertFalse("O email não é valido", email == null || email.isEmpty() || email.trim().equals(""));
		
	}
	
	@Test
	public void nao_deve_aceitar_data_nascimento_null() {
		assertNotNull(dataNascimento);
	}

}
