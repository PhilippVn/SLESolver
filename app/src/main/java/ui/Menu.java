package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    JFrame jFrame;

    public Menu(JFrame jf){
        this.jFrame = jf;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createTitledBorder("Menu"));
        init();
    }

    private void init(){
        JButton gaussB = new JButton("Gauss");
        JButton luDecompB = new JButton("LU Decomposition");
        add(gaussB);
        add(luDecompB);

        JPanel gaussP = new Gauss();
        JPanel luDecompositionP = new LUDecomposition();
        gaussB.addActionListener(new MenuAction(gaussP));
        luDecompB.addActionListener(new MenuAction(luDecompositionP));
        setVisible(true);
    }

    private class MenuAction implements ActionListener {

        private JPanel panel;
        private MenuAction(JPanel pnl) {
            this.panel = pnl;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ((Ui)Menu.this.jFrame).changePanel(panel);
        }
    }

}
