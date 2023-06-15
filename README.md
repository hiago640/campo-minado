# Campo Minado em Java

Este repositório contém o projeto do jogo Campo Minado desenvolvido em Java como parte do curso "Java 2022 COMPLETO: Do Zero ao Profissional + Projetos!".

## Funcionalidades

O jogo Campo Minado possui as seguintes funcionalidades:

- Tabuleiro com células que podem conter bombas ou números indicando a quantidade de bombas nas células vizinhas.
- Marcar células suspeitas de conterem bombas.
- Revelar células para avançar no jogo.

## Como jogar

- Ao iniciar o jogo, você verá um tabuleiro vazio.
- Clique com o botão esquerdo do mouse em uma célula para revelá-la.
  - Se a célula contiver uma bomba, o jogo terminará.
  - Se a célula estiver vazia, será revelado o número de bombas nas células vizinhas.
- Clique com o botão direito do mouse em uma célula para marcar ou desmarcar uma suspeita de bomba.
- Continue revelando células e marcando suspeitas de bombas até que todas as células sem bombas sejam reveladas.

## Tecnologias utilizadas

- Linguagem de programação Java
- Biblioteca Swing para a interface gráfica

## Como executar o projeto

1. Certifique-se de ter o ambiente de desenvolvimento Java configurado em sua máquina.
2. Clone este repositório para o diretório desejado em seu computador.
3. Abra o projeto em sua IDE Java de preferência (Eclipse, IntelliJ, NetBeans, etc.).
4. Compile e execute o arquivo `CampoMinado.java`.

```shell
javac CampoMinado.java
java CampoMinado
```