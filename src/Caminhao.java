import java.util.Random;

public class Caminhao {
    Integer capacidade;
    Boolean cheio;

    public Caminhao() {
        this.capacidade = new Random().ints(4, 16).findFirst().getAsInt();
        this.cheio = false;
    }

    public void encher() {

    }

    public void esvaziar() {

    }

}
