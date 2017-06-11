package Grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matija on 12 Jun 17.
 */
public class WorkPanel extends JPanel {
    public Crtez drawing;

    private Point start, end;

    public WorkPanel(){

        // Dodavanje eventova
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // Pravimo pocetnu tacku
                start = new Point(e.getX(), e.getY());
            }
            @Override
            public void mouseReleased(MouseEvent e){
                super.mouseReleased(e);
                end = new Point(e.getX(), e.getY());

                drawing.addFigure(new Duz(start, end, 3, Color.BLUE));
                repaint();
            }
        });
    }
    public WorkPanel(Crtez crtez){
        drawing = crtez;
    }

    public void openNewDrawing(Crtez newDrawing){
        drawing = newDrawing;
    }

    @Override
    public void paint(Graphics g){
        if(drawing != null)
            drawing.paintAll(g);
    }
}
