package grafika.alati;

import grafika.*;
import grafika.elementi.Figura;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

/**
 * Created by Matija on 14 Jun 17.
 */
public abstract class Alat {

    protected Stack<Figura> deleteOnUndo = new Stack<>();

    // Konstruktor za singleton
    public Alat(){}

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mouseDrag(MouseEvent e);


    public void undo(){
        deleteOnUndo.push(WorkPanel.drawing.popLast()); // Pushujemo na stack brisanih stekom
    }
    public void redo(){
        if(!deleteOnUndo.empty()){
            WorkPanel.drawing.addFigure(deleteOnUndo.pop()); // Vracamo u crtez
        }
    }


    public void mouseMove(MouseEvent e){ // Kada se pomeri mis
        Point currPoint = e.getPoint();
        RadniProzor.rightLabel.setText("X:" + currPoint.getX() + " Y:" + currPoint.getY());
    }

}
