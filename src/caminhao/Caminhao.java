package caminhao;

import java.util.Random;

import plantacao.Plantacao;

public class Caminhao {
    private Plantacao plantacao;
    private Integer capacidade;
    private Boolean cheio;

    public Caminhao(Builder builder) {
        this.plantacao = builder.plantacao;
        this.capacidade = builder.capacidade;
        this.cheio = builder.cheio;
    }

    public static class Builder {

        private Plantacao plantacao;
        private Integer capacidade;
        private Boolean cheio;

        public Builder plantacao(Plantacao plantacao) {
            this.plantacao = plantacao;
            return this;
        }

        public Builder capacidade() {
            this.capacidade = new Random().ints(4, 16).findFirst().getAsInt();
            return this;
        }

        public Builder cheio(boolean ischeio) {
            this.cheio = ischeio;
            return this;
        }

        public Caminhao build() {
            return new Caminhao(this);
        }

    }

    public Plantacao getPlantacao() {
        return plantacao;
    }

    public void encher() {
        this.cheio = true;
    }

    public void descarregar() {
        this.cheio = false;
    }

    public Boolean isCheio() {
        return cheio;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

}
