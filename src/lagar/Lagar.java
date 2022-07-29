package lagar;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import caminhao.Caminhao;

public class Lagar implements Callable<Boolean> {

    private static BlockingQueue<Caminhao> filaCaminhoes = new ArrayBlockingQueue<>(3);
    private boolean isCapacidadeMaxima = false;
    private boolean isRecepcaoProcessada = false;
    private double tempoEspera;
    private boolean processar;
    private Integer emProcessamento = 0;

    public Lagar() {
    }

    public void setProcessar(boolean processar) {
        this.processar = processar;
    }

    public boolean getIsCapacidadeMaxima() {
        return isCapacidadeMaxima;
    }

    public boolean getIsRecepcaoProcessada() {
        return isRecepcaoProcessada;
    }

    public int getTamanhoFila() {
        return filaCaminhoes.size();
    }

    public void enfileraCaminhao(Caminhao caminhao) {
        if (filaCaminhoes.remainingCapacity() == 0) {
            isCapacidadeMaxima = true;
            return;
        }
        filaCaminhoes.add(caminhao);
    }

    public void incrementaProcessamento() {
        this.emProcessamento++;
    }

    public void decrementaProcessamento() {
        this.emProcessamento--;
    }

    public static BlockingQueue<Caminhao> getFilaCaminhoes() {
        return filaCaminhoes;
    }

    public double processaRecepcao() {
        try {
            Caminhao primeiroCaminhaoDaFila = filaCaminhoes.take();
            tempoEspera = primeiroCaminhaoDaFila.getCapacidade() / 2; // pois 2 segundos corresponde a 4 toneladas e
                                                                      // podemos ter de 4 até 16 toneladas
            isRecepcaoProcessada = true;
            isCapacidadeMaxima = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tempoEspera;
    }

    @Override
    public Boolean call() throws Exception {

        this.processar = true;

        while (processar) {

            if (emProcessamento <= 3 && !filaCaminhoes.isEmpty()) {

                new Thread(new Processamento(filaCaminhoes, this)).start();
                incrementaProcessamento();

            }

        }

        return true;
    }
}