package Grafika;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Matija on 12 Jun 17.
 */
public class WorkPanel extends JPanel {
    private Crtez drawing;

    public WorkPanel(){

    }
    public WorkPanel(Crtez crtez){
        drawing = crtez;
    }

    public void openNewDrawing(Crtez newDrawing){
        drawing = newDrawing;
    }

    @Override
    public void paint(Graphics g){
        if(drawing != null)
            drawing.paintAll(g);
    }
}
