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
    public void mousePressed(MouseEvent e) {
        fromPos = e.getPoint();
        Figura newFigure = WorkPanel.drawing.selectFigure(fromPos);


        if(figuraToMove != null && newFigure != null) { // Ako postoje i stara i nova selektovana gasimo selektovanost za staru
            figuraToMove.setSelected(false); // Ponistavamo selektovani flag
        }

        if (newFigure != null) { // Ako postoji nova figura selektujemo nju
            figuraToMove = newFigure;
            figuraToMove.setSelected(true); // selektovan je novi
        }


        if(figuraToMove != null) // Ako postoji selektovana figura hvatamo njen catch
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

        RadniProzor.rightLabel.setText("X:" + newPoint.getX() + " Y:" + newPoint.getY());

    }
}
