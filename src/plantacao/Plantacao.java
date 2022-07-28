package plantacao;

public class Plantacao implements Runnable {

    final private String nomePlantacao;
    final private String variedadePlantacao;
    final private Integer distanciaLagarSegundos;
    final private long inicioProducao;

    private Plantacao(Builder builder) {
        this.nomePlantacao = builder.nomePlantacao;
        this.variedadePlantacao = builder.variedadePlantacao;
        this.distanciaLagarSegundos = builder.distanciaLagarSegundos;
        this.inicioProducao = System.currentTimeMillis();
    }

    public static class Builder {

        private String nomePlantacao;
        private String variedadePlantacao;
        private Integer distanciaLagarSegundos;

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

        public Plantacao build() {
            return new Plantacao(this);
        }

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
        long tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        while (tempoProducaoMinutos < 2) {

            sleepTime = (int) ((Math.random() * range) + min);

            System.out.println("Execução " + i + "/10 da plantação " + this.nomePlantacao + " com espera de "
                    + sleepTime + " segundos.");

            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;

            tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        }

    }

}
