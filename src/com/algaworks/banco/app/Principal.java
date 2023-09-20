package com.algaworks.banco.app;

import com.algaworks.banco.modelo.*;
import com.algaworks.banco.modelo.atm.CaixaEletronico;
import com.algaworks.banco.modelo.excessao.SaldoInsuficienteException;
import com.algaworks.banco.modelo.pagamento.Boleto;
import com.algaworks.banco.modelo.pagamento.Holerite;

public abstract class Principal {

    public static void main(String[] args) {
        Pessoa titular1 = new Pessoa();
        titular1.setNome("Rafael da Silva");
        titular1.setDocumento("3478962387");
        titular1.setRendimentoAnual(15_000d);

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Maria de Fátima");
        titular2.setDocumento("8324792837");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaInvestimento minhaConta = new ContaInvestimento(titular1, 123, 987);
        ContaEspecial suaConta = new ContaEspecial(titular2, 222, 333, 1_000);

        try {

        minhaConta.depositar(30_000);
        minhaConta.sacar(1_000);
        minhaConta.creditarRentimentos(0.8);
        minhaConta.debitarTarifaMensal();

        suaConta.depositar(15_000);
        suaConta.sacar(15_500);
        suaConta.debitarTarifaMensal();

        Boleto boletoEscola = new Boleto(titular2, 35_000);
        Holerite salarioFuncionario = new Holerite(titular2, 100, 160);

        caixaEletronico.pagar(boletoEscola, minhaConta);
        caixaEletronico.pagar(salarioFuncionario, minhaConta);

        caixaEletronico.estornarPagamento(boletoEscola, minhaConta);

        boletoEscola.imprimirRecibo();
        salarioFuncionario.imprimirRecibo();

        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro ao executar operaçao na conta: " + e.getMessage());
        }

        caixaEletronico.imprimirSaldo(minhaConta);
        System.out.println();
        caixaEletronico.imprimirSaldo(suaConta);
    }

}
