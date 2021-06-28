import javax.swing.*;

public class Menu extends JFrame {
    private JTextField textField1;
    private JCheckBox womanCheckBox;
    private JButton button1;
    private JCheckBox manCheckBox;
    private JButton button2;
    private JEditorPane oyunuYapanKisininIsmiEditorPane;
    private JPanel panel = new JPanel();

    public Menu(){
        panel.add(textField1);
        panel.add(womanCheckBox);
        panel.add(button1);
        panel.add(manCheckBox);
        panel.add(button2);
        panel.add(oyunuYapanKisininIsmiEditorPane);


        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
