package lagar;

import caminhao.Caminhao;
import output.Relatorio;

public class Processamento implements Runnable {

    private Lagar lagar;
    private Caminhao caminhao;
    private Relatorio relatorio;

    public Processamento(Lagar lagar, Caminhao caminhao, Relatorio relatorio) {
        this.lagar = lagar;
        this.caminhao = caminhao; 
        this.relatorio = relatorio;
    }

    @Override
    public void run() {
        if (caminhao != null) {
            lagar.incrementaProcessamento();

            int numeroRecepcao = lagar.getRecepcao().indexOf(caminhao) + 1;
            System.out
                    .println(
                            "### LAGAR - RECEPÇÃO #" + numeroRecepcao + " ### | Caminhão de " +
                                    caminhao.getCapacidade()
                                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                                    + " começou a descarregar!");
            Integer tempoDescarregamento = caminhao.getCapacidade() / 2; // pois 2 segundos

            try {
                Thread.sleep(tempoDescarregamento * 1000);
                caminhao.descarregar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lagar.setToneladasRecebidas(caminhao.getCapacidade());

            System.out.println("### LAGAR - PROCESSAMENTO ### | Caminhão de " + caminhao.getCapacidade()
                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                    + " descarregou em " + tempoDescarregamento + " e foi liberado!");

            relatorio.adicionaLinha(
                    caminhao.getCapacidade(), //valorToneladas
                    caminhao.getPlantacao().getVariedadePlantacao(), //tipoAzeitona
                    numeroRecepcao, //numeroRecepcao
                    caminhao.getPlantacao().getNomePlantacao(), //origemPlantacao
                    tempoDescarregamento); //totalDeSegundos

            lagar.setCapacidadeMaxima(false);
        }
        // lagar.setRecepcao(numeroRecepcao);
        lagar.decrementaProcessamento();
    }

}
