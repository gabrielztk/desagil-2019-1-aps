package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandSum;
    private final NandGate nandCarry;


    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(0, nandLeft);

        nandSum = new NandGate();
        nandSum.connect(0, nandTop);
        nandSum.connect(1, nandBottom);

        nandCarry = new NandGate();
        nandCarry.connect(0, nandLeft);
        nandCarry.connect(1, nandLeft);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandSum.read();
        } else {
            return nandCarry.read();
        }

    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
