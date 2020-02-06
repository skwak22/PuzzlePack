import java.awt.event.ActionListener;

class M1 extends NewLook implements ActionListener {

    public M1 (MainFrame an) {
        super(an);
    }

    public void specialDo() {
        an.clearFrame();
        an.init();
    }
}