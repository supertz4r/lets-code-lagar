package teste;

import caminhao.Caminhao;
import lagar.Lagar;
import lagar.Processamento;
import plantacao.Azeitonas;
import plantacao.Plantacao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LagarTeste {

    public static void main(String[] args) throws InterruptedException {

        Lagar lagar = new Lagar.Builder().build();

        Plantacao plantacaoA = new Plantacao.Builder().nomePlantacao("G1")
                .variedadePlantacao(Azeitonas.GALEGA)
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build();

        Caminhao c1 = new Caminhao.Builder()
                .plantacao(plantacaoA)
                .capacidade()
                .cheio(false)
                .build();

//        testeUmCaminhao(lagar, c1);

        System.out.println("--- TESTE DO LAGAR COM 3 CAMINHÕES ---");
        Caminhao c2 = new Caminhao.Builder()
                .plantacao(plantacaoA)
                .capacidade()
                .cheio(false)
                .build();

        Caminhao c3 = new Caminhao.Builder()
                .plantacao(plantacaoA)
                .capacidade()
                .cheio(false)
                .build();

        List<Caminhao> caminhoes = List.of(c1, c2, c3);
        // Dando ArrayIndexOutOfBounds
        testeMultiplosCaminhoes(lagar, caminhoes);

        Caminhao c4 = new Caminhao.Builder()
                .plantacao(plantacaoA)
                .capacidade()
                .cheio(false)
                .build();
        List<Caminhao> caminhoes2 = List.of(c1, c2, c3, c4);
        //Thread fica em espera indefinidamente
//        testeMultiplosCaminhoes(lagar, caminhoes2);

    }

    private static void testeMultiplosCaminhoes(Lagar lagar, List<Caminhao> caminhoes) {
        caminhoes.forEach((caminhao) -> lagar.enfileraCaminhao(caminhao));
        System.out.printf("Tamanho da fila: %-10s%n", lagar.getTamanhoFila());
        while (lagar.getTamanhoFila() > 0) {
            Caminhao caminhao = lagar.processaCaminhao();
            new Thread(new Processamento(lagar, caminhao)).start();
            System.out.println(lagar.getTamanhoFila());
        }
    }

    private static void testeUmCaminhao(Lagar lagar, Caminhao c1) throws InterruptedException {
        lagar.enfileraCaminhao(c1);
        System.out.printf("Tamanho da fila: %-10s%n", lagar.getTamanhoFila());
        Thread processa = new Thread(new Processamento(lagar, c1));
        processa.start();
        synchronized (processa) {
            processa.wait();
            System.out.printf("Tamanho da fila: %-10s%n%n", lagar.getTamanhoFila());
        }
    }

}
