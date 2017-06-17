package grafika.alati;

import grafika.RadniProzor;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matija on 14 Jun 17.
 */
public abstract class Alat {
    // Konstruktor za singleton
    public Alat(){}

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mouseDrag(MouseEvent e);


    public void mouseMove(MouseEvent e){ // Kada se pomeri mis
        Point currPoint = e.getPoint();
        RadniProzor.rightLabel.setText("X:" + currPoint.getX() + " Y:" + currPoint.getY());
    }
}
