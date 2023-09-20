package com.algaworks.banco.modelo.excessao;

public class SaldoInsuficienteException  extends RuntimeException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
