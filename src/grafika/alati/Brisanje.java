package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Brisanje extends Alat {

    private Stack<Figura> deletedFigures = new Stack<>();
    private Stack<Figura> undeletedFigures = new Stack<>();
    private Figura toDelete;
    public Brisanje(){ super();}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        Point currPoint = e.getPoint();

        toDelete = WorkPanel.drawing.selectFigure(currPoint);
        if(toDelete != null){
            deletedFigures.push(toDelete);
            WorkPanel.drawing.deleteFigura(toDelete);
        }
    }

    @Override
    public void mouseDrag(MouseEvent e) {}

    @Override
    public void undo() {
        if(!deletedFigures.empty()){
            Figura popFigura = deletedFigures.pop();
            undeletedFigures.push(popFigura);
            WorkPanel.drawing.addFigure(popFigura);
        }
    }

    @Override
    public void redo() {
        if(!undeletedFigures.empty()) {
            Figura deleteAgain = undeletedFigures.pop();
            deletedFigures.push(deleteAgain);
            WorkPanel.drawing.deleteFigura(deleteAgain);
        }
    }


}
