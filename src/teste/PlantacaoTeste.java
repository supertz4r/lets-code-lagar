package teste;

import lagar.Lagar;
import plantacao.Azeitonas;
import plantacao.Plantacao;

public class PlantacaoTeste {

    public static void main(String[] args) throws Exception {

        Lagar lagar = new Lagar.Builder().build();;

        Plantacao plantacaoA = new Plantacao.Builder().nomePlantacao("G1")
                .variedadePlantacao(Azeitonas.GALEGA)
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build();

//        Plantacao plantacaoB = new Plantacao.Builder().nomePlantacao("G2")
//                .variedadePlantacao("Galega")
//                .distanciaLagarSegundos(4)
//                .lagar(lagar)
//                .build();
//
//        Plantacao plantacaoC = new Plantacao.Builder().nomePlantacao("C1")
//                .variedadePlantacao("Cordovil")
//                .distanciaLagarSegundos(4)
//                .lagar(lagar)
//                .build();
//
//        Plantacao plantacaoD = new Plantacao.Builder().nomePlantacao("C2")
//                .variedadePlantacao("Cordovil")
//                .distanciaLagarSegundos(4)
//                .lagar(lagar)
//                .build();
//
//        Plantacao plantacaoE = new Plantacao.Builder().nomePlantacao("P1")
//                .variedadePlantacao("Picual")
//                .distanciaLagarSegundos(4)
//                .lagar(lagar)
//                .build();

        Thread p1 = new Thread(plantacaoA);
//        Thread p2 = new Thread(plantacaoB);
//        Thread p3 = new Thread(plantacaoC);
//        Thread p4 = new Thread(plantacaoD);
//        Thread p5 = new Thread(plantacaoE);

        p1.start();

//        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();

    }

}
