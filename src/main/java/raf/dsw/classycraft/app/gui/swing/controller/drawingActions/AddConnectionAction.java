package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddConnectionAction extends AbstractClassCrafTAction {

    private String imePolja;
    private String kardinalnost;
    private String vidljivostPolja;

    public AddConnectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/connection.png"));
        putValue(NAME,"Add connection");
        putValue(SHORT_DESCRIPTION,"Add connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object[] obj={"agregacija","kompozicija","zavisnost","generalizacija"};
        int s = JOptionPane.showOptionDialog(null, "Izaberi", "Izaberi!",
                0, 3, null, obj, obj[0]);

        if(s==3)
            MainFrame.getInstance().getPackageView().startConnectionState("generalizacija",null,null, null);
        else if(s==2){
            Object[] obj2={"use","instantiate","call"};
            int s2 = JOptionPane.showOptionDialog(null, "Izaberi", "Izaberi!",
                    0, 3, null, obj2, obj2[0]);
            if(s2==2){
                MainFrame.getInstance().getPackageView().startConnectionState("zavisnost","call",null, null);
            }
            else if(s2==1) {
                MainFrame.getInstance().getPackageView().startConnectionState("zavisnost","instantiate",null, null);
            }
            else if(s2==0){
                MainFrame.getInstance().getPackageView().startConnectionState("zavisnost","use",null, null);
            }

        }else if(s==1){
            JFrame f = new JFrame("kompozicija");

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            f.setSize(350, 200);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Kompozicija");
            JPanel jPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            Color clr = new Color(100, 100, 150);
            f.getContentPane().setBackground(clr);

            JLabel jLabelIme = new JLabel("Naziv polja: ");
            JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
            JLabel jLabelKardinalnost = new JLabel("Kardinalnost: ");
            JTextField ime = new JTextField();
            String[] v = new String[]{"public", "private", "protected"};
            JComboBox<String> vidljivost = new JComboBox<>(v);
            JRadioButton yes = new JRadioButton();
            JRadioButton no = new JRadioButton();
            ButtonGroup bg = new ButtonGroup();
            yes.setText("0..1");
            no.setText("0..*");
            bg.add(yes);
            bg.add(no);
            Box box = new Box(BoxLayout.X_AXIS);
            box.add(yes);
            box.add(no);

            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imePolja = ime.getText();
                    if(imePolja.isEmpty())
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                    else {
                        vidljivostPolja = (String) vidljivost.getSelectedItem();
                        if (yes.isSelected()) {
                            kardinalnost = "0..1";
                        } else {
                            kardinalnost = "0..*";
                        }
                        MainFrame.getInstance().getPackageView().startConnectionState("kompozicija",imePolja,kardinalnost,vidljivostPolja);
                        f.dispose();
                    }
                }
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                }
            });
            jPanel.add(jLabelIme);
            jPanel.add(ime);
            jPanel.add(jLabelVidljivost);
            jPanel.add(vidljivost);
            jPanel.add(jLabelKardinalnost);
            jPanel.add(box);
            jPanel.add(ok);
            jPanel.add(cancel);
            jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            f.add(jPanel);
            f.setVisible(true);

        }else if(s==0){
            JFrame f = new JFrame("agregacija");

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            f.setSize(350, 200);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Agregacija");
            JPanel jPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            Color clr = new Color(100, 100, 150);  //boja za pozadinu
            f.getContentPane().setBackground(clr);

            JLabel jLabelIme = new JLabel("Naziv polja: ");
            JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
            JLabel jLabelKardinalnost = new JLabel("Kardinalnost: ");
            JTextField ime = new JTextField();

            String[] v = new String[]{"public", "private", "protected"};
            JComboBox<String> vidljivost = new JComboBox<>(v);
            JRadioButton yes = new JRadioButton();
            JRadioButton no = new JRadioButton();
            ButtonGroup bg = new ButtonGroup();
            yes.setText("0..1");
            no.setText("0..*");
            bg.add(yes);
            bg.add(no);
            Box box = new Box(BoxLayout.X_AXIS);
            box.add(yes);
            box.add(no);

            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imePolja = ime.getText();
                    if(imePolja.isEmpty())
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                    else {
                        vidljivostPolja = (String) vidljivost.getSelectedItem();
                        if (yes.isSelected()) {
                            kardinalnost = "0..1";
                        } else {
                            kardinalnost = "0..*";
                        }
                        MainFrame.getInstance().getPackageView().startConnectionState("agregacija",imePolja,kardinalnost, vidljivostPolja);
                        f.dispose();
                    }
                }
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                }
            });
            jPanel.add(jLabelIme);
            jPanel.add(ime);
            jPanel.add(jLabelVidljivost);
            jPanel.add(vidljivost);
            jPanel.add(jLabelKardinalnost);
            jPanel.add(box);
            jPanel.add(ok);
            jPanel.add(cancel);
            jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            f.add(jPanel);
            f.setVisible(true);

        }

    }
}
