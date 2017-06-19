package grafika;

import grafika.alati.*;
import grafika.elementi.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Matija on 10 Jun 17.
 */
public class RadniProzor extends JFrame {

    private JMenuBar menuBar;
    private JMenu file, help;
    private JMenuItem newFile, save, load, closeThis, quit, about;

    // Toolbar elements
    private JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public ButtonGroup toolsGroup = new ButtonGroup();
    public static final String[] toolNames = new String[]{ "Move", "Erase", "Line", "Lines", "Closed Lines", "Rectangle"};
    public static String currentToolName = toolNames[0];
    public static final JComboBox<Integer> lineThick = new JComboBox<>(new Integer[]{1,2,3,4,5});
    public static ColorChooserButton lineColor = new ColorChooserButton(Color.BLACK);

    // Canvas elementi
    private static WorkPanel workPanel = new WorkPanel();
    // List of tools
    public static final HashMap<String, Alat> toolsList = new HashMap<>();
    static{
        // Unosimo u hesh mapu
        toolsList.put(toolNames[0], new Pomeranje());
        toolsList.put(toolNames[1], new Brisanje());
        toolsList.put(toolNames[2], new CrtanjeLinija());
        toolsList.put(toolNames[3], new CrtanjeIzlomljenih());
        toolsList.put(toolNames[4], new CrtanjeZatvorenih());
        toolsList.put(toolNames[5], new CrtanjePravugaonika());

        // Ako se promeni
        lineThick.addActionListener((e) -> {
            updateStatus();
        });

    }


    // Status bar
    private JPanel statusbar = new JPanel(new GridLayout(1,2));
    public static JLabel leftLabel, rightLabel;




    private Point start, end;

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
        // Fill canvas
        makeCanvas();
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
        newFile.addActionListener(e -> {
            WorkPanel.drawing.deleteAll();
            workPanel.clear();
            workPanel.repaint();
        });
        file.add(newFile);
        file.addSeparator();

        // Save - zapamti
        save = new JMenuItem("Save");
        // Zapmti
        save.addActionListener((e) -> {
            // file chooser
            JFileChooser saveFileDialog = new JFileChooser();

            if(saveFileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File fileTOSave = saveFileDialog.getSelectedFile();

                WorkPanel.drawing.saveFile(fileTOSave);


            }
        });
        file.add(save);

        // Save as
        load = new JMenuItem("Load");
        load.addActionListener((e)->{
            JFileChooser loadFileDialog = new JFileChooser();

            if(loadFileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                File fileToLoad = loadFileDialog.getSelectedFile();

                WorkPanel.drawing.loadFile(fileToLoad);
                workPanel.repaint();
            }
        });
        file.add(load);
        file.addSeparator();

        // Close this
        closeThis = new JMenuItem("Close This");
        file.add(closeThis);

        // Quit
        quit = new JMenuItem("Quit");
        quit.addActionListener((e)->{
            dispose();
        });
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
        toolbar.add(new JLabel("Tools:"));

        // Za svako ime alata dodajemo button
        for(String toolName : toolNames){
            JButton newBtn = new JButton(toolName);
            toolsGroup.add(newBtn);
            toolbar.add(newBtn);

            // Dodavanje eventa
            newBtn.addActionListener((e) -> {
                WorkPanel.selectedTool = toolsList.get(toolName);
                currentToolName = toolName;
                updateStatus();
            });
        }

        // Dodavanje dodavanje debljine linije
        toolbar.add(new JLabel("Line thick:"));
        toolbar.add(lineThick);

        toolbar.add(lineColor);


        add(toolbar, BorderLayout.NORTH);
    }

    private void makeStatus(){
        statusbar.setBackground(Color.LIGHT_GRAY);

        // Postavljanje labela
        leftLabel = new JLabel(toolNames[0] + " selected", JLabel.LEFT);
        updateStatus();
        rightLabel = new JLabel("", JLabel.RIGHT);
        statusbar.add(leftLabel);
        statusbar.add(rightLabel);

        add(statusbar, BorderLayout.SOUTH);
    }

    private void makeCanvas(){
        Crtez novi = new Crtez();

        Duz matija = new Duz(new Point(10,10), new Point(200,200), 3, Color.BLUE);
        novi.addFigure(matija);

        matija = new Duz(new Point(200,10), new Point(10, 200), 4, Color.RED);
        novi.addFigure(matija);

        workPanel.openNewDrawing(novi);

        add(workPanel, BorderLayout.CENTER);
    }

    public static void updateStatus(){
        Color currColor = lineColor.getSelectedColor();
        leftLabel.setText(currentToolName + " selected, color: rgb(" + currColor.getRed() + ", " + currColor.getGreen() + ", " + currColor.getBlue() + "), thickness: " +  lineThick.getSelectedItem());
    }

}
