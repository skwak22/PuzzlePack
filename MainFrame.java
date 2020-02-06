import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame {
    Container ct;
    public void clearFrame() {
        ct.removeAll();
    }
    public void init() {
        ct = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ct.setLayout(new BoxLayout(ct,BoxLayout.PAGE_AXIS));
        ct.add(Box.createRigidArea(new Dimension(50,10)));
        JLabel message = new JLabel("Welcome to the Puzzle Pack!");
        ct.add(message);
        message = new JLabel("Select a game to play below!");
        ct.add(message);
        ct.add(Box.createRigidArea(new Dimension(0,10)));

        JButton jb = new JButton("Skyscraper");
        ct.add(jb);
        jb.addActionListener(new G1(this));

        ct.add(Box.createRigidArea(new Dimension(0,10)));
        jb = new JButton("Kakurasu");
        ct.add(jb);
        jb.addActionListener(new G2(this));
        ct.add(Box.createRigidArea(new Dimension(0,10)));

        jb = new JButton("KenKen");
        ct.add(jb);
        jb.addActionListener(new G3(this));


    }

    public void init2() {

        Skyscraper frame = new Skyscraper(this);
        frame.createGame();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(600,600));
        frame.repaint();
    }
    public void init3() {
        Kakurasu frame = new Kakurasu(this);
        frame.createGame();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(600,600));
        frame.repaint();
    }
    public void init4() throws FileNotFoundException {

        KenKen frame = new KenKen(this);
        frame.createGame();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(600,600));
        frame.repaint();

    }

    public static void main(String[] args) {

        MainFrame one = new MainFrame();
        one.init();
        one.pack();
        one.setVisible(true);
        one.setSize(new Dimension(100,200));
        one.repaint();
    }
}
