package br.com.contmatic.empresa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.com.contmatic.enums.TipoTelefone;

public class Telefone {
    
    private String phones;
    
    private TipoTelefone tipoTelefone;

    public Telefone(String telefone, TipoTelefone tipoTelefone) {
        this.phones = telefone;
        this.tipoTelefone = tipoTelefone;
    }

    public String getPhones() {
        return phones;
    }
    
    public void setPhones(String phones) {
        this.phones = phones;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phones).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        return new EqualsBuilder().append(phones, other).isEquals();
    }

    @Override
    public String toString() {
        return "Telefone [telefone=" + phones + ", tipoTelefone=" + tipoTelefone + "]";
    }

}
