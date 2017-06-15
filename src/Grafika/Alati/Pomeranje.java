package Grafika.Alati;

import Grafika.Figure.Figura;
import Grafika.WorkPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Matija on 15 Jun 17.
 */
public class Pomeranje extends Alat {
    private Point fromPos, toPos, oldPoint = new Point(0,0);
    private Figura figuraToMove;

    public Pomeranje(){

    }

    @Override
    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e){
        fromPos = e.getPoint();
        figuraToMove = WorkPanel.drawing.selectFigure(fromPos);

        if(figuraToMove != null)
            figuraToMove.setNewCatch(fromPos);

        System.out.println("Mouse pressed: " + figuraToMove);
    }

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseDrag(MouseEvent e){
        Point newPoint = e.getPoint();

        if(figuraToMove != null) {
            figuraToMove.moveNew(newPoint);
        }

        oldPoint = newPoint;

    }
}
