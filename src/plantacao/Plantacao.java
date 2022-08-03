package plantacao;

import caminhao.Caminhao;
import lagar.Lagar;
import regras.RegrasLagar;
import regras.VerificaRegras;

import java.util.Map;

public class Plantacao implements Runnable {

    final private String nomePlantacao;
    final private Azeitonas variedadePlantacao;
    final private Integer distanciaLagarSegundos;
    private boolean produzir;
    private boolean emEspera;
    private Lagar lagar;
    private RegrasLagar regras = new VerificaRegras();

    private Plantacao(Builder builder) {
        this.nomePlantacao = builder.nomePlantacao;
        this.variedadePlantacao = builder.variedadePlantacao;
        this.distanciaLagarSegundos = builder.distanciaLagarSegundos;
        this.lagar = builder.lagar;
        this.produzir = true;
        this.emEspera = false;
    }

    public void setProduzir(boolean produzir) {
        this.produzir = produzir;
    }

    public void setEmEspera(boolean emEspera) {
        this.emEspera = emEspera;
    }

    public String getNomePlantacao() {
        return nomePlantacao;
    }

    public Azeitonas getVariedadePlantacao() {
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
        Map<String, Integer> tempoXToneladas = regras.getTempoXToneladas();
        int ToneladasPorSegundo = tempoXToneladas.get("toneladas") / tempoXToneladas.get("tempo");
        long tempoInicioProducao = System.currentTimeMillis();

        while (produzir) {
            if (lagar.getTamanhoFila() < lagar.getCapacidadeLagar()) {
                Caminhao caminhao = new Caminhao.Builder()
                        .plantacao(this)
                        .capacidade()
                        .cheio(false)
                        .build();

                sleepTime = (caminhao.getCapacidade() / ToneladasPorSegundo);

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

                if (!emEspera) {
                    System.out.println("Enviando caminhão de " + caminhao.getCapacidade()
                            + " toneladas da plantação " + this.nomePlantacao + " para o Lagar. Tempo de viagem: "
                            + getDistanciaLagarSegundos() + " segundos");

                    // Simula o transporte do caminhão.
                    try {
                        Thread.sleep(getDistanciaLagarSegundos() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lagar.enfileraCaminhao(caminhao);
                }
                i++;
            } else {

                System.out.println("Plantação " + this.nomePlantacao + " em espera!");
                // Esse bloco está parando a produção da plantação.
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        long tempoFimProducao = System.currentTimeMillis();
        System.out.println("Plantação " + nomePlantacao + " foi finalizada! Execução total: "
                + ((tempoFimProducao - tempoInicioProducao) / 60000) + "m"
                + (((tempoFimProducao - tempoInicioProducao) - 60000) / 120000) + "s");
    }

    public static class Builder {

        private String nomePlantacao;
        private Azeitonas variedadePlantacao;
        private Integer distanciaLagarSegundos;
        private Lagar lagar;

        public Builder nomePlantacao(String nomePlantacao) {
            this.nomePlantacao = nomePlantacao;
            return this;
        }

        public Builder variedadePlantacao(Azeitonas variedadePlantacao) {
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
}
