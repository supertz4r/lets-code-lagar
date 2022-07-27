import java.util.Random;

public class Caminhao {
    private Integer capacidade;
    private Boolean cheio;

    public Caminhao() {
        this.capacidade = new Random().ints(4, 16).findFirst().getAsInt();
        this.cheio = false;
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
