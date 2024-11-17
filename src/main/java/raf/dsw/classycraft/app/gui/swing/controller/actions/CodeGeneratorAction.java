package raf.dsw.classycraft.app.gui.swing.controller.actions;

import javafx.beans.binding.StringBinding;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.*;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.model.implementation.interclass.Enumm;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.model.implementation.interclass.Interfejs;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.ElementPainter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeGeneratorAction extends AbstractClassCrafTAction {

    public CodeGeneratorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/uml.png"));
        putValue(NAME, "Generate");
        putValue(SHORT_DESCRIPTION, "Generate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**za trenutni diagram view**/
        // int selectedDiagraView = MainFrame.getInstance().getPackageView().getJTabbedPane().getSelectedIndex();
        // DiagramView dv = MainFrame.getInstance().getPackageView().getDiagramViews().get(selectedDiagraView);

        List<DiagramView> diagramViews = MainFrame.getInstance().getPackageView().getDiagramViews();

        for (DiagramView dv : diagramViews) {


            String classyNode = dv.getDiagram().getName();
            String paketUKomSeNalazi = dv.getDiagram().getParent().getName();
            Diagram diagram = dv.getDiagram();

            List<ElementPainter> painters = dv.getPainters();


            List<DiagramElement> de = new ArrayList<>();

            List<Klasa> lisaKlasa = new ArrayList<>();
            List<Enumm> listaEnuma = new ArrayList<>();
            List<Interfejs> listaInterfejsa = new ArrayList<>();
            List<Connection> listaKonekcija = new ArrayList<>();

            int brojInterklasa = lisaKlasa.size() + listaEnuma.size() + listaInterfejsa.size();

            for (ElementPainter painter : painters) {
                de.add(painter.getDiagramElement());
                if (painter.getDiagramElement() instanceof Klasa) {
                    lisaKlasa.add((Klasa) painter.getDiagramElement());
                    System.out.println(painter.getDiagramElement().getName());
                } else if (painter.getDiagramElement() instanceof Enumm) {
                    listaEnuma.add((Enumm) painter.getDiagramElement());
                } else if (painter.getDiagramElement() instanceof Interfejs) {
                    listaInterfejsa.add((Interfejs) painter.getDiagramElement());
                } else if (painter.getDiagramElement() instanceof Connection) {
                    listaKonekcija.add((Connection) painter.getDiagramElement());
                }

            }

            for (Klasa k : lisaKlasa) {
                String klasa = k.getName();
                File file = new File(klasa + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fileWriter);

                    List<Klasa> klaseDobijeneNasledjivanjem = new ArrayList<>();
                    List<Interfejs> interfejsDobijeneNasledjivanjem = new ArrayList<>();
                    int counter = 0;
                    int counter1 = 0;

                    String vidljivost = k.getVidljivost();

                    Boolean aKlasa = k.isApstraktna();
                    String apstraktnaKlasa = "abstract";
                    if (!aKlasa) {
                        apstraktnaKlasa = "";
                    }
                    bw.write(classyNode);
                    bw.newLine();
                    bw.write("package " + paketUKomSeNalazi + "\n\n");
                    bw.write(vidljivost + " " + apstraktnaKlasa + " class " + klasa);

                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder strigGeneralizacija = new StringBuilder();
                    StringBuilder stringZavisnost = new StringBuilder();

                    for (Connection conn : listaKonekcija) {
                        /***from ili to**/if (conn.getFrom().getName().equals(klasa)) {
                            if (conn instanceof Agregacija) {

                                String vidljivosrAgregacije = ((Agregacija) conn).getVidljivost();
                                String kardinalnost = ((Agregacija) conn).getKardinalnost();
                                String imePolja = ((Agregacija) conn).getImePolja();
                                String tip = conn.getTo().getName();

                                if (kardinalnost.equals("0..1")) {
                                    stringBuilder.append("\t" + vidljivosrAgregacije + " " + tip + " " + imePolja + ";\n");
                                } else {
                                    stringBuilder.append("\t" + vidljivosrAgregacije + " List<" + tip + "> " + imePolja + ";\n");
                                }
                            } else if (conn instanceof Kompozicija) {
                                String vidljivosrKompozicije = ((Kompozicija) conn).getVidljivost();
                                String kardinalnost = ((Kompozicija) conn).getKardinalnost();
                                String imePolja = ((Kompozicija) conn).getImePolja();
                                String tip = conn.getTo().getName();

                                if (kardinalnost.equals("0..1")) {
                                    stringBuilder.append("\t" + vidljivosrKompozicije + " " + tip + " " + imePolja + ";\n");
                                } else {
                                    stringBuilder.append("\t" + vidljivosrKompozicije + " List<" + tip + "> " + imePolja + ";\n");
                                }
                            } else if (conn instanceof Generalizacija) {
                                counter++;
                                if (counter == 1) {
                                    bw.write(" extends ");
                                }
                                if (conn.getTo() instanceof Klasa) {
                                    klaseDobijeneNasledjivanjem.add((Klasa) conn.getTo());
                                }

                                String ime = conn.getTo().getName();
                                strigGeneralizacija.append(ime + ", ");

                            } else if (conn instanceof Zavisnost) {
                                /** dodati kod**/
                                counter1++;
                                if (counter1 == 1) {
                                    stringZavisnost.append(" implemens ");
                                }

                                if (conn.getTo() instanceof Interfejs) {
                                    interfejsDobijeneNasledjivanjem.add((Interfejs) conn.getTo());
                                }
                                String ime = conn.getTo().getName();
                                stringZavisnost.append(ime + ", ");
                            }


                        }
                    }
                    if (strigGeneralizacija.length() > 0) {
                        strigGeneralizacija.deleteCharAt(strigGeneralizacija.length() - 2);
                    }

                    if (stringZavisnost.length() > 0) {
                        stringZavisnost.deleteCharAt(stringZavisnost.length() - 2);
                    }
                    bw.write(String.valueOf(strigGeneralizacija));
                    bw.write(String.valueOf(stringZavisnost));
                    bw.write("{");
                    bw.newLine();
                    bw.newLine();
                    bw.write(String.valueOf(stringBuilder));

                    /*** Ovde treba da budu veze ove klase**/

                    bw.newLine();

                    List<ClassContent> sadrzajKlase = k.getContent();
                    StringBuilder metodeIzklase = new StringBuilder();
                    for (ClassContent content : sadrzajKlase) {
                        /** dodavanje atributa*/
                        if (content instanceof Atribute) {
                            String nazivAtributa = content.getName();
                            String vidljivostAtributa = ((Atribute) content).getVidljivost();
                            String tipAtributa = ((Atribute) content).getTip();
                            bw.write("\t" + vidljivostAtributa + " " + tipAtributa + " " + nazivAtributa + ";");
                            bw.newLine();
                        } else {
                            /**dodavanje metoda*/
                            String nazivMetode = content.getName();
                            String vidljivostMetode = ((Method) content).getVidljivost();
                            String povratnaVrednost = ((Method) content).getTip();

                            metodeIzklase.append("\t" + vidljivostMetode + " " + povratnaVrednost + " " + nazivMetode + "() {\n\t}\n\n");
                        }
                    }
                    List<Method> metodeDobijeneNasledjivanjem = new ArrayList<>();
                    StringBuilder nasledjeneMetodeString = new StringBuilder();

                    /** dodavanje metoda iz nadklase**/
                    for (Klasa klasa1 : klaseDobijeneNasledjivanjem) {
                        for (ClassContent ck : klasa1.getContent()) {
                            if (ck instanceof Method) {
                                if (!((Method) ck).getVidljivost().equals("private")) {
                                    metodeDobijeneNasledjivanjem.add((Method) ck);
                                }
                                /**dodavanje u model**/
                                // k.addMetod((Method) ck);
                            }
                        }
                    }

                    for (Method method : metodeDobijeneNasledjivanjem) {
                        String imeMetode = method.getName();
                        String vidljivostMetode = method.getVidljivost();
                        String povratniTip = method.getTip();
                        nasledjeneMetodeString.append("\t@Override\n\t" + vidljivostMetode + " " + povratniTip + " " + imeMetode + "(){\n\t}\n\n");
                    }

                    List<Method> metodeDobijeneNasledjivanjemInterfejsa = new ArrayList<>();
                    StringBuilder nasledjeneMetodeInterfejsaString = new StringBuilder();


                    /** dodavanje metoda iz interfejsa**/
                    for (Interfejs inter : interfejsDobijeneNasledjivanjem) {
                        for (ClassContent ck : inter.getContent()) {
                            if (ck instanceof Method) {
                                metodeDobijeneNasledjivanjemInterfejsa.add((Method) ck);
                                /**dodavanje u model**/
                                // k.addMetod((Method) ck);
                            }
                        }
                    }

                    for (Method method : metodeDobijeneNasledjivanjemInterfejsa) {
                        String imeMetode = method.getName();
                        String vidljivostMetode = method.getVidljivost();
                        String povratniTip = method.getTip();
                        nasledjeneMetodeInterfejsaString.append("\t@Override\n\t" + vidljivostMetode + " " + povratniTip + " " + imeMetode + "(){\n\t}\n\n");
                    }
                    bw.write(String.valueOf(metodeIzklase));
                    bw.write(String.valueOf(nasledjeneMetodeString));
                    bw.write(String.valueOf(nasledjeneMetodeInterfejsaString));
                    bw.write("\n}");
                    bw.close();
                    fileWriter.close();

                } catch (IOException a) {
                    throw new RuntimeException(a);
                }

            }


            /** ispisivanje enuma u fajl */
            for (Enumm en : listaEnuma) {
                String enumm = en.getName();
                File file = new File(enumm + ".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fileWriter);

                    String vidljivost = en.getVidljivost();

                    bw.write("package " + paketUKomSeNalazi + "\n\n");
                    bw.write(vidljivost + " enum " + enumm + "{\n\n");
                    List<ClassContent> sadrzajEnuma = en.getContent();

                    StringBuilder enumPolaj = new StringBuilder();

                    for (ClassContent classContent : sadrzajEnuma) {
                        if (classContent instanceof EnumElement) {
                            String imePolja = classContent.getName();
                            enumPolaj.append(imePolja + ", ");
                        }
                    }
                    if (enumPolaj.length() > 0) {
                        enumPolaj.deleteCharAt(enumPolaj.length() - 2);
                    }
                    bw.write(String.valueOf(enumPolaj));

                    bw.close();
                    fileWriter.close();

                } catch (IOException a) {
                    throw new RuntimeException(a);
                }
            }


            /** ispisivanje interfejsa u fajl */
            for (Interfejs in : listaInterfejsa) {
                String intefejs = in.getName();
                File file = new File(intefejs + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fileWriter);

                    String vidljivost = in.getVidljivost();
                    List<Interfejs> metodeIzInterfejsa  = new ArrayList<>();

                    bw.write("package " + paketUKomSeNalazi + "\n\n");
                    bw.write(vidljivost + " interface " + intefejs + " ");


                    StringBuilder nasledjivanjaIntefejs=new StringBuilder();
                    int counterr=0;
                    for (Connection conn : listaKonekcija) {
                        if (conn.getFrom().getName().equals(intefejs)) {
                            if(conn instanceof Generalizacija){
                                counterr++;
                                if(counterr==1){
                                    nasledjivanjaIntefejs.append("extend ");
                                }
                                String ime =  conn.getTo().getName();
                                nasledjivanjaIntefejs.append(ime+", ");

                                if (conn.getTo() instanceof Interfejs) {
                                    metodeIzInterfejsa.add((Interfejs) conn.getTo());
                                }
                            }

                        }
                        }
                    if (nasledjivanjaIntefejs.length() > 0) {
                        nasledjivanjaIntefejs.deleteCharAt(nasledjivanjaIntefejs.length() - 2);
                    }
                    nasledjivanjaIntefejs.append("{\n\n");
                    bw.write(String.valueOf(nasledjivanjaIntefejs));

                    List<ClassContent> sadrzajKlase = in.getContent();
                    StringBuilder metodeIzklase = new StringBuilder();
                    for (ClassContent content : sadrzajKlase) {

                        if (content instanceof Method) {
                            /**dodavanje metoda*/
                            String nazivMetode = content.getName();
                            String vidljivostMetode = ((Method) content).getVidljivost();
                            String povratnaVrednost = ((Method) content).getTip();

                            metodeIzklase.append("\t" + vidljivostMetode + " " + povratnaVrednost + " " + nazivMetode + "();\n\n");
                        }
                    }
                  
                    bw.write(String.valueOf(metodeIzklase));

                    List<Method> mDobijeneN = new ArrayList<>();
                    StringBuilder nasledjeneM = new StringBuilder();


                    /** dodavanje metoda iz interfejsa**/
                    for (Interfejs inter : metodeIzInterfejsa) {
                        for (ClassContent ck : inter.getContent()) {
                            if (ck instanceof Method) {
                                mDobijeneN.add((Method) ck);
                                /**dodavanje u model**/
                                // k.addMetod((Method) ck);
                            }
                        }
                    }

                    for (Method method : mDobijeneN) {
                        String imeMetode = method.getName();
                        String vidljivostMetode = method.getVidljivost();
                        String povratniTip = method.getTip();
                        nasledjeneM.append("\t@Override\n\t" + vidljivostMetode + " " + povratniTip + " " + imeMetode + "(){\n\t}\n\n");
                    }
                    bw.write(String.valueOf(nasledjeneM));
                    bw.write("\n\n}");
                    bw.close();
                    fileWriter.close();

                } catch (IOException a) {
                    throw new RuntimeException(a);
                }
            }
        }

    }
}
