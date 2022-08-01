package lagar;

import caminhao.Caminhao;

public class Processamento implements Runnable {

    private Lagar lagar;

    public Processamento(Lagar lagar) {
        this.lagar = lagar;
    }

    @Override
    public void run() {

        Caminhao caminhao = lagar.processaCaminhao();
        if (caminhao != null) {
            System.out.println("### LAGAR - PROCESSAMENTO ### | Caminhão de " + caminhao.getCapacidade()
                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                    + " começou a descarregar!");
            Integer tempoDescarregamento = caminhao.getCapacidade() / 2; // pois 2 segundos

            try {
                Thread.sleep(tempoDescarregamento * 1000);
                caminhao.descarregar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("### LAGAR - PROCESSAMENTO ### | Caminhão de " + caminhao.getCapacidade()
                    + " toneladas da plantação " + caminhao.getPlantacao().getNomePlantacao()
                    + " descarregou em " + tempoDescarregamento + " e foi liberado!");

            lagar.setCapacidadeMaxima(false);
        }
        lagar.decrementaProcessamento();
    }

}
