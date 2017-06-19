package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Matija on 15 Jun 17.
 */
public class Pomeranje extends Alat {
    private Point fromPos;
    private Figura figuraToMove;


    // belezenje pomeraja
    public static class Pomeraj{
        Figura movedFigure;
        ArrayList<Point> movedPoints;
        Pomeraj(Figura moved, ArrayList<Point> tacke){
            movedFigure = moved;
            movedPoints = tacke;
        }
    }

    private Stack<Pomeraj> pomeraji = new Stack<>();
    private Stack<Pomeraj> undoPomeraji = new Stack<>();

    private ArrayList<Point> currentPoints;

    private void savePoints(){ // Pamtimo u curr point kloniranu
        if(figuraToMove != null){
            currentPoints = new ArrayList<>();

            for(Point currPoint : figuraToMove.getPoints()){
                currentPoints.add((Point)currPoint.clone());
            }
        }
    }

    public Pomeranje(){

    }

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e) {
        fromPos = e.getPoint();
        Figura newFigure = WorkPanel.drawing.selectFigure(fromPos);


        if (figuraToMove != null && newFigure != null) { // Ako postoje i stara i nova selektovana gasimo selektovanost za staru
            figuraToMove.setSelected(false); // Ponistavamo selektovani flag
        }

        if (newFigure != null) { // Ako postoji nova figura selektujemo nju
            figuraToMove = newFigure;
            figuraToMove.setSelected(true); // selektovan je novi
        }


        if (figuraToMove != null) { // Ako postoji selektovana figura hvatamo njen catch
            // Pamtimo figuru i stare pozicije na stek
            savePoints();
            pomeraji.push(new Pomeraj(figuraToMove, currentPoints));

            figuraToMove.setNewCatch(fromPos);


        }

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

    @Override
    public void undo() {

        // Undo skida sa steka i primenjuje stare pozicije na novu
        if(!pomeraji.empty()){


            Pomeraj pomeraj = pomeraji.pop();

            // patimo pre undo-a pozicije
            currentPoints = pomeraj.movedFigure.getPoints(); // trenutne pamtimo u stare
            undoPomeraji.push(new Pomeraj(pomeraj.movedFigure, currentPoints));

            // premestamo na stare pozicije
            pomeraj.movedFigure.setPoints(pomeraj.movedPoints);
        }

    }

    @Override
    public void redo() {

        if(!undoPomeraji.empty()){

            Pomeraj undoPomeraj = undoPomeraji.pop();

            currentPoints = undoPomeraj.movedFigure.getPoints();
            pomeraji.push(new Pomeraj(undoPomeraj.movedFigure, currentPoints));

            // premestamo na stare pozicije
            undoPomeraj.movedFigure.setPoints(undoPomeraj.movedPoints);
        }

    }
}
