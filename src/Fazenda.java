public class Fazenda {
    Double tempoEncherEsvaziarCaminhao;
    private static Fazenda fazenda;

    private Fazenda() {
        criaPlantacoes();
        criaLagar();
    }

    public static Fazenda getInstance() {
        if (fazenda == null) {
            return new Fazenda();
        }
        return fazenda;
    }

    public void criaPlantacoes() {

    }

    public void criaLagar() {

    }
}
