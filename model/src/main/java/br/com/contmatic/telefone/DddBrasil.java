package br.com.contmatic.telefone;
/**
 * The Enum DddBrasil.
 */
public enum DddBrasil {
                       
                       /** The sao paulo. */
                       SAO_PAULO("11", "SP"),
                       
                       /** The sao jose dos campos. */
                       SAO_JOSE_DOS_CAMPOS("12", "SP"),
                       
                       /** The santos. */
                       SANTOS("13", "SP"),
                       
                       /** The bauru. */
                       BAURU("14", "SP"),
                       
                       /** The sorocaba. */
                       SOROCABA("15", "SP"),
                       
                       /** The ribeirao preto. */
                       RIBEIRAO_PRETO("16", "SP"),
                       
                       /** The sao jose do rio preto. */
                       SAO_JOSE_DO_RIO_PRETO("17", "SP"),
                       
                       /** The presidente prudente. */
                       PRESIDENTE_PRUDENTE("18", "SP"),
                       
                       /** The campinas. */
                       CAMPINAS("19", "SP"),
                       
                       /** The rio de janeiro. */
                       RIO_DE_JANEIRO("21", "RJ"),
                       
                       /** The campos dos goytacazes. */
                       CAMPOS_DOS_GOYTACAZES("22", "RJ"),
                       
                       /** The volta redonda. */
                       VOLTA_REDONDA("24", "RJ"),
                       
                       /** The vila velha. */
                       VILA_VELHA("27", "ES"),
                       
                       /** The cachoeiro de itapemirim. */
                       CACHOEIRO_DE_ITAPEMIRIM("28", "ES"),
                       
                       /** The belo horizonte. */
                       BELO_HORIZONTE("31", "MG"),
                       
                       /** The juiz de fora. */
                       JUIZ_DE_FORA("32", "MG"),
                       
                       /** The governador valadares. */
                       GOVERNADOR_VALADARES("33", "MG"),
                       
                       /** The uberlandia. */
                       UBERLANDIA("34", "MG"),
                       
                       /** The pocos de caldas. */
                       POCOS_DE_CALDAS("35", "MG"),
                       
                       /** The divinopolis. */
                       DIVINOPOLIS("37", "MG"),
                       
                       /** The montes claros. */
                       MONTES_CLAROS("38", "MG"),
                       
                       /** The curitiba. */
                       CURITIBA("41", "PR"),
                       
                       /** The ponta grossa. */
                       PONTA_GROSSA("42", "PR"),
                       
                       /** The londrina. */
                       LONDRINA("43", "PR"),
                       
                       /** The maringa. */
                       MARINGA("44", "PR"),
                       
                       /** The foz do iguacu. */
                       FOZ_DO_IGUACU("45", "PR"),
                       
                       /** The francisco beltrao. */
                       FRANCISCO_BELTRAO("46", "PR"),
                       
                       /** The joinville. */
                       JOINVILLE("47", "SC"),
                       
                       /** The florianopolis. */
                       FLORIANOPOLIS("48", "SC"),
                       
                       /** The chapeco. */
                       CHAPECO("49", "SC"),
                       
                       /** The porto alegre. */
                       PORTO_ALEGRE("51", "RS"),
                       
                       /** The pelotas. */
                       PELOTAS("53", "RS"),
                       
                       /** The caxias do sul. */
                       CAXIAS_DO_SUL("54", "RS"),
                       
                       /** The santa maria. */
                       SANTA_MARIA("55", "RS"),
                       
                       /** The brasilia. */
                       BRASILIA("61", "DF"),
                       
                       /** The goiania. */
                       GOIANIA("62", "GO"),
                       
                       /** The palmas. */
                       PALMAS("63", "TO"),
                       
                       /** The rio verde. */
                       RIO_VERDE("64", "GO"),
                       
                       /** The cuiaba. */
                       CUIABA("65", "MT"),
                       
                       /** The rondonopolis. */
                       RONDONOPOLIS("66", "MT"),
                       
                       /** The campo grande. */
                       CAMPO_GRANDE("67", "MS"),
                       
                       /** The rio branco. */
                       RIO_BRANCO("68", "AC"),
                       
                       /** The porto velho. */
                       PORTO_VELHO("69", "RO"),
                       
                       /** The salvador. */
                       SALVADOR("71", "BA"),
                       
                       /** The ilheus. */
                       ILHEUS("73", "BA"),
                       
                       /** The juazeiro. */
                       JUAZEIRO("74", "BA"),
                       
                       /** The feira de santana. */
                       FEIRA_DE_SANTANA("75", "BA"),
                       
                       /** The barreiras. */
                       BARREIRAS("77", "BA"),
                       
                       /** The aracaju. */
                       ARACAJU("79", "SE"),
                       
                       /** The recife. */
                       RECIFE("81", "PE"),
                       
                       /** The maceio. */
                       MACEIO("82", "AL"),
                       
                       /** The joao pessoa. */
                       JOAO_PESSOA("83", "PB"),
                       
                       /** The natal. */
                       NATAL("84", "RN"),
                       
                       /** The fortaleza. */
                       FORTALEZA("85", "CE"),
                       
                       /** The teresina. */
                       TERESINA("86", "PI"),
                       
                       /** The petrolina. */
                       PETROLINA("87", "PE"),
                       
                       /** The juazeiro do norte. */
                       JUAZEIRO_DO_NORTE("88", "CE"),
                       
                       /** The picos. */
                       PICOS("89", "PI"),
                       
                       /** The belem. */
                       BELEM("91", "PA"),
                       
                       /** The manaus. */
                       MANAUS("92", "AM"),
                       
                       /** The santarem. */
                       SANTAREM("93", "PA"),
                       
                       /** The maraba. */
                       MARABA("94", "PA"),
                       
                       /** The boa vista. */
                       BOA_VISTA("95", "RR"),
                       
                       /** The macapa. */
                       MACAPA("96", "AP"),
                       
                       /** The coari. */
                       COARI("97", "AM"),
                       
                       /** The sao luis. */
                       SAO_LUIS("98", "MA"),
                       
                       /** The imperatriz. */
                       IMPERATRIZ("99", "MA");

    /** _The_nome._ */
    private String ddd;

    /** _The_sigla._ */
    private String sigla;

    /**
     * Instantiates a new ddd brasil.
     *
     * @param ddd the ddd
     * @param sigla the sigla
     */
    private DddBrasil(String ddd, String sigla) {
        this.ddd = ddd;
        this.sigla = sigla;
    }

    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Gets the sigla.
     *
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

}
