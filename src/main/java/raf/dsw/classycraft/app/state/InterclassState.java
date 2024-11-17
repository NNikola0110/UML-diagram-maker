package raf.dsw.classycraft.app.state;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.interclass.Enumm;
import raf.dsw.classycraft.app.model.implementation.interclass.Interfejs;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.*;
import raf.dsw.classycraft.app.painters.interclass.EnumPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.InterfejsPainter;
import raf.dsw.classycraft.app.painters.interclass.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static javafx.scene.input.KeyCode.ENTER;

@Getter
@Setter
public class InterclassState implements State {

    private String vrstaElementa;

    private String imeKlase;
    private String vidljivostKlase;

    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        if(vrstaElementa.equals("klasa")) {
            if(!provera(p,dv)) {
                JFrame f = new JFrame("klasa");

                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();
                f.setSize(350, 200);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Klasa");
                JPanel jPanel = new JPanel(new GridLayout(4, 4, 10, 10));
                Color clr = new Color(100, 100, 150);
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv klase: ");
                JLabel jLabelAbstract = new JLabel("Abstraktna: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JTextField ime = new JTextField();
                JRadioButton yes = new JRadioButton();
                JRadioButton no = new JRadioButton();
                ButtonGroup bg = new ButtonGroup();
                yes.setText("YES");
                no.setText("NO");
                bg.add(yes);
                bg.add(no);
                Box box = new Box(BoxLayout.X_AXIS);
                box.add(yes);
                box.add(no);
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JButton ok = new JButton("Ok");

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        imeKlase = ime.getText();
                        vidljivostKlase = (String) vidljivost.getSelectedItem();
                        if (imeKlase.isEmpty())
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                        else {
                            Klasa klasa = new Klasa(imeKlase, dv.getDiagram());
                            klasa.setVidljivost(vidljivostKlase);
                            klasa.setApstraktna(yes.isSelected());
                            //System.out.println(klasa.isApstraktna());
                            klasa.setLocation(p);
                            klasa.setColor(new Color(43, 42, 76));
                            klasa.setSize(new Dimension(200, 100));
                            KlasaPainter klasaPainter = new KlasaPainter(klasa);

                            dv.getPainters().add(klasaPainter);
                            MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(), klasa);
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
                jPanel.add(jLabelAbstract);
                jPanel.add(box);
                jPanel.add(ok);
                jPanel.add(cancel);
                jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                f.add(jPanel);
                f.setVisible(true);
            }
        }

        else if(vrstaElementa.equals("interfejs")){
            if(!provera(p,dv)) {
                JFrame f = new JFrame("interfejs");

                Toolkit kit = Toolkit.getDefaultToolkit();

                Dimension screenSize = kit.getScreenSize();
                f.setSize(350, 150);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Interfejs");
                JPanel jPanel = new JPanel(new GridLayout(3, 2, 10, 10));
                Color clr = new Color(100, 100, 150);
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv interfejsa: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JTextField ime = new JTextField();
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JButton ok = new JButton("Ok");
                //ok.getInputMap().put(KeyStroke.getKeyStroke("ENTER"));
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        imeKlase = ime.getText();
                        vidljivostKlase = (String) vidljivost.getSelectedItem();
                        if (imeKlase.isEmpty())
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                        else {
                            Interfejs interfejs = new Interfejs(imeKlase, dv.getDiagram());
                            interfejs.setVidljivost(vidljivostKlase);
                            interfejs.setLocation(p);
                            interfejs.setColor(new Color(97, 75, 195));
                            interfejs.setSize(new Dimension(100, 100));
                            InterfejsPainter interfejsPainter = new InterfejsPainter(interfejs);
                            dv.getPainters().add(interfejsPainter);
                            MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),interfejs);

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
                jPanel.add(ok);
                jPanel.add(cancel);
                jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                f.add(jPanel);
                f.setVisible(true);
            }
            /*if(!provera(p,dv)){
                String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Ime interfejsa:\n", "Interfejs", JOptionPane.QUESTION_MESSAGE);
                if (name == null || name.isEmpty()) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);
                }else {
                    Interfejs interfejs = new Interfejs(name, dv.getDiagram());

                    interfejs.setLocation(p);
                    interfejs.setColor(new Color(97, 75, 195));
                    interfejs.setSize(new Dimension(100, 100));
                    InterfejsPainter interfejsPainter = new InterfejsPainter(interfejs);

                    dv.getPainters().add(interfejsPainter);
                    MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),interfejs);
                    //dv.repaint();
                }
            }*/

        }

        else if(vrstaElementa.equals("enum")){
            if(!provera(p,dv)) {
                JFrame f = new JFrame("enum");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();
                f.setSize(350, 150);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Enum");
                JPanel jPanel = new JPanel(new GridLayout(3, 2, 10, 10));
                Color clr = new Color(100, 100, 150);
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv enum: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JTextField ime = new JTextField();
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JButton ok = new JButton("Ok");
                //ok.getInputMap().put(KeyStroke.getKeyStroke("ENTER"));
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        imeKlase = ime.getText();
                        vidljivostKlase = (String) vidljivost.getSelectedItem();
                        if (imeKlase.isEmpty())
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                        else {
                            Enumm enumm = new Enumm(imeKlase,dv.getDiagram());
                            enumm.setVidljivost(vidljivostKlase);
                            enumm.setLocation(p);
                            enumm.setColor(new Color(60, 121, 245));
                            enumm.setSize(new Dimension(100,100));
                            EnumPainter enumPainter = new EnumPainter(enumm);
                            dv.getPainters().add(enumPainter);
                            MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),enumm);

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
                jPanel.add(ok);
                jPanel.add(cancel);
                jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                f.add(jPanel);
                f.setVisible(true);
            }
            /*if(!provera(p,dv)){
                String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Ime enuma:\n", "Enum", JOptionPane.QUESTION_MESSAGE);
                if (name == null || name.isEmpty()) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);
                }else {
                    Enumm enumm = new Enumm(name,dv.getDiagram());
                    enumm.setLocation(p);
                    enumm.setColor(new Color(60, 121, 245));
                    enumm.setSize(new Dimension(100,100));
                    EnumPainter enumPainter = new EnumPainter(enumm);

                    dv.getPainters().add(enumPainter);
                    MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),enumm);

                    //dv.repaint();
                }
            }*/
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
    public boolean provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p)) {
                    System.out.println("Element at.");
                    return true;
                }else if(ep instanceof InterclassPainter){
                    InterclassPainter i = (InterclassPainter) ep;
                    Rectangle2D rect = ep instanceof KlasaPainter ? new Rectangle2D.Double(p.x,p.y,200,100):new Rectangle2D.Double(p.x,p.y,100,100);
                    if(i.getRectangle().intersects(rect)){
                        System.out.println("Element AT");
                        return true;
                    }
                }
            }
            return false;
        }else{
            return false;
        }
    }

}
