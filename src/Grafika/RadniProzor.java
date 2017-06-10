package Grafika;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Matija on 10 Jun 17.
 */
public class RadniProzor extends JFrame {

    private JMenuBar menuBar;
    private JMenu file, help;
    private JMenuItem newFile, save, saveAs, closeThis, quit, about;
    private JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel statusbar = new JPanel(new GridLayout(1,2));

    private PopupDialog aboutDialog = new PopupDialog(this, "About", "© Матија Лукић 2017 ЕТФ Београд");

    public RadniProzor(JPanel prozor){
        super("Vektorska grafika");

        // Osnovna podesavanja
        setSize(800,800);
        prozor = new Prozor();
        getContentPane().add(prozor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Fill menu
        makeMenu();
        // Fill toolbar
        makeTools();

        //  Fill status bar
        makeStatus();

        setVisible(true);
    }


    private void makeMenu(){
        // Menu
        menuBar = new JMenuBar();

        // File meni
        file = new JMenu("File");
        menuBar.add(file);

        // Novi fajl
        newFile = new JMenuItem("New Drawing");
        file.add(newFile);
        file.addSeparator();

        // Save - zapamti
        save = new JMenuItem("Save");
        file.add(save);

        // Save as
        saveAs = new JMenuItem("Save As");
        file.add(saveAs);
        file.addSeparator();

        // Close this
        closeThis = new JMenuItem("Close This");
        file.add(closeThis);

        // Quit
        quit = new JMenuItem("Quit");
        file.add(quit);


        // Help menu
        help = new JMenu("Help");

        about = new JMenuItem("About");
        about.addActionListener((e)->{
            aboutDialog.setVisible(true);
        });
        help.add(about);

        menuBar.add(help);


        setJMenuBar(menuBar);
    }
    // Fill the toolbar
    private void makeTools(){
        toolbar.setBackground(Color.WHITE);
        toolbar.add(new JLabel("Matija"));

        add(toolbar, BorderLayout.NORTH);
    }

    private void makeStatus(){
        statusbar.setBackground(Color.DARK_GRAY);

        statusbar.add(new JLabel("Levo", JLabel.LEFT));
        statusbar.add(new JLabel("Desno", JLabel.RIGHT));

        add(statusbar, BorderLayout.SOUTH);
    }

}
