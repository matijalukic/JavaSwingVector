package Grafika;

import Grafika.Alati.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matija on 12 Jun 17.
 */
public class WorkPanel extends JPanel {
    public static Crtez drawing = new Crtez(); // Crtez
    public static Alat selectedTool = new Pomeranje();


    public WorkPanel(){

        // Dodavanje eventova
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                selectedTool.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                //selectedTool.mouseReleased(e);
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                RadniProzor.rightLabel.setText("x:" + e.getX() + ", y:" + e.getY());

            }
            @Override
            public void mouseDragged(MouseEvent e){
                super.mouseDragged(e);
                selectedTool.mouseDrag(e);
                repaint();
            }
        });
    }
    public WorkPanel(Crtez crtez){
        drawing = crtez;
    }

    // Novi crtez
    public void openNewDrawing(Crtez newDrawing){
        drawing = newDrawing;
    }

    // Promena alata
    public void changeTool(Alat noviAlat){
        selectedTool = noviAlat;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(), getHeight()); // clear canvas

        if(drawing != null)
            drawing.paintAll(g);
    }
}
