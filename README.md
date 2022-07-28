## Modelo de Relatório

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

## Quesitos de Avaliação

- Modelagem Orientação a Objetos.
- Padrões de projeto.
- Domínio de Stream, Lambdas, NIO, RegEx, Java Time e Threads.
- Regras de negócio e lógica utiliza.

# Estrutura de implementação

- Lagar: 1 Thread
- Plantações: Threads
- Caminhões: Threads
- Fazenda: Gerenciador

# Perguntas

- Só um caminhão é carregado por vez na plantação?
-
