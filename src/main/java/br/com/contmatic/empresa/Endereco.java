package br.com.contmatic.empresa;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.com.contmatic.constantes.Constante;
import br.com.contmatic.enums.EstadosBrasil;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringFormatException;
import br.com.contmatic.services.StringSizeException;


public class Endereco {

    private String rua;

    private String bairro;

    private String cep;

    private int numeroResidencia;

    private String cidade;

    private EstadosBrasil estado;

    public Endereco(String rua, String bairro, String cep, int numeroResidencia, String cidade, EstadosBrasil estado) {
        setRua(rua);
        setBairro(bairro);
        setCep(cep);
        setNumeroResidencia(numeroResidencia);
        setCidade(cidade);
        setEstado(estado);
    }

    public Endereco() {
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        ruaIsEmpty(rua);
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        bairroIsEmpty(bairro);
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        cepIsEmpty(cep);
        cepContainsWord(cep);
        cepSizeValidation(cep);
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        cidadeIsEmpty(cidade);
        cidadeContainsNumber(cidade);
        this.cidade = cidade;
    }

    public EstadosBrasil getEstado() {
        return estado;
    }

    public void setEstado(EstadosBrasil estado) {
        estadoIsEmpty(estado);
        this.estado = estado;
    }

    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(int numeroResidencia) {
        numeroResidenciaLessThanZero(numeroResidencia);
        this.numeroResidencia = numeroResidencia;
    }

    private void numeroResidenciaLessThanZero(int numeroResidencia) {
        if (numeroResidencia < 0) {
            throw new IllegalArgumentException("O número é menor do que zero");
        }
    }

    private void cidadeIsEmpty(String cidade) {
        if (StringUtils.isEmpty(cidade) || cidade.trim().equals("")) {
            throw new EmptyStringException("A cidade esta vazia");
        }
    }

    private void cidadeContainsNumber(String cidade) {
        if (cidade.matches(Constante.ILLEGAL_NUMBER)) {
            throw new StringFormatException("A cidade contém algum número");
        }
    }

    private void estadoIsEmpty(EstadosBrasil estado) {
        if (estado == null) {
            throw new NullPointerException("O estado esta vazio");
        }
    }

    private void ruaIsEmpty(String rua) {
        if (StringUtils.isEmpty(rua) || rua.trim().equals("")) {
            throw new EmptyStringException("A rua esta vazia");
        }
    }

    private void cepIsEmpty(String cep) {
        if (StringUtils.isEmpty(cep) || cep.trim().equals("")) {
            throw new EmptyStringException("O cep esta vazio");
        }
    }

    private void cepSizeValidation(String cep) {
        if (cep.length() != 8) {
            throw new StringSizeException("O tamanho do cep é incorreto");
        }
    }

    private void cepContainsWord(String cep) {
        if (!StringUtils.isNumeric(cep)) {
            throw new StringFormatException("O cep contém letras e números");
        }
    }

    private void bairroIsEmpty(String bairro) {
        if (StringUtils.isEmpty(bairro) || bairro.trim().equals("")) {
            throw new EmptyStringException("O bairro esta vazio");
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).append(numeroResidencia).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(cep, other.cep).append(numeroResidencia, other.numeroResidencia).isEquals();
    }

    @Override
    public String toString() {
        return "Endereco [rua=" + getRua() + ", bairro=" + getBairro() + ", cep=" + getCep() + ", numeroResidencia=" + getNumeroResidencia() + ", cidade=" + getCidade() + ", estado=" + getEstado() +
            "]";
    }

}
