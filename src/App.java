public class App {
    public static void main(String[] args) throws Exception {

        VerificaRegras regras = new VerificaRegras("regras.txt");

        System.out.println("getPlantacoes\n\t" + regras.getPlantacoes());
        System.out.println("getCapacidadeRecepcaoLagar\n\t" + regras.getCapacidadeRecepcaoLagar());
        System.out.print("getRangeCapacidadeCaminhao\n\t");
        for (int i = 0; i < regras.getRangeCapacidadeCaminhao().length; i++) {
            System.out.print(regras.getRangeCapacidadeCaminhao()[i] + " ");
        }
        
    }
}
