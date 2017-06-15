package Grafika.Alati;

import Grafika.*;
import Grafika.Figure.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matija on 14 Jun 17.
 */
public class CrtanjeLinija extends Alat {

    private Point start, end;

    public CrtanjeLinija(){
        super();
    }

    @Override
    public void mousePressed(MouseEvent e){
        start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        end = e.getPoint();
        WorkPanel.drawing.addFigure(new Duz(start,end, (int) RadniProzor.lineThick.getSelectedItem(), RadniProzor.lineColor.getSelectedColor()));
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mouseDrag(MouseEvent e){

    }
}
