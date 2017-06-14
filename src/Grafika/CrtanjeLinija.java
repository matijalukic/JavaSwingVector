package Grafika;

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
        WorkPanel.drawing.addFigure(new Duz(start,end, (int)RadniProzor.lineThick.getSelectedItem(), Color.BLUE));
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }
}
