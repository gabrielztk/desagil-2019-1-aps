package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private final Light light = new Light();

    private final Image image;
    private Color color;


    public GateView(Gate gate) {
        super(350, 200);
        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];
        int[] yValue = new int[2];
        yValue[0] = 60;
        yValue[1] = 120;

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            if (inputSize == 1) {
                add(inputBoxes[i], 28, 90, 20, 20);
            } else {
                add(inputBoxes[i], 28, yValue[i], 20, 20);
            }

            inputBoxes[i].addActionListener(this);

            gate.connect(i, switches[i]);
        }

        light.connect(0, gate);
        light.setR(255);


        color = new Color(light.getR(), light.getG(), light.getB());


        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }


        color = new Color(light.getR(), light.getG(), light.getB());
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {


        int x = event.getX();
        int y = event.getY();

        if (Math.pow(x - 317.2, 2) + Math.pow(y - 99.5, 2) < Math.pow(17.5, 2)) {


            color = JColorChooser.showDialog(this, null, color);

            light.setR(color.getRed());
            light.setG(color.getGreen());
            light.setB(color.getBlue());

            color = new Color(light.getR(), light.getG(), light.getB());

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 25, 25, 300, 150, this);

        g.setColor(color);
        g.fillOval(300, 82, 35, 35);

        getToolkit().sync();
    }
}
