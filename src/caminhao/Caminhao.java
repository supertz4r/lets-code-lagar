package caminhao;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import plantacao.Plantacao;
import regras.RegrasLagar;
import regras.VerificaRegras;

public class Caminhao {
    private Plantacao plantacao;
    private Integer capacidade;
    private Boolean cheio;
    private RegrasLagar regras;

    public Caminhao(Builder builder) {
        this.plantacao = builder.plantacao;
        this.capacidade = builder.capacidade;
        this.cheio = builder.cheio;
        this.regras = builder.regras;
    }

    public static class Builder {

        private Plantacao plantacao;
        private Integer capacidade;
        private Boolean cheio;
        private VerificaRegras regras = new VerificaRegras();

        public Builder plantacao(Plantacao plantacao) {
            this.plantacao = plantacao;
            return this;
        }

        public Builder capacidade() {
            int[] rangeCapacidade = regras.getRangeCapacidadeCaminhao();
            this.capacidade = new Random().ints(rangeCapacidade[0], rangeCapacidade[1]).findFirst().getAsInt();
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
