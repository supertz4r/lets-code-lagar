package teste;

import regras.VerificaRegras;

public class VerificaRegrasTeste {

    public static void main(String[] args) throws Exception {
        VerificaRegras regras = new VerificaRegras();

        System.out.println(

            "\n\ngetDadosArquivo -> " + regras.getDadosArquivo()+ 

            "\n\ngetDataArquivo -> " + regras.getDataArquivo() + 

            "\n\ngetAzeitonas -> " + regras.getAzeitonas() + 

            "\n\ngetPlantacoes -> " + regras.getPlantacoes() + 

            "\n\ngetCapacidadeRecepcaoLagar -> " + regras.getCapacidadeRecepcaoLagar() + 

            "\n\ngetRangeCapacidadeCaminhao -> " + regras.getRangeCapacidadeCaminhao()[0] + " - " + regras.getRangeCapacidadeCaminhao()[1] + 

            "\n\ngetRangeEnchimentoSegundos -> " + regras.getRangeEnchimentoSegundos()[0] + " - " + regras.getRangeEnchimentoSegundos()[1] + 

            "\n\ngetRangeRecepcao -> " + regras.getRangeRecepcao()[0] + " - " + regras.getRangeCapacidadeCaminhao()[1] + 

            "\n\ngetTempoXToneladas -> " + regras.getTempoXToneladas() + 

            "\n\ngetMaxCaminhoesNaFila -> " + regras.getMaxCaminhoesNaFila() + 

            "\n\ngetMinCaminhoesNaFila -> " + regras.getMinCaminhoesNaFila() + 

            "\n\ngetTempoExecucaoGeralMax -> " + regras.getTempoExecucaoGeralMax()

        );
    }

}
