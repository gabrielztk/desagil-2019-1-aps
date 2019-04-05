package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends JPanel implements ActionListener {

    // A ideia é que essa componente gráfica represente
    // uma calculadora específica. Essa calculadora que
    // está sendo representada é guardada como atributo.
    private final Gate gate;

    // A classe JTextField representa um campo de texto.
    private final JCheckBox resultBox;
    private final JCheckBox[] listaBoxes;
    private final int[] listaPinos;
    private final Switch[] listaSwitchs;


    public GateView(Gate gate) {
        this.gate = gate;

        JLabel inputLabel = new JLabel("Input");
        JLabel resultLabel = new JLabel("Output");
        resultBox = new JCheckBox();
        listaBoxes = new JCheckBox[this.gate.getInputSize()];
        listaPinos = new int[this.gate.getInputSize()];
        listaSwitchs = new Switch[this.gate.getInputSize()];

        add(inputLabel);
        // Nada de especial na construção dos campos.
        for (int x = 0; x < this.gate.getInputSize(); x++) {

            listaBoxes[x] = new JCheckBox();
            listaSwitchs[x] = new Switch();
            listaPinos[x] = x;
            gate.connect(x, listaSwitchs[x]);

            add(listaBoxes[x]);


            listaBoxes[x].addActionListener(this);

        }

        // A classe JLabel representa um rótulo, ou seja,
        // um texto não-editável que queremos colocar na
        // interface para identificar alguma coisa. Não
        // precisa ser atributo, pois não precisamos mais
        // mexer nesses objetos depois de criar e adicionar.


        // Um JPanel tem um layout, ou seja, um padrão para
        // organizar as componentes dentro dele. A linha abaixo
        // estabelece um dos padrões mais simples: simplesmente
        // colocar uma componente debaixo da outra, alinhadas.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Colocamos todas componentes aqui no contêiner.


        add(resultLabel);
        add(resultBox);
        resultBox.setEnabled(false);

        // Uma campo de texto tem uma lista de observadores que
        // reagem quando o usuário dá Enter depois de digitar.
        // Usamos o método addActionListener para adicionar esta
        // instância de CalculatorView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ActionListener" lá em cima.


        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.


        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        update();
    }

    private void update() {
        boolean verdade;


        for (int numero : listaPinos) {

            verdade = listaBoxes[numero].isSelected();

            if (verdade) {
                listaSwitchs[numero].turnOn();
            } else {
                listaSwitchs[numero].turnOff();
            }

        }
        resultBox.setSelected(gate.read());


        //double result = calculator.calculate(weight, radius);

        // O contrário também vale! Para colocar um double no
        // campo, precisamos antes converter ele para String.
        //resultField.setText(Double.toString(result));
    }

    // O que esta componente deve fazer quando o usuário der um
    // Enter depois de digitar? Apenas chamar o update, é claro!
    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
