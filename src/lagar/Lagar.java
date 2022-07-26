package lagar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import caminhao.Caminhao;
import fazenda.Fazenda;
import output.Relatorio;
import regras.RegrasLagar;
import regras.VerificaRegras;

public class Lagar implements Runnable {

    private final Integer capacidadeFilaLagar;
    private final Integer capacidadeRecepcaoLagar;
    private final Integer capacidadeMinimaFilaLagar;
    private BlockingQueue<Caminhao> filaCaminhoes;
    private boolean isCapacidadeMaxima;
    private boolean isRecepcaoProcessada;
    private Integer emProcessamento;
    private Integer toneladasRecebidas;
    private List<Caminhao> recepcao = new ArrayList<>();
    private RegrasLagar regras;

    private Relatorio relatorio = new Relatorio();

    public Lagar(Builder builder) {
        this.filaCaminhoes = Builder.filaCaminhoes;
        this.isCapacidadeMaxima = builder.isCapacidadeMaxima;
        this.isRecepcaoProcessada = builder.isRecepcaoProcessada;
        this.emProcessamento = builder.emProcessamento;
        this.capacidadeFilaLagar = builder.capacidadeFilaLagar;
        this.capacidadeRecepcaoLagar = builder.capacidadeRecepcaoLagar;
        this.capacidadeMinimaFilaLagar = builder.capacidadeMinimaFilaLagar;
        this.toneladasRecebidas = builder.toneladasRecebidas;
        this.regras = Builder.regras;
        for (int i = 0; i < capacidadeRecepcaoLagar; i++) {
            recepcao.add(null);
        }
    }

    public synchronized void setToneladasRecebidas(Integer toneladasAdicionadas) {
        this.toneladasRecebidas += toneladasAdicionadas;
    }

    public synchronized Integer getToneladasRecebidas() {
        return this.toneladasRecebidas;
    }

    public synchronized Integer getCapacidadeMinimaLagar() {
        return capacidadeMinimaFilaLagar;
    }

    public static class Builder {

        private static RegrasLagar regras = new VerificaRegras();
        private static BlockingQueue<Caminhao> filaCaminhoes = new ArrayBlockingQueue<>(regras.getMaxCaminhoesNaFila());
        private boolean isCapacidadeMaxima = false;
        private boolean isRecepcaoProcessada = false;
        private Integer emProcessamento = 0;
        private Integer toneladasRecebidas = 0;
        private Integer capacidadeFilaLagar = regras.getMaxCaminhoesNaFila();
        private Integer capacidadeRecepcaoLagar = regras.getCapacidadeRecepcaoLagar();
        private Integer capacidadeMinimaFilaLagar = regras.getMinCaminhoesNaFila();

        public Builder filaCaminhoes() {
            return this;
        }

        public Builder isCapacidadeMaxima() {
            return this;
        }

        public Builder isRecepcaoProcessada() {
            return this;
        }

        public Builder emProcessamento() {
            return this;
        }

        public Lagar build() {
            return new Lagar(this);
        }
    }

    public RegrasLagar getRegras() {
        return regras;
    }

    public List<Caminhao> getRecepcao() {
        return recepcao;
    }

    public void setCapacidadeMaxima(Boolean capacidadeMaximaEstado) {
        this.isCapacidadeMaxima = capacidadeMaximaEstado;
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

    public synchronized void incrementaProcessamento() {
        this.emProcessamento++;
    }

    public synchronized void decrementaProcessamento() {
        this.emProcessamento--;
    }

    public Integer getEmProcessamento() {
        return emProcessamento;
    }

    public Integer getCapacidadeLagar() {
        return capacidadeFilaLagar;
    }

    public synchronized Caminhao getCaminhaoRecepcao() {
        for (Caminhao caminhao : this.recepcao) {
            if (caminhao != null)
                return caminhao;
        }
        return null;
    }

    public boolean recepcaoVazia() {
        for (Caminhao caminhao : this.recepcao) {
            if (caminhao != null)
                return false;
        }

        return true;
    }

    private void verificaTamanhoFila() {
        if (getTamanhoFila() >= this.capacidadeFilaLagar) {
            setCapacidadeMaxima(true);
        }
    }

    public void enfileraCaminhao(Caminhao caminhao) {

        while (isCapacidadeMaxima
                && filaCaminhoes.remainingCapacity() <= (capacidadeFilaLagar - capacidadeMinimaFilaLagar)) {

            System.out.println("### LAGAR - FILA CHEIA ### | Caminhão de " + caminhao.getCapacidade()
                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                    + " chegou no lagar e aguarda para entrar na fila!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        filaCaminhoes.add(caminhao);
        System.out.println("### LAGAR - FILA ### | Caminhão de " + caminhao.getCapacidade()
                + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                + " chegou no lagar e espera na fila!");

    }

    private void processaCaminhao(int nRecepcao) {
        Caminhao caminhao = this.filaCaminhoes.poll();
        this.recepcao.set(nRecepcao, caminhao);

        System.out.println("### LAGAR - RECEPÇÃO ### | Caminhao vindo da fazenda "
                + caminhao.getPlantacao().getNomePlantacao()
                + " foi para recepção do lagar e será processado.");
        new Thread(new Processamento(this, caminhao, relatorio)).start();
    }

    private void liberaCaminhaoDaRecepcao(int nRecepcao) {
        if (!this.recepcao.get(nRecepcao).isCheio()) {
            this.recepcao.set(nRecepcao, null);
            System.out.println("### LAGAR - RECEPÇÃO ### | Recepção " + nRecepcao + " vazia.");
        }
    }

    @Override
    public void run() {
        while (true) {
            for (int nRecepcao = 0; nRecepcao < this.recepcao.size(); nRecepcao++) {
                if (!this.filaCaminhoes.isEmpty() && this.recepcao.get(nRecepcao) == null) {
                    processaCaminhao(nRecepcao);
                    break;
                }
                if (this.recepcao.get(nRecepcao) != null) {
                    liberaCaminhaoDaRecepcao(nRecepcao);
                }
                verificaTamanhoFila();
            }

            if (Fazenda.isTodasPlantacoesFinalizadas() && recepcaoVazia()) {
                try {
                    relatorio.escreveRelatorio();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                    System.out.println(exception.getStackTrace());
                }
                System.out.println(
                        "######################################### FIM DA SIMULAÇÃO #########################################");
                break;
            }

        }
    }

}