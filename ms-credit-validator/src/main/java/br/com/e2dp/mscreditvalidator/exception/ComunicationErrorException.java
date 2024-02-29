package br.com.e2dp.mscreditvalidator.exception;

import lombok.Data;
import lombok.Getter;


public class ComunicationErrorException extends Exception {

    @Getter
    private Integer status;
    public ComunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
