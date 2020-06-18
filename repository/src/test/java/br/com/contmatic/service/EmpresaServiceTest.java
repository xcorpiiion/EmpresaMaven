package br.com.contmatic.service;

import br.com.contmatic.constantes.easyrandom.*;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import org.bson.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaServiceTest {

    private static EmpresaService empresaService;

    private static Empresa empresa;

    @BeforeClass
    public static void easyRandomDados() {
        dadosEmpresa();
        empresaService.save(empresa);
    }

    private static void dadosEmpresa() {
        empresa = new Empresa();
        empresaService = new EmpresaService();
        empresa = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.VALIDO);
        empresa.setFuncionario(Arrays.asList(EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO)));
        empresa.getFuncionario().get(0).setTelefones(new HashSet<>());
        empresa.getFuncionario().get(0).getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        empresa.setCliente(Arrays.asList(EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.VALIDO)));
        empresa.getCliente().get(0).setProdutosComprados(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        empresa.getCliente().get(0).setCarrinhoProdutos(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        empresa.getCliente().get(0).setTelefones(new HashSet<>());
        empresa.getCliente().get(0).getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        empresa.setProduto(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        empresa.setTelefones(new HashSet<>());
        empresa.getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        empresa.setEndereco(new HashSet<>());
        empresa.getEndereco().add(EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
    }

    @Test
    public void deve_salvar_empresa() {
        try {
            dadosEmpresa();
            empresaService.save(empresa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void deve_retornar_null_ao_salvar_empresa() {
        empresa = null;
        empresaService.save(empresa);
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
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
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCnpj(), empresa.getCnpj());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_nome() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getNome(), empresa.getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_email() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getEmail(), empresa.getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_telefone() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getTelefones(), empresa.getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_endereco() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getEndereco(), empresa.getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_produto() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getProduto(), empresa.getProduto());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario(), empresa.getFuncionario());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_nome() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getNome(), empresa.getFuncionario().get(0).getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_email() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getEmail(), empresa.getFuncionario().get(0).getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_cpf() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getCpf(), empresa.getFuncionario().get(0).getCpf());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_dataNascimento() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getDataNascimento(), empresa.getFuncionario().get(0).getDataNascimento());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_cargo() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getCargo(), empresa.getFuncionario().get(0).getCargo());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_salario() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getSalario(), empresa.getFuncionario().get(0).getSalario());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_endereco() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getEndereco(), empresa.getFuncionario().get(0).getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_tipoContrato() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getTipoContrato(), empresa.getFuncionario().get(0).getTipoContrato());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_funcionario_com_mesmo_telefone() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getFuncionario().get(0).getTelefones(), empresa.getFuncionario().get(0).getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente(), empresa.getCliente());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_nome_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getNome(), empresa.getCliente().get(0).getNome());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_dataNascimento_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getDataNascimento(), empresa.getCliente().get(0).getDataNascimento());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_dinheiroCarteira_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getDinheiroCarteira(), empresa.getCliente().get(0).getDinheiroCarteira());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_endereco_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getEndereco(), empresa.getCliente().get(0).getEndereco());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_telefone_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getTelefones(), empresa.getCliente().get(0).getTelefones());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_produtoComprado_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getProdutosComprados(), empresa.getCliente().get(0).getProdutosComprados());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_produtoCarrinho_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getCarrinhoProdutos(), empresa.getCliente().get(0).getCarrinhoProdutos());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_email_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getEmail(), empresa.getCliente().get(0).getEmail());
    }

    @Test()
    public void deve_retornar_empresa_pelo_cnpj_e_deve_retornar_mesmo_cpf_de_cliente() {
        Empresa empresa = empresaService.findById(this.empresa.getCnpj());
        Assert.assertEquals(this.empresa.getCliente().get(0).getCpf(), empresa.getCliente().get(0).getCpf());
    }

    @Test()
    public void deve_atualizar_empresa() {
        empresa.setNome("Teste");
        empresaService.update(empresa);
        Assert.assertEquals(empresaService.findById(empresa.getCnpj()).getNome(), "Teste");
    }

}
