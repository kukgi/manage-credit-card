package br.com.e2dp.mscreditvalidator.exception;

public class ClientDateNotfoundException extends Exception {

    public ClientDateNotfoundException() {
        super("Dados do cliente não encontrado para o CPF informado");
    }
}
