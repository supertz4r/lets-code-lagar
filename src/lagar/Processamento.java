package lagar;

import caminhao.Caminhao;

public class Processamento implements Runnable {

    private Lagar lagar;
    private Caminhao caminhao;

    public Processamento(Lagar lagar, Caminhao caminhao) {
        this.lagar = lagar;
        this.caminhao = caminhao;
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

            System.out.println("### LAGAR - PROCESSAMENTO ### | Caminhão de " +
                    caminhao.getCapacidade()
                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                    + " descarregou em " + tempoDescarregamento + " e foi liberado!");

            lagar.setCapacidadeMaxima(false);
        }
        // lagar.setRecepcao(numeroRecepcao);
        lagar.decrementaProcessamento();
    }

}
