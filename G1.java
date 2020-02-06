import java.awt.event.ActionListener;
//switcher to game panels
class G1 extends NewLook implements ActionListener {

    public G1(MainFrame an) {
        super(an);
    }

    public void specialDo() {
        an.init2();
    }
}