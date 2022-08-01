package plantacao;

import caminhao.Caminhao;
import lagar.Lagar;

public class Plantacao implements Runnable {

    final private String nomePlantacao;
    final private String variedadePlantacao;
    final private Integer distanciaLagarSegundos;
    private boolean produzir;
    private Lagar lagar;

    private Plantacao(Builder builder) {
        this.nomePlantacao = builder.nomePlantacao;
        this.variedadePlantacao = builder.variedadePlantacao;
        this.distanciaLagarSegundos = builder.distanciaLagarSegundos;
        this.lagar = builder.lagar;
        this.produzir = true;
    }

    public static class Builder {

        private String nomePlantacao;
        private String variedadePlantacao;
        private Integer distanciaLagarSegundos;
        private Lagar lagar;

        public Builder nomePlantacao(String nomePlantacao) {
            this.nomePlantacao = nomePlantacao;
            return this;
        }

        public Builder variedadePlantacao(String variedadePlantacao) {
            this.variedadePlantacao = variedadePlantacao;
            return this;
        }

        public Builder distanciaLagarSegundos(Integer distanciaLagarSegundos) {
            this.distanciaLagarSegundos = distanciaLagarSegundos;
            return this;
        }

        public Builder lagar(Lagar lagar) {
            this.lagar = lagar;
            return this;
        }

        public Plantacao build() {
            return new Plantacao(this);
        }

    }

    public void setProduzir(boolean produzir) {
        this.produzir = produzir;
    }

    public String getNomePlantacao() {
        return nomePlantacao;
    }

    public String getVariedadePlantacao() {
        return variedadePlantacao;
    }

    public Integer getDistanciaLagarSegundos() {
        return distanciaLagarSegundos;
    }

    public Lagar getLagar() {
        return lagar;
    }

    @Override
    public void run() {

        int i = 1;
        int sleepTime;
        long tempoInicioProducao = System.currentTimeMillis();

        while (produzir) {

            if (lagar.getTamanhoFila() < 3) {

                Caminhao caminhao = new Caminhao.Builder()
                        .plantacao(this)
                        .capacidade()
                        .cheio(false)
                        .build();

                sleepTime = (caminhao.getCapacidade() / 2);

                System.out
                        .println("Execução nº " + i + " da plantação " + this.nomePlantacao + ". Enchendo caminhão de "
                                + caminhao.getCapacidade() + " toneladas em " + sleepTime + " segundos.");

                // Simula o carregamento do caminhão.
                try {
                    Thread.sleep(sleepTime * 1000);
                    caminhao.encher();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Enviando caminhão de " + caminhao.getCapacidade()
                        + " toneladas da plantação " + this.nomePlantacao + " para o Lagar. Tempo de viagem: "
                        + getDistanciaLagarSegundos() + " segundos");

                // Simula o transporte do caminhão.
                try {
                    Thread.sleep(getDistanciaLagarSegundos() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lagar.enfileraCaminhao(new Caminhao.Builder().plantacao(this).capacidade().cheio(false).build());

                i++;

            } else {

                System.out.println("Plantação " + this.nomePlantacao + " em espera!");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        long tempoFimProducao = System.currentTimeMillis();

        System.out.println("Plantação " + nomePlantacao + " foi finalizada! Execução total: "
                + ((tempoFimProducao - tempoInicioProducao) / 60000) + "m"
                + (((tempoFimProducao - tempoInicioProducao) - 60000) / 120000) + "s");

    }

}
