package br.com.welson.meucontrole.util;

public enum Mensagem {
    EMAIL_OU_SENHA_INVALIDOS("E-mail ou senha inválidos."),
    ESSE_RECURSO_NAO_EXISTE("Esse recurso não existe."),
    USUARIO_NAO_ENCONTRADO("Usuário não encontrado.");

    private String messagem;

    Mensagem(String messagem) {
        this.messagem = messagem;
    }

    public String getMessagem() {
        return messagem;
    }
}
