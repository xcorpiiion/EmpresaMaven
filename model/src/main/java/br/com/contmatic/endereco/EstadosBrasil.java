package br.com.contmatic.endereco;
/**
 * The Enum EstadosBrasil.
 */
public enum EstadosBrasil {

                           /** The acre. */
                           ACRE("Acre", "AC"),

                           /** The alagoas. */
                           ALAGOAS("Alagoas", "AL"),

                           /** The amapa. */
                           AMAPA("Amapá", "AP"),

                           /** The amazonas. */
                           AMAZONAS("Amazonas", "AM"),

                           /** The bahia. */
                           BAHIA("Bahia", "BA"),

                           /** The ceara. */
                           CEARA("Ceará", "CE"),

                           /** The distritofederal. */
                           DISTRITOFEDERAL("Distrito Federal", "DF"),

                           /** The espiritosanto. */
                           ESPIRITOSANTO("Espírito Santo", "ES"),

                           /** The goias. */
                           GOIAS("Goiás", "GO"),

                           /** The maranhao. */
                           MARANHAO("Maranhão", "MA"),

                           /** The matogrosso. */
                           MATOGROSSO("Mato Grosso", "MT"),

                           /** The matogrossodosul. */
                           MATOGROSSODOSUL("Mato Grosso do Sul", "MS"),

                           /** The minasgerais. */
                           MINASGERAIS("Minas Gerais", "MG"),

                           /** The para. */
                           PARA("Pará", "PA"),

                           /** The paraiba. */
                           PARAIBA("Paraíba", "PB"),

                           /** The parana. */
                           PARANA("Paraná", "PR"),

                           /** The pernambuco. */
                           PERNAMBUCO("Pernambuco", "PE"),

                           /** The piaui. */
                           PIAUI("Piauí", "PI"),

                           /** The riodejaneiro. */
                           RIODEJANEIRO("Rio de Janeiro", "RJ"),

                           /** The riograndedonorte. */
                           RIOGRANDEDONORTE("Rio Grande do Norte", "RN"),

                           /** The riograndedosul. */
                           RIOGRANDEDOSUL("Rio Grande do Sul", "RS"),

                           /** The rondonia. */
                           RONDONIA("Rondônia", "RO"),

                           /** The roraima. */
                           RORAIMA("Roraima", "RR"),

                           /** The santacatarina. */
                           SANTACATARINA("Santa Catarina", "SC"),

                           /** The saopaulo. */
                           SAOPAULO("São Paulo", "SP"),

                           /** The sergipe. */
                           SERGIPE("Sergipe", "SE"),

                           /** The tocantins. */
                           TOCANTINS("Tocantins", "TO");

    /** The nome. */
    private String nome;

    /** The sigla. */
    private String sigla;

    /**
     * Instantiates a new estados brasil.
     *
     * @param nome the nome
     * @param sigla the sigla
     */
    private EstadosBrasil(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
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
