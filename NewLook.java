import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.FileNotFoundException;

class NewLook implements ActionListener{

    MainFrame an;

    public NewLook(MainFrame an) {
        this.an = an;
    }

    public void specialDo() throws FileNotFoundException {
        an.init();
    }

    public void actionPerformed(ActionEvent e)  {
        an.getContentPane().removeAll();
        try {
            specialDo();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        an.validate();
        an.repaint();
    }
}

