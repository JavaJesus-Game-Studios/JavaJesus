package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.javajesus.game.ZombieSurvival;

public class ZombieGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

    protected JPanel window = new JPanel();
    private JButton survival, endurance, back, quit;
    private Rectangle rsurvival, rendurance, rback, rquit;
    //Configuration config = new Configuration();

    private int width = 800;
    private int height = 400;
    protected int button_width = 80;
    protected int button_height = 40;

    public ZombieGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // setUndecorated(true);
        setTitle("Javaendurance Options:");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(window);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        window.setLayout(null);
        drawButtons();
        
        repaint();
    }

    private void drawButtons() {
        survival = new JButton("Survival");
        rsurvival = new Rectangle((width / 2) - (button_width / 2), 90,
                button_width, button_height);
        survival.setBounds(rsurvival);
        window.add(survival);

        endurance = new JButton("Endurance");
        rendurance = new Rectangle((width / 2) - (button_width / 2), 140,
                button_width, button_height);
        endurance.setBounds(rendurance);
        window.add(endurance);

        back = new JButton("Back");
        rback = new Rectangle((width / 2) - (button_width / 2), 190,
                button_width, button_height);
        back.setBounds(rback);
        window.add(back);

        quit = new JButton("Quit!");
        rquit = new Rectangle((width / 2) - (button_width / 2), 240,
                button_width, button_height);
        quit.setBounds(rquit);
        window.add(quit);

        survival.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ZombieSurvival().start();
            }
        });
        endurance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Infinite monsters will attack you to death");
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            dispose();
            new Launcher(0);    
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
