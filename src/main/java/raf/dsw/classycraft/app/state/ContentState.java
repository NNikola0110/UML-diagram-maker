package raf.dsw.classycraft.app.state;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.model.implementation.interclass.Enumm;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.content.MethodPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

@Getter
@Setter
public class ContentState implements State {

    private String vrstaElementa;
    //private Interclass interclassParent;
    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        Interclass provera = provera(p,dv);

        if(vrstaElementa.equals("atribut")){
            if(provera !=null && provera instanceof Klasa) {
                JFrame f = new JFrame();
                f.setSize(400, 200);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Atribut");
                JPanel jPanel = new JPanel(new GridLayout(4, 4, 10, 10));
                Color clr = new Color(100, 100, 150);  //boja za pozadinu
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv atributa: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JLabel jLabelTip = new JLabel("Tip: ");
                JTextField ime = new JTextField();
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JTextField tip = new JTextField();
                JButton ok = new JButton("Ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        /*String v = new String();
                        if (vidljivost.getSelectedItem().equals("public")) v = "+";
                        else if (vidljivost.getSelectedItem().equals("private")) v = "-";
                        else if (vidljivost.getSelectedItem().equals("protected")) v = "#";*/

                        Atribute atribut = new Atribute(ime.getText(),(String)vidljivost.getSelectedItem(), tip.getText());
                        atribut.setPomeraj(50 + provera.getContent().size() * 20);
                        provera.addAtribut(atribut);

                        f.dispose();

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
                jPanel.add(jLabelTip);
                jPanel.add(tip);
                jPanel.add(ok);
                jPanel.add(cancel);
                jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                f.add(jPanel);
                f.setVisible(true);
            }else{
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            }
        }
        else if(vrstaElementa.equals("metoda")){
            if(provera !=null && !(provera instanceof Enumm)) {
                JFrame f = new JFrame();
                f.setSize(400, 200);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Metoda");
                JPanel jPanel = new JPanel(new GridLayout(4, 4, 10, 10));
                Color clr = new Color(100, 100, 150);  //boja za pozadinu
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv metode: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JLabel jLabelTip = new JLabel("Tip: ");
                JTextField ime = new JTextField();
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JTextField tip = new JTextField();
                JButton ok = new JButton("Ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        /*String v = new String();
                        if (vidljivost.getSelectedItem().equals("public")) v = "+";
                        else if (vidljivost.getSelectedItem().equals("private")) v = "-";
                        else if (vidljivost.getSelectedItem().equals("protected")) v = "#";*/

                        Method metoda = new Method(ime.getText(), (String)vidljivost.getSelectedItem(), tip.getText());
                        metoda.setPomeraj(50 + provera.getContent().size() * 20);
                        provera.addMetod(metoda);

                        f.dispose();
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
                jPanel.add(jLabelTip);
                jPanel.add(tip);
                jPanel.add(ok);
                jPanel.add(cancel);
                jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                f.add(jPanel);
                f.setVisible(true);
            }else{
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            }
        }
        else if(vrstaElementa.equals("enum")){
            if(provera !=null && provera instanceof Enumm) {
                String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Ime enum elementa:\n", "EnumElement", JOptionPane.QUESTION_MESSAGE);
                if (name == null || name.isEmpty()) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);
                }else{
                    EnumElement enumElement = new EnumElement(name);
                    provera.addEnumElement(enumElement);
                }
            }else{
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            }
        }
    }

    @Override
    public void misPritisnut(Point p, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point p, DiagramView dv) {

    }

    @Override
    public void misPrevucen(Point p, DiagramView dv) {

    }

    public Interclass provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p) && ep instanceof InterclassPainter) {
                    System.out.println("Moze da se doda.");
                    return ((InterclassPainter) ep).getInterclass();
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
