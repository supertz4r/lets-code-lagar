package lagar;

import caminhao.Caminhao;
import output.Relatorio;

import java.util.Map;

public class Processamento implements Runnable {

    private Lagar lagar;
    private Caminhao caminhao;
    private Relatorio relatorio;

    public Processamento(Lagar lagar, Caminhao caminhao, Relatorio relatorio) {
        this.lagar = lagar;
        this.caminhao = caminhao;
        this.relatorio = relatorio;
    }

    private void logCaminhaoAProcessar(int numeroRecepcao) {
        System.out
                .println(
                        "### LAGAR - RECEPÇÃO #" + numeroRecepcao + " ### | Caminhão de " +
                                caminhao.getCapacidade()
                                + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                                + " começou a descarregar!");
    }

    private void logCaminhaoProcessado(int tempoDescarregamento) {
        System.out.println("### LAGAR - PROCESSAMENTO ### | Caminhão de " + caminhao.getCapacidade()
                + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                + " descarregou em " + tempoDescarregamento + " e foi liberado!");
    }

    private int calculaTempoDescarregamento() {
        Map<String, Integer> tempoXToneladas = lagar.getRegras().getTempoXToneladas();
        int ToneladasPorSegundo = tempoXToneladas.get("toneladas") / tempoXToneladas.get("tempo");

        return caminhao.getCapacidade() / ToneladasPorSegundo;
    }

    @Override
    public void run() {
        if (caminhao != null) {
            lagar.incrementaProcessamento();

            int numeroRecepcao = lagar.getRecepcao().indexOf(caminhao) + 1;
            logCaminhaoAProcessar(numeroRecepcao);

            int tempoDescarregamento = calculaTempoDescarregamento();

            try {
                Thread.sleep(tempoDescarregamento * 1000);
                caminhao.descarregar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lagar.setToneladasRecebidas(caminhao.getCapacidade());

            logCaminhaoProcessado(tempoDescarregamento);

            relatorio.adicionaLinha(
                    caminhao.getCapacidade(), // valorToneladas
                    caminhao.getPlantacao().getVariedadePlantacao().toString(), // tipoAzeitona
                    numeroRecepcao, // numeroRecepcao
                    caminhao.getPlantacao().getNomePlantacao(), // origemPlantacao
                    tempoDescarregamento); // totalDeSegundos

        }
        lagar.decrementaProcessamento();
    }

}
