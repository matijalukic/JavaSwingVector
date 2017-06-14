package Grafika;

import java.awt.event.*;

/**
 * Created by Matija on 14 Jun 17.
 */
public abstract class Alat {
    public static Alat singleTool; // single tool

    // Konstruktor za singleton
    public Alat(){
        // Podesavamo
        singleTool = this;
    }

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
}
