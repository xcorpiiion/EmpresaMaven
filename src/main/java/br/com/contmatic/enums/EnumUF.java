package br.com.contmatic.enums;

public enum EnumUF {

    AC("Acre", 12, "AC"),
    AL("Alagoas", 27, "AL"),
    AM("Amazonas", 13, "AM"),
    AP("Amapá", 16, "AP"),
    BA("Bahia", 29, "BA"),
    CE("Ceará", 23, "CE"),
    DF("Distrito Federal", 53, "DF"),
    ES("Espírito Santo", 32, "ES"),
    GO("Goiás", 52, "GO"),
    MA("Maranhão", 21, "MA"),
    MG("Minas Gerais", 31, "MG"),
    MS("Mato Grosso do Sul", 50, "MS"),
    MT("Mato Grosso", 51, "MT"),
    PA("Pará¡",	15, "PA"),
    PB("Paraiba", 25, "PB"),
    PE("Pernambuco", 26, "PE"),
    PI("Piauí", 22, "PI"),
    PR("Paraná", 41, "PR"),
    RJ("Rio de Janeiro", 33, "RJ"),
    RN("Rio Grande do Norte", 24, "RN"),
    RO("Rondônia", 11, "RO"),
    RR("Roraima", 14, "RR"),
    RS("Rio Grande do Sul", 43, "RS"),
    SC("Santa Catarina", 42, "SC"),
    SE("Sergipe", 28, "SE"),
    SP("São Paulo", 35, "SP"),
    TO("Tocantins", 17, "TO");

    private String nome;
    private Integer codigoIbge;
    private String sigla;

    private EnumUF(String nome, Integer codigoIbge, String sigla) {
        this.nome = nome;
        this.codigoIbge = codigoIbge;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public String getSigla() {
        return sigla;
    }

    public static EnumUF fromOrdinal(int ordinal) {

        for (EnumUF uf : EnumUF.values()) {

            if (ordinal == uf.ordinal()) {

                return uf;
            }
        }

        throw new IllegalArgumentException("Não existe nenhuma UF com o ordinal " + ordinal + ".");
    }

    public static EnumUF fromCodigoIbge(Integer codigoIbge) {
        for (EnumUF uf : EnumUF.values()) {

            if (!uf.name().equals("NONE") && uf.getCodigoIbge().equals(codigoIbge)) {

                return uf;
            }
        }
        return null;
    }

    public static EnumUF fromCodigoIbge(String codigoIbge) {
        for (EnumUF uf : EnumUF.values()) {

            if (!uf.name().equals("NONE") && uf.getCodigoIbge().toString().equalsIgnoreCase(codigoIbge)) {

                return uf;
            }
        }
        return null;
    }

    public static EnumUF fromSigla(String sigla) {
        for (EnumUF uf : EnumUF.values()) {

            if (uf.getSigla().equalsIgnoreCase(sigla)) {

                return uf;
            }
        }
        return null;

    }

    public static EnumUF fromNome(String nome) {
        for (EnumUF uf : EnumUF.values()) {

            if (uf.getNome().equalsIgnoreCase(nome)) {

                return uf;
            }
        }
        return null;

    }

    @Override
    public String toString() {
        return sigla;
    }
}
