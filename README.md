<h1 align="center"> Projeto Final do Módulo - Lagar </h1>
<h4 align="center">
Crie uma aplicação que faça a gestão de transporte das plantações de azeitona até o lagar.
A configuração da aplicação é com base na leitura e interpretação do arquivo de regras.txt</h4>
<br />

# Modelo de Relatório

Durante o processo deve ir registrando no arquivo de relatório relatorio-1991.txt a seguinte informação:

```
24/02

09:24:56 - 0004 >> 4 toneladas de Galega na recepção 1 de origem da plantação 2 com tempo total de 8 segundos.
09:24:57 - 0012 >> 8 toneladas de Picual na recepção 2 de origem da plantação 5 com o tempo total de 6 segundos.
09:24:58 - 0018 >> 6 toneladas de Cordovil na recepção 1 de origem da plantação 3 com o tempo total de 6 segundos.
```

- A sequência de números 0000 são a quantidade de toneladas acumuladas.
- O tempo total de X segundos é o tempo des que o caminhão começa a ser carregado, incluíndo o tempo do percurso e o tempo de entrega.

A data a considerar não será a de 24/02/1991, mas sim a data de 28/04/2022.

Deve então considerar data correta de 28/04/2022 tanto no arquivo de regras como no arquivo de relatório.

# Quesitos de Avaliação

- Modelagem Orientação a Objetos.
- Padrões de projeto.
- Domínio de Stream, Lambdas, NIO, RegEx, Java Time e Threads.
- Regras de negócio e lógica utiliza.

# Estrutura de implementação

- Lagar:

  - [x] Controlar o número das receptoras
  - [ ] Controla o total de toneladas de azeitona já recebidas

- Plantações:

  - [x] Geram caminhões
  - [x] Enchem os caminhões (2 ton/seg)
  - [x] Despacham os caminhões
  - [x] Requisitam fila para o processamento
  - [ ] Paralizar as plantações quando a capacidade máxima da fila no lagar for alcançada: Mais ou Menos (as plantações não produzem, mas não aguardam propriamente em um wait e sim em um while-sleep)
  - [x] Reiniciar a produção das plantações quando a capacidade mínima da fila no lagar for alcançada
  - [x] Apenas caminhões já despachados podem ser processados após o tempo de execução da produção

- Caminhões:

  - [x] Fazer o transporte das azeitonas para o lagar

- Fazenda (Gerenciador da plantação toda):
  - [x] Executa por 2 minutos
  - [x] Aciona o processamento de caminhões no lagar
  - [x] Espera o processamento de todos os caminhões, na fila ou em trânsito, antes de finalizar

# Perguntas

- Só um caminhão é carregado por vez na plantação? **Sim**
- Existem infinitos caminhões? **Sim**
- O tempo de execução geral é contado a partir da criação da fazenda? **Sim**
- A capacida de recepção no lagar é dinâmica (arquivo de configurações)? **Sim**

# Checklist da entrega

- Padrões de projeto:

  - Singleton: Fazenda
  - Builder: Plantacoes

- Modelagem OO:

  - Packages
  - Interfaces
  - Herança
  - Polimorfismo

- Streams:

  - VerificaRegras -> getAzeitonas

- Lambdas:

  - VerificaRegras -> getAzeitonas

- NIO:

  - VerificaRegras -> leArquivo
  - Relatorio -> escreveRelatorio

- Regex:

  - VerificaRegras

- JavaTime:

  - VerificaRegras -> getDataArquivo
  - Relatorio -> adicionaLinha

- Threads:
