package br.com.contmatic.telefone;

public enum DddBrasil {

    SAO_PAULO("11", "SP"),

    SAO_JOSE_DOS_CAMPOS("12", "SP"),

    SANTOS("13", "SP"),

    BAURU("14", "SP"),

    SOROCABA("15", "SP"),

    RIBEIRAO_PRETO("16", "SP"),

    SAO_JOSE_DO_RIO_PRETO("17", "SP"),

    PRESIDENTE_PRUDENTE("18", "SP"),

    CAMPINAS("19", "SP"),

    RIO_DE_JANEIRO("21", "RJ"),

    CAMPOS_DOS_GOYTACAZES("22", "RJ"),

    VOLTA_REDONDA("24", "RJ"),

    VILA_VELHA("27", "ES"),

    CACHOEIRO_DE_ITAPEMIRIM("28", "ES"),

    BELO_HORIZONTE("31", "MG"),

    JUIZ_DE_FORA("32", "MG"),

    GOVERNADOR_VALADARES("33", "MG"),

    UBERLANDIA("34", "MG"),

    POCOS_DE_CALDAS("35", "MG"),

    DIVINOPOLIS("37", "MG"),

    MONTES_CLAROS("38", "MG"),

    CURITIBA("41", "PR"),

    PONTA_GROSSA("42", "PR"),

    LONDRINA("43", "PR"),

    MARINGA("44", "PR"),

    FOZ_DO_IGUACU("45", "PR"),

    FRANCISCO_BELTRAO("46", "PR"),

    JOINVILLE("47", "SC"),

    FLORIANOPOLIS("48", "SC"),

    CHAPECO("49", "SC"),

    PORTO_ALEGRE("51", "RS"),

    PELOTAS("53", "RS"),

    CAXIAS_DO_SUL("54", "RS"),

    SANTA_MARIA("55", "RS"),

    BRASILIA("61", "DF"),

    GOIANIA("62", "GO"),

    PALMAS("63", "TO"),

    RIO_VERDE("64", "GO"),

    CUIABA("65", "MT"),

    RONDONOPOLIS("66", "MT"),

    CAMPO_GRANDE("67", "MS"),

    RIO_BRANCO("68", "AC"),

    PORTO_VELHO("69", "RO"),

    SALVADOR("71", "BA"),

    ILHEUS("73", "BA"),

    JUAZEIRO("74", "BA"),

    FEIRA_DE_SANTANA("75", "BA"),

    BARREIRAS("77", "BA"),

    ARACAJU("79", "SE"),

    RECIFE("81", "PE"),

    MACEIO("82", "AL"),

    JOAO_PESSOA("83", "PB"),

    NATAL("84", "RN"),

    FORTALEZA("85", "CE"),

    TERESINA("86", "PI"),

    PETROLINA("87", "PE"),

    JUAZEIRO_DO_NORTE("88", "CE"),

    PICOS("89", "PI"),

    BELEM("91", "PA"),

    MANAUS("92", "AM"),

    SANTAREM("93", "PA"),

    MARABA("94", "PA"),

    BOA_VISTA("95", "RR"),

    MACAPA("96", "AP"),

    COARI("97", "AM"),

    SAO_LUIS("98", "MA"),

    IMPERATRIZ("99", "MA");

    private String ddd;

    private String sigla;

    private DddBrasil(String ddd, String sigla) {
        this.ddd = ddd;
        this.sigla = sigla;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSigla() {
        return sigla;
    }

}
