package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Matija on 15 Jun 17.
 */
public class Pomeranje extends Alat {
    private Point fromPos;
    private Figura figuraToMove;

    public Pomeranje(){

    }

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){
        fromPos = e.getPoint();
        figuraToMove = WorkPanel.drawing.selectFigure(fromPos);

        if(figuraToMove != null)
            figuraToMove.setNewCatch(fromPos);
    }

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseDrag(MouseEvent e){
        Point newPoint = e.getPoint();
        System.out.println("Firua koju pomeramo : " + figuraToMove);
        if(figuraToMove != null) {
            figuraToMove.moveNew(newPoint);
        }

    }
}
