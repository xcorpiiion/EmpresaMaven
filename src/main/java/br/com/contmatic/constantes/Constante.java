package br.com.contmatic.constantes;

import static java.lang.String.valueOf;

public final class Constante {

	public static final String EMAIL_VALIDO = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";

	public static final String CLIENTE = "cliente";

	public static final String EMPRESA = "empresa";

	public static final String ENDERECO = "endereco";

	public static final String FUNCIONARIO = "funcionario";

	public static final String PRODUTO = "produto";

	private static final String NOME_CLASSE = "nomeClasse";

	private static final String NOME_CAMPO = "nomeCampo";

	private Constante() {

	}

	public static String campoVazioOrNullMensagemPadrao(String nomeCampo, String nomeClasse) {
		return "Campo nomeCampo de nomeClasse não pode ficar vazio".replace(NOME_CAMPO, nomeCampo).replace(NOME_CLASSE,
				nomeClasse);
	}

	public static String tamanhoCamposMensagemPadrao(int tamanhoMinimo, int tamanhoMaximo, String nomeCampo,
			String nomeClasse) {
		return "O campo nomeCampo de nomeClasse precisa ter um tamanho mínimo de tamanhoMinimo e um tamanho máximo de tamanhoMaximo"
				.replace("tamanhoMinimo", nomeCampo).replace("tamanhoMaximo", nomeClasse)
				.replace("min", valueOf(tamanhoMinimo)).replace("max", valueOf(tamanhoMaximo));
	}

	public static String stringJustContainsWordMensagemPadrao(String nomeCampo, String nomeClasse) {
		return "Campo nomeCampo de nomeClasse deve apenas possuir letras".replace(NOME_CAMPO, nomeCampo)
				.replace(NOME_CLASSE, nomeClasse);
	}

	public static String stringJustContainsWordAndNumberMensagemPadrao(String nomeCampo, String nomeClasse) {
		return "Campo nomeCampo de nomeClasse não deve possuir caracteres especiais".replace(NOME_CAMPO, nomeCampo)
				.replace(NOME_CLASSE, nomeClasse);
	}

	public static String campoInvalidoMensagemPadrao(String nomeCampo, String nomeClasse) {
		return "Campo nomeCampo de nomeClasse está invalido. Favor revisar o campo".replace(NOME_CAMPO, nomeCampo)
				.replace(NOME_CLASSE, nomeClasse);
	}

	public static String dataInvalidaMensagem(String mensagem) {
		return mensagem;
	}

	public static String isDataGreaterThanCurrent(String nomeCampo, String nomeClasse) {
		return "Campo nomeCampo de nomeClasse não pode ser maior do que a data atual".replace(NOME_CAMPO, nomeCampo)
				.replace(NOME_CLASSE, nomeClasse);
	}

}
