package br.com.contmatic.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.constantes.easyrandom.EasyRandomCliente;
import br.com.contmatic.constantes.easyrandom.EasyRandomEmpresa;
import br.com.contmatic.constantes.easyrandom.EasyRandomEndereco;
import br.com.contmatic.constantes.easyrandom.EasyRandomFuncionario;
import br.com.contmatic.constantes.easyrandom.EasyRandomProduto;
import br.com.contmatic.constantes.easyrandom.EasyRandomTelefone;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteCliente;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteEmpresa;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteEndereco;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteFuncionario;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteProduto;
import br.com.contmatic.constantes.easyrandom.TipoDadoParaTesteTelefone;
import br.com.contmatic.empresa.Empresa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaServiceTest {

    private static EmpresaService empresaService;

    private static Empresa staticEmpresa;

    @BeforeClass
    public static void easyRandomDados() {
        dadosEmpresa();
        empresaService.save(staticEmpresa);
    }

    private static void dadosEmpresa() {
        staticEmpresa = new Empresa();
        empresaService = new EmpresaService();
        staticEmpresa = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.VALIDO);
        staticEmpresa.setFuncionario(Arrays.asList(EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO)));
        staticEmpresa.getFuncionario().get(0).setTelefones(new HashSet<>());
        staticEmpresa.getFuncionario().get(0).getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        staticEmpresa.setCliente(Arrays.asList(EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.VALIDO)));
        staticEmpresa.getCliente().get(0).setProdutosComprados(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        staticEmpresa.getCliente().get(0).setCarrinhoProdutos(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        staticEmpresa.getCliente().get(0).setTelefones(new HashSet<>());
        staticEmpresa.getCliente().get(0).getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        staticEmpresa.setProduto(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        staticEmpresa.setTelefones(new HashSet<>());
        staticEmpresa.getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        staticEmpresa.setEndereco(new HashSet<>());
        staticEmpresa.getEndereco().add(EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
    }
	
    @Test
    public void deve_salvar_empresa() {
        try {
            dadosEmpresa();
            empresaService.save(staticEmpresa);
            assertEquals(staticEmpresa, empresaService.findById(staticEmpresa.getCnpj()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void deve_retornar_null_ao_salvar_empresa() {
        empresaService.save(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_dar_exception_ao_tentar_cadastrar_empresa_com_mesmo_cnpj() {
    	dadosEmpresa();
    	empresaService.save(staticEmpresa);
    	empresaService.save(staticEmpresa);
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertNotNull(empresa);
    }

    @Test
    public void deve_retornar_null_ao_procurar_empresa_pelo_cnpj() {
        Empresa empresa = empresaService.findById("1234567890123456");
        Assert.assertNull(empresa);
    }

    @Test()
    public void deve_retornar_todas_as_empresa() {
        Assert.assertNotNull(empresaService.findAll());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_cnpj() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCnpj(), empresa.getCnpj());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_nome() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getNome(), empresa.getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_email() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getEmail(), empresa.getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_telefone() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getTelefones(), empresa.getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_endereco() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getEndereco(), empresa.getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_produto() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getProduto(), empresa.getProduto());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario(), empresa.getFuncionario());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_nome() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getNome(), empresa.getFuncionario().get(0).getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_email() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getEmail(), empresa.getFuncionario().get(0).getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_cpf() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getCpf(), empresa.getFuncionario().get(0).getCpf());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_dataNascimento() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getDataNascimento(), empresa.getFuncionario().get(0).getDataNascimento());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_cargo() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getCargo(), empresa.getFuncionario().get(0).getCargo());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_salario() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getSalario(), empresa.getFuncionario().get(0).getSalario());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_endereco() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getEndereco(), empresa.getFuncionario().get(0).getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_tipoContrato() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getTipoContrato(), empresa.getFuncionario().get(0).getTipoContrato());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_telefone() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getFuncionario().get(0).getTelefones(), empresa.getFuncionario().get(0).getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente(), empresa.getCliente());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_nome_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getNome(), empresa.getCliente().get(0).getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_dataNascimento_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getDataNascimento(), empresa.getCliente().get(0).getDataNascimento());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_dinheiroCarteira_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getDinheiroCarteira(), empresa.getCliente().get(0).getDinheiroCarteira());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_endereco_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getEndereco(), empresa.getCliente().get(0).getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_telefone_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getTelefones(), empresa.getCliente().get(0).getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_produtoComprado_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getProdutosComprados(), empresa.getCliente().get(0).getProdutosComprados());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_produtoCarrinho_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getCarrinhoProdutos(), empresa.getCliente().get(0).getCarrinhoProdutos());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_email_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getEmail(), empresa.getCliente().get(0).getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_cpf_de_cliente() {
        Empresa empresa = empresaService.findById(staticEmpresa.getCnpj());
        Assert.assertEquals(staticEmpresa.getCliente().get(0).getCpf(), empresa.getCliente().get(0).getCpf());
    }

    @Test()
    public void deve_atualizar_empresa() {
        staticEmpresa.setNome("Teste");
        empresaService.update(staticEmpresa);
        assertEquals(empresaService.findById(staticEmpresa.getCnpj()).getNome(), staticEmpresa.getNome());
    }
    
    @Test()
    public void deve_deletar_empresa() {
    	dadosEmpresa();
    	empresaService.save(staticEmpresa);
    	empresaService.deleteById(staticEmpresa.getCnpj());
    	assertEquals(null, empresaService.findById(staticEmpresa.getCnpj()));
    	empresaService.save(staticEmpresa);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_exception_ao_tentar_deletar_empresa_que_nao_existe() {
    	empresaService.deleteById("1");
    }

}
