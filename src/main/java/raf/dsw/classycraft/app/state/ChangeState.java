package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Agregacija;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.model.implementation.connection.Kompozicija;
import raf.dsw.classycraft.app.model.implementation.connection.Zavisnost;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.model.implementation.interclass.Enumm;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.model.implementation.interclass.Interfejs;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.KlasaPainter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ChangeState implements State {

    private DefaultListModel<ClassContent> contentListModel;
    private JList<ClassContent> contentList;

    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);

        DiagramElement element = provera(p, dv);
        if (element instanceof Interclass) {
            Interclass provera = (Interclass) element;
            if(provera instanceof Klasa) {
                JFrame f = new JFrame("Edit");
                f.setSize(500, 450);
                f.setTitle("Property Editor");

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(6, 2, 7, 10));
                mainPanel.add(new JLabel("Name:"));
                JTextField nameTextField = new JTextField(provera.getName());
                mainPanel.add(nameTextField);

                mainPanel.add(new JLabel("Visibility:"));
                String[] visibilityOptions = {"public", "private", "protected"};
                JComboBox<String> visibilityComboBox = new JComboBox<>(visibilityOptions);
                if (provera.getVidljivost().equals("public"))
                    visibilityComboBox.setSelectedItem("public");
                else if (provera.getVidljivost().equals("private"))
                    visibilityComboBox.setSelectedItem("private");
                else if (provera.getVidljivost().equals("protected"))
                    visibilityComboBox.setSelectedItem("protected");

                mainPanel.add(visibilityComboBox);
                mainPanel.add(new JLabel("Abstract:"));
                JRadioButton abstractYes = new JRadioButton("Yes");
                JRadioButton abstractNo = new JRadioButton("No");
                ButtonGroup abstractGroup = new ButtonGroup();
                abstractGroup.add(abstractYes);
                abstractGroup.add(abstractNo);
                if (provera.isApstraktna())
                    abstractYes.setSelected(true);
                else
                    abstractNo.setSelected(true);
                JPanel abstractPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                abstractPanel.add(abstractYes);
                abstractPanel.add(abstractNo);
                mainPanel.add(abstractPanel);

                mainPanel.add(new JLabel("Content:"));
                mainPanel.add(new JLabel("(selektujte u listi sta zelite da izmenite)"));

                contentListModel = new DefaultListModel<>();
                for (ClassContent c : provera.getContent()) {
                    contentListModel.addElement(c);
                }
                contentList = new JList<>(contentListModel);
                JScrollPane contentScrollPane = new JScrollPane(contentList);
                mainPanel.add(contentScrollPane);

                JButton edit = new JButton("Edit");
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClassContent izabrano = contentList.getSelectedValue();
                        editContent(provera, izabrano, contentList);
                        dv.getDiagram().notifySubscribers("paint");
                    }
                });
                mainPanel.add(edit);

                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Cancel");
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);

                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //contentList.getModel();
                        provera.setName(nameTextField.getText());
                        provera.setVidljivost((String) visibilityComboBox.getSelectedItem());
                        provera.setApstraktna(abstractYes.isSelected());
                        dv.repaint();
                        //provera.setContent();

                        f.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });

                f.setLayout(new BorderLayout());

                f.add(mainPanel, BorderLayout.CENTER);
                f.add(buttonPanel, BorderLayout.SOUTH);

                //f.pack();
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);

            }else if(provera instanceof Interfejs || provera instanceof Enumm){
                JFrame f = new JFrame("Edit");
                f.setSize(300, 250);
                f.setTitle("Property Editor");

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(4, 2, 7, 10));
                mainPanel.add(new JLabel("Name:"));
                JTextField nameTextField = new JTextField(provera.getName());
                mainPanel.add(nameTextField);

                mainPanel.add(new JLabel("Visibility:"));
                String[] visibilityOptions = {"public", "private", "protected"};
                JComboBox<String> visibilityComboBox = new JComboBox<>(visibilityOptions);
                if(provera.getVidljivost().equals("public"))
                    visibilityComboBox.setSelectedItem("public");
                else if(provera.getVidljivost().equals("private"))
                    visibilityComboBox.setSelectedItem("private");
                else if(provera.getVidljivost().equals("protected"))
                    visibilityComboBox.setSelectedItem("protected");
                mainPanel.add(visibilityComboBox);

                mainPanel.add(new JLabel("Content:"));
                mainPanel.add(new JLabel("(selektujte u listi sta zelite da izmenite)"));

                contentListModel = new DefaultListModel<>();
                for (ClassContent c : provera.getContent()) {
                    contentListModel.addElement(c);
                }
                contentList = new JList<>(contentListModel);
                JScrollPane contentScrollPane = new JScrollPane(contentList);
                mainPanel.add(contentScrollPane);

                JButton edit = new JButton("Edit");
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClassContent izabrano = contentList.getSelectedValue();
                        editContent(provera, izabrano,contentList);
                        dv.repaint();
                    }
                });
                mainPanel.add(edit);

                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Cancel");
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);

                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //contentList.getModel();
                        provera.setName(nameTextField.getText());
                        provera.setVidljivost((String)visibilityComboBox.getSelectedItem());
                        dv.repaint();
                        //provera.setContent();

                        f.dispose();
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });

                f.setLayout(new BorderLayout());
                f.add(mainPanel, BorderLayout.CENTER);
                f.add(buttonPanel, BorderLayout.SOUTH);
                //f.pack();
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);
            }
        }
        else if(element instanceof Connection){
            Connection provera = (Connection)element;
            if(provera instanceof Agregacija){

                JFrame f = new JFrame("agregacija");

                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();
                f.setSize(350, 200);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setTitle("Agregacija");
                JPanel jPanel = new JPanel(new GridLayout(4, 2, 10, 10));
                Color clr = new Color(100, 100, 150);
                f.getContentPane().setBackground(clr);

                JLabel jLabelIme = new JLabel("Naziv polja: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JLabel jLabelKardinalnost = new JLabel("Kardinalnost: ");
                JTextField ime = new JTextField(((Agregacija) provera).getImePolja());
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                vidljivost.setSelectedItem(((Agregacija) provera).getVidljivost());
                JRadioButton button1 = new JRadioButton();
                JRadioButton button = new JRadioButton();
                if (((Agregacija) provera).getKardinalnost().equals("0..1"))
                    button1.setSelected(true);
                else
                    button.setSelected(true);
                ButtonGroup bg = new ButtonGroup();
                button1.setText("0..1");
                button.setText("0..*");
                bg.add(button1);
                bg.add(button);
                Box box = new Box(BoxLayout.X_AXIS);
                box.add(button1);
                box.add(button);

                JButton ok = new JButton("Ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(ime.getText().isEmpty())
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                        else {
                            ((Agregacija) provera).setImePolja(ime.getText());
                            ((Agregacija) provera).setVidljivost((String) vidljivost.getSelectedItem());
                            if (button1.isSelected()) {
                                ((Agregacija) provera).setKardinalnost("0..1");
                            } else {
                                ((Agregacija) provera).setKardinalnost("0..*");
                            }
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

            }else if(provera instanceof Kompozicija){

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
                JLabel jLabelKardinalnost = new JLabel("Kardinalnost: ");
                JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
                JTextField ime = new JTextField(((Kompozicija) provera).getImePolja());
                String[] v = new String[]{"public", "private", "protected"};
                JComboBox<String> vidljivost = new JComboBox<>(v);
                JRadioButton button1 = new JRadioButton();
                JRadioButton button = new JRadioButton();
                if (((Kompozicija) provera).getKardinalnost().equals("0..1"))
                    button1.setSelected(true);
                else
                    button.setSelected(true);
                ButtonGroup bg = new ButtonGroup();
                button1.setText("0..1");
                button.setText("0..*");
                bg.add(button1);
                bg.add(button);
                Box box = new Box(BoxLayout.X_AXIS);
                box.add(button1);
                box.add(button);

                JButton ok = new JButton("Ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(ime.getText().isEmpty())
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);

                        else {
                            ((Kompozicija) provera).setImePolja(ime.getText());
                            ((Kompozicija) provera).setVidljivost((String) vidljivost.getSelectedItem());
                            if (button1.isSelected()) {
                                ((Kompozicija) provera).setKardinalnost("0..1");
                            } else {
                                ((Kompozicija) provera).setKardinalnost("0..*");
                            }
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
            }else if(provera instanceof Zavisnost){
                Object[] obj2={"use","instantiate","call"};
                int s2 = JOptionPane.showOptionDialog(null, "Izaberite u koji tip zelite da promenite: ", "Zavisnost",
                        0, 3, null, obj2, obj2[0]);
                if(s2==0) ((Zavisnost) provera).setTip("use");
                else if(s2==1) ((Zavisnost) provera).setTip("instantiate");
                else if(s2==2)((Zavisnost) provera).setTip("call");
                //System.out.println("proveraaaaa = " + provera.toString());
            }
        }
    }

    public void editContent(Interclass provera, ClassContent izabrano,JList<ClassContent> contentList) {

        JFrame f = new JFrame();
        f.setSize(600, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //ClassContent izabran = vratiContent(provera,izabrano);
        if(izabrano instanceof Atribute) {
            f.setTitle("Atribut");
            JPanel jPanel = new JPanel(new GridLayout(4, 4, 10, 10));
            Color clr = new Color(100, 100, 150);
            f.getContentPane().setBackground(clr);

            JLabel jLabelIme = new JLabel("Naziv atributa: ");
            JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
            JLabel jLabelTip = new JLabel("Tip: ");
            JTextField ime = new JTextField(izabrano.getName());
            String[] v = new String[]{"public", "private", "protected"};
            JComboBox<String> vidljivost = new JComboBox<>(v);
            vidljivost.setSelectedItem(((Atribute) izabrano).getVidljivost());
            JTextField tip = new JTextField(((Atribute) izabrano).getTip());
            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(ClassContent c:provera.getContent()){
                        if(c.equals(izabrano)){
                            c.setName(ime.getText());
                            ((Atribute) c).setTip(tip.getText());
                            ((Atribute) c).setVidljivost(vidljivost.getSelectedItem().toString());
                        }
                    }
                    //System.out.println("ISPIS : " + izabrano.toString());
                    DefaultListModel model = new DefaultListModel();
                    for (ClassContent c : provera.getContent()) {
                        model.addElement(c);
                    }
                    contentList.setModel(model);
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

        }else if(izabrano instanceof Method){

            f.setTitle("Metoda");
            JPanel jPanel = new JPanel(new GridLayout(4, 4, 10, 10));
            Color clr = new Color(100, 100, 150);
            f.getContentPane().setBackground(clr);

            JLabel jLabelIme = new JLabel("Naziv metode: ");
            JLabel jLabelVidljivost = new JLabel("Vidljivost: ");
            JLabel jLabelTip = new JLabel("Tip: ");
            JTextField ime = new JTextField(izabrano.getName());
            String[] v = new String[]{"public", "private", "protected"};
            JComboBox<String> vidljivost = new JComboBox<>(v);
            vidljivost.setSelectedItem(((Method) izabrano).getVidljivost());
            JTextField tip = new JTextField(((Method) izabrano).getTip());
            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    izabrano.setName(ime.getText());
                    ((Method) izabrano).setTip(tip.getText());
                    ((Method) izabrano).setVidljivost(vidljivost.getSelectedItem().toString());
                   
                    DefaultListModel model = new DefaultListModel();
                    for (ClassContent c : provera.getContent()) {
                        model.addElement(c);
                    }
                    contentList.setModel(model);
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

        }else if(izabrano instanceof EnumElement){
            String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Ime enum elementa:\n", izabrano.getName());
            if (name == null || name.isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);
            }else{
                izabrano.setName(name);
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

    public DiagramElement provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p) && ep instanceof InterclassPainter) {
                    return ((InterclassPainter) ep).getInterclass();
                }else if(ep.elementAt(p) && ep instanceof ConnectionPainter){
                    return ((ConnectionPainter) ep).getConnection();
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
