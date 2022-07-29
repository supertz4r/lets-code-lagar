package lagar;

import java.util.concurrent.BlockingQueue;

import caminhao.Caminhao;

public class Processamento implements Runnable {

    private BlockingQueue<Caminhao> filaCaminhoes;
    private Lagar lagar;

    public Processamento(BlockingQueue<Caminhao> filaCaminhoes, Lagar lagar) {
        this.filaCaminhoes = filaCaminhoes;
        this.lagar = lagar;
    }

    @Override
    public void run() {

        try {
            Caminhao caminhao = filaCaminhoes.take();
            // tempoEspera = primeiroCaminhaoDaFila.getCapacidade() / 2; // pois 2 segundos
            // corresponde a 4 toneladas e
            // // podemos ter de 4 até 16 toneladas
            // isRecepcaoProcessada = true;
            // isCapacidadeMaxima = false;
            lagar.decrementaProcessamento();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
