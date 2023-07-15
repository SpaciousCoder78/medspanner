//importing modules
import javax.swing.*;
import java.awt.*;
public class info {
    public info(){
        JFrame z = new JFrame("Info");
        JLabel l2= new JLabel("medspanner v1.1.1 \n\n");
        JLabel l3= new JLabel("Created by SpaciousCoder78 @ GitHub \n\n");
        JLabel l4 = new JLabel("medspanner is a new way to manage your medicines and \n track your observations");
        JLabel l5 = new JLabel("JDK Version: 17 LTS \n\n");
        z.setLayout(new BoxLayout(z.getContentPane(), BoxLayout.Y_AXIS));
        z.add(Box.createVerticalGlue());
        z.add(l2);
        z.add(Box.createVerticalStrut(10));
        z.add(l3);
        z.add(Box.createVerticalStrut(10));
        z.add(l4);
        z.add(Box.createVerticalStrut(10));
        z.add(l5);
        z.add(Box.createVerticalGlue());
        z.setVisible(true);
        z.setSize(400,400);

    }
}
