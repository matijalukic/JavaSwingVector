package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Brisanje extends Alat {

    public Brisanje(){ super();}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        Point currPoint = e.getPoint();

        Figura toDelete = WorkPanel.drawing.selectFigure(currPoint);
        WorkPanel.drawing.deleteFigura(toDelete);
    }

    @Override
    public void mouseDrag(MouseEvent e) {}
}