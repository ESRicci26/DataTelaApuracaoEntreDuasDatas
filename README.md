Documentação DataTelaApuracaoEntreDatas.java
--------------------------------------------

Esta aplicação Java Swing terá uma interface gráfica com campos para inserir datas, um combo box para selecionar o dia da semana e um campo de texto
para exibir os resultados.

Criação da Interface Gráfica:
-----------------------------
Um JFrame principal é criado para conter todos os componentes.
JPanel é usado com GridBagLayout para organizar os componentes na janela.

Componentes da Interface:
-------------------------
JFormattedTextField para entrada das datas inicial e final, formatadas no padrão "dd/MM/yyyy".
JComboBox para selecionar o dia da semana desejado.
JButton para acionar o cálculo.
JTextArea para exibir os resultados das datas.
JTextField para mostrar a quantidade de dias da semana selecionados.

Ação do Botão:
--------------
O botão "Apurar" possui um ActionListener que processa a entrada de dados.
Usa SimpleDateFormat para converter as datas de entrada.
Gera uma lista de todas as datas no intervalo especificado.
Filtra as datas que correspondem ao dia da semana selecionado.
Exibe os resultados na JTextArea e a contagem no JTextField.
Este código oferece uma interface simples e funcional para calcular e exibir as datas de um dia da semana específico dentro de um intervalo fornecido.
