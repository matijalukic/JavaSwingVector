package grafika;

import javax.swing.*;

/**
 * Created by Matija on 10 Jun 17.
 */
public class PopupDialog extends JDialog {

    public PopupDialog(JFrame parent, String naslov, String tekst){
        super(parent, naslov);
        setBounds(30, 30, 300, 100);

        add(new JLabel(tekst, JLabel.CENTER));
    }
}
