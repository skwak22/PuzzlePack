import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

class G3 extends NewLook implements ActionListener {

    public G3(MainFrame an) {
        super(an);
    }

    public void specialDo() throws FileNotFoundException {
        an.init4();
    }
}