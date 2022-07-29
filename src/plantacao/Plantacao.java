package plantacao;

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

    @Override
    public void run() {

        int i = 1;
        int max = 8;
        int min = 2;
        int range = (max - min) + 1;
        int sleepTime;
        long tempoInicioProducao = System.currentTimeMillis();

        while (produzir) {

            if (lagar.getFilaCaminhoes().size() != 3) {

                sleepTime = (int) ((Math.random() * range) + min);

                System.out.println("Execução " + i + "/10 da plantação " + this.nomePlantacao + " com espera de "
                        + sleepTime + " segundos.");

                try {
                    Thread.sleep(sleepTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;

            } else {

                System.out.println("Plantação " + this.nomePlantacao + " em espera!");

                try {
                    Thread.sleep(1000);
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
