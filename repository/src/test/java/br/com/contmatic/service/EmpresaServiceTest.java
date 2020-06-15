package br.com.contmatic.service;

import br.com.contmatic.constantes.easyrandom.*;
import br.com.contmatic.empresa.Empresa;
import org.bson.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EmpresaServiceTest {

    private static EmpresaService empresaService;

    private static Empresa empresa;

    @BeforeClass
    public static void easyRandomDados() {
        empresa = new Empresa();
        empresaService = new EmpresaService();
        empresa = EasyRandomEmpresa.validadorEasyRandomEmpresa(TipoDadoParaTesteEmpresa.VALIDO);
        empresa.setFuncionario(Arrays.asList(EasyRandomFuncionario.validadorEasyRandomFuncionario(TipoDadoParaTesteFuncionario.VALIDO)));
        empresa.setCliente(Arrays.asList(EasyRandomCliente.validadorEasyRandomCliente(TipoDadoParaTesteCliente.VALIDO)));
        empresa.setProduto(Arrays.asList(EasyRandomProduto.validadorEasyRandomProduto(TipoDadoParaTesteProduto.VALIDO)));
        empresa.setTelefones(new HashSet<>());
        empresa.getTelefones().add(EasyRandomTelefone.validadorEasyRandomTelefone(TipoDadoParaTesteTelefone.VALIDO));
        empresa.setEndereco(new HashSet<>());
        empresa.getEndereco().add(EasyRandomEndereco.validadorEasyRandomEndereco(TipoDadoParaTesteEndereco.VALIDO));
    }

    @Test
    public void deve_salvar_empresa() {
        try {
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
        Document empresaDocument = empresaService.findById(empresa.getCnpj());
        Assert.assertNotNull(empresaDocument);
    }

    @Test()
    public void deve_retornar_null_ao_procurar_empresa_pelo_cnpj() {
        Document empresaDocument = empresaService.findById("1234567890123456");
        Assert.assertNull(empresaDocument);
    }

    @Test()
    public void deve_retornar_todas_as_empresa() {
        List<Document> empresaDocument = empresaService.findAll();
        Assert.assertNotNull(empresaDocument);
    }

    @Test()
    public void deve_atualizar_empresa() {
        empresa.setNome("Teste");
        empresaService.update(empresa);
    }

}
