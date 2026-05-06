# Sockets-Concorrente-Paralela
Laboratório sockets : Luiz Eduardo Camurça e Luis Eduardo Alburquerque 

Documentação de Aula Prática: Computação Distribuída com Sockets

**Objetivo**
Exploração de paralelismo com memória distribuída para a resolução de problemas distribuídos através da comunicação entre processos via Sockets em Java.

**Implementação Realizada**
O projeto consistiu na criação de um sistema para multiplicação de matrizes operando em arquitetura cliente-servidor, junto de uma mensagem :

* **Servidor (Balanceador de Carga):**
  Vinculado à porta local 12346, o processo aguardou conexões, recebeu arrays bidimensionais e uma mensagem, realizou o cálculo aritmético e devolveu o resultado.

* **Cliente (Trabalhador):**
  Conectou-se ao endereço IP do servidor para enviar os dados de entrada e receber o objeto processado.

**Curso:** Ciência da Computação (Unifor)
**Disciplina:** Computação Paralela e Concorrente
