package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Sandbox extends JFrame {
	
	private static final long serialVersionUID = 1L;

    protected JPanel window = new JPanel();
    private JButton zombie, jesus, back, quit;
    private Rectangle rzombie, rjesus, rback, rquit;
    //Configuration config = new Configuration();

    private int width = 800;
    private int height = 400;
    protected int button_width = 80;
    protected int button_height = 40;

    public Sandbox() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // setUndecorated(true);
        setTitle("JavaJesus Options:");
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
        zombie = new JButton("ZOMBIE MODE");
        rzombie = new Rectangle((width / 2) - (button_width / 2), 90,
                button_width, button_height);
        zombie.setBounds(rzombie);
        window.add(zombie);

        jesus = new JButton("Jesus Mode");
        rjesus = new Rectangle((width / 2) - (button_width / 2), 140,
                button_width, button_height);
        jesus.setBounds(rjesus);
        window.add(jesus);

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

        zombie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ZombieGUI();
            }
        });
        jesus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("There can be only one...");
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            dispose();
            new Launcher(0).startMenu();    
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
