package caminhao;

import java.util.Random;

import fazenda.Fazenda;

public class Caminhao {
    private Fazenda fazenda;
    private Integer capacidade;
    private Boolean cheio;

    public Caminhao(Builder builder) {
        this.fazenda = builder.fazenda;
        this.capacidade = builder.capacidade;
        this.cheio = builder.cheio;
    }

    public static class Builder {

        private Fazenda fazenda;
        private Integer capacidade;
        private Boolean cheio;

        public Builder fazenda(Fazenda fazenda) {
            this.fazenda = fazenda;
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
