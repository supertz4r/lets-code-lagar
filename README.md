<h1 align="center"> Projeto Final do Módulo - Lagar </h1>
<h4 align="center">Enunciado</h4>

Crie uma aplicação que faça a gestão de transporte das plantações de azeitona até o lagar.

A configuração da aplicação é com base na leitura e interpretação do arquivo de regras.txt:

```
Data:
    24/02/1991

3 Variedades de Azeitonas:
    Galega
    Cordovil
    Picual

5 Plantações de Azeitonas:
    2 plantações de Galega com a distância de 4 segundos para o lagar.
    2 plantações de Cordovil com a distância de 3 segundos para o lagar.
    1 plantação de Picual com a distância de 2 segundos para o lagar.

3 Capacidades de Recepção no lagar em simultâneo.

Capacidade dos Caminhões de transporte:
    Varia entre 4 até 16 toneladas de azeitonas.

Configurações Gerais:
    Cada plantação enche um caminhão entre 2 a 8 segundos.
    Cada recepção demora entre 2 a 8 segundos para ser processada.
    Sendo que 2 segundos corresponde a 4 toneladas.
    Quando atingir 12 caminhões na fila em espera no Lagar, as plantações param de produzir.
    Quando o lagar voltar a atingir 4 caminhões em espera, então as plantações podem enviar mais.

Finalização:
    Com 2 minutos de execução geral as plantações fecham a sua produção.
    O lagar continua recebendo os caminhões que estão na fila ou que estejam a caminho.
```

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
- Plantações:

* Executam por 2 minutos:

- Caminhões: Threads
- Fazenda: Gerenciador

# Perguntas

- Só um caminhão é carregado por vez na plantação? Sim.

# Checklist da entrega

- Padrões de projeto:

* Singleton: Fazenda
* Builder: Plantacoes

- Modelagem OO:

* Packages
* Interfaces
* Herança
* Polimorfismo

- Streams:

- Lambdas:

- NIO:

- Regex:

- JavaTime:

- Threads:
