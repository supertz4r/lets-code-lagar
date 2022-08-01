package lagar;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import caminhao.Caminhao;

public class Lagar {

    private final Integer capacidadeLagar = 3;
    private final Integer capacidadeRecepcaoLagar = 3;
    private BlockingQueue<Caminhao> filaCaminhoes = new ArrayBlockingQueue<>(capacidadeLagar);
    private boolean isCapacidadeMaxima = false;
    private boolean isRecepcaoProcessada = false;
    private double tempoEspera;
    private Integer emProcessamento = 0;
    private Integer[] recepcao = new Integer[capacidadeRecepcaoLagar];

    public Lagar() {
        for (int i = 0; i < capacidadeRecepcaoLagar; i++) {
            recepcao[i] = 0;
        }
    }

    public synchronized Integer getNumeroReceptora() {

        Integer numeroReceptora = 0;

        for (int i = 0; i < capacidadeRecepcaoLagar; i++) {
            if (recepcao[i] == 0) {
                recepcao[i] = 1;
                numeroReceptora = i + 1;
                break;
            }
        }

        return numeroReceptora;

    }

    public synchronized void setRecepcao(Integer posicao) {
        recepcao[posicao - 1] = 0;
    }

    public synchronized void setCapacidadeMaxima(Boolean capacidadeMaximaEstado) {
        this.isCapacidadeMaxima = capacidadeMaximaEstado;
        this.notifyAll();
    }

    public synchronized boolean getIsCapacidadeMaxima() {
        return isCapacidadeMaxima;
    }

    public Integer getCapacidadeRecepcaoLagar() {
        return capacidadeRecepcaoLagar;
    }

    public boolean getIsRecepcaoProcessada() {
        return isRecepcaoProcessada;
    }

    public synchronized int getTamanhoFila() {
        return filaCaminhoes.size();
    }

    public synchronized void enfileraCaminhao(Caminhao caminhao) {

        while (filaCaminhoes.remainingCapacity() == 0) {
            setCapacidadeMaxima(true);
            try {
                System.out.println("### LAGAR - FILA CHEIA ### | Caminhão de " + caminhao.getCapacidade()
                        + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                        + " chegou no lagar e aguarda para entrar na fila!");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        filaCaminhoes.add(caminhao);
        System.out.println("### LAGAR - FILA ### | Caminhão de " + caminhao.getCapacidade()
                + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                + " chegou no lagar e espera na fila!");

    }

    public synchronized void incrementaProcessamento() {
        this.emProcessamento++;
    }

    public synchronized void decrementaProcessamento() {
        this.emProcessamento--;
    }

    public Integer getEmProcessamento() {
        return emProcessamento;
    }

    public synchronized Caminhao processaCaminhao() {
        try {
            if (filaCaminhoes.size() != 0)
                return filaCaminhoes.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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

    public Integer getCapacidadeLagar() {
        return capacidadeLagar;
    }

}