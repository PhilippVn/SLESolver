package ui;


import javax.swing.*;

public class Ui extends JFrame {
    private Menu menu;

    public Ui(){
        this.menu = new Menu(this);
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        changePanel(menu);
        setVisible(true);
    }

    public void changePanel(JPanel panel){
        getContentPane().removeAll();
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        repaint();
    }
}
