package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.model.implementation.Package;
import raf.dsw.classycraft.app.model.implementation.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PackageView extends JPanel implements ISubscriber {

    Package p;
    StateManager s;
    JTabbedPane jTabbedPane;
    JLabel projectName;
    JLabel author;
    List<DiagramView> tabs = new ArrayList<>();
    List<DiagramView> diagramViews = new ArrayList<>();
    int exists = 0;
    private JScrollPane jScrollPane;
    private JViewport jViewport;

    public PackageView() {
        init();
    }

   /* public PackageView(Package p) {
        this.p = p;
        init();
    }*/

    private void init() {
        projectName=new JLabel();
        projectName.setVisible(true);
        add(projectName);


        author = new JLabel();
        author.setVisible(true);
        add(author);

        s = new StateManager();

        jTabbedPane=new JTabbedPane();
        jTabbedPane.setVisible(true);
        add(jTabbedPane);

        BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(box);

        projectName.setAlignmentX(CENTER_ALIGNMENT);
        author.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void openView(ClassyNodeComposite selected){
        if(selected instanceof Package) {
            this.p = (Package) selected;
            p.addSubscriber(this);
            p.getParent().addSubscriber(this);
            setLabels();
            setDiagramViews();
        }
    }

    public void setLabels() {
        if(p.getParent() instanceof Project){
            author.setText("Autor: "+(((Project)p.getParent()).getAuthor()));
            projectName.setText("Ime projekta: " + (p.getParent().getName()));
        }
        else if(p.getParent() instanceof Package){
            while(!(p.getParent() instanceof Project)){
                Package packageParent = (Package) p.getParent();
                p.setParent(packageParent.getParent());
            }
            author.setText("Autor: "+(((Project)p.getParent()).getAuthor()));
            projectName.setText("Ime projekta: " + (p.getParent().getName()));
        }
    }

    public DiagramView exists(Diagram child){
       for(int i=0; i<diagramViews.size(); i++){
           if(diagramViews.get(i).getDiagram().equals(child)){
               return diagramViews.get(i);
           }
       }
       return null;
    }

    public void setDiagramViews(){
        tabs.clear();
        jTabbedPane.removeAll();
        //exists = 0;
            for (ClassyNode child : p.getChildren()) {
                if (child instanceof Diagram) {
                    DiagramView diagramView = exists((Diagram)child);
                    if(diagramView == null){
                        DiagramView dv = new DiagramView((Diagram)child);
                        diagramViews.add(dv);
                        dv.setPackageView(this);
                        tabs.add(dv);
                    }
                    else{
                        tabs.add(diagramView);
                    }
                }
            }
            for (DiagramView tab : tabs) {
                jScrollPane = new JScrollPane(tab);
                jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                //  jScrollPane.setPreferredSize(new Dimension(50, 70));
                jViewport=jScrollPane.getViewport();
                jScrollPane.setViewportView(tab);
                tab.setJViewport(jViewport);

                jTabbedPane.add(tab.getDiagram().getName(), jScrollPane);

                //jTabbedPane.add(tab.getDiagram().getName(), tab);
            }

    }

    public void removeJTabb(){
        tabs.clear();
        jTabbedPane.removeAll();
       projectName.setText(null);
       author.setText(null);
    }
    public void removeTabs(){
        tabs.clear();
        jTabbedPane.removeAll();
    }

    public void startInterclassState(String vrsta){s.setInterclassState(vrsta);}
    public void startConnectionState(String vrsta, String imePolja, String kardinalnost, String vidljivostPolja){s.setConnectionState(vrsta, imePolja, kardinalnost,vidljivostPolja);}
    public void startContentState(String vrsta){
        s.setContentState(vrsta);
    }
    public void startDeleteState() {
        s.setDeleteState();
    }
    public void startSelectionState(){s.setSelectionState();}
    public void startMoveState(){s.setMoveState();}
    public void startDuplicateState() {s.setDuplicateState();}
    public void startChangeState() {s.setChangeState();}
    public void startZoomInState(){s.setZoomInState();}
    public void startZoomOutState(){s.setZoomOutState();}
    public void startZoomToFitState(){s.setZoomToFiTState();}



    public void misKliknut(Point p, DiagramView dv){s.getCurrState().misKliknut(p,dv);}
    public void misPritisnut(Point p, DiagramView dv) {s.getCurrState().misPritisnut(p,dv);}
    public void misOtpusten(Point p, DiagramView dv){
        s.getCurrState().misOtpusten(p,dv);
    }
    public void misPrevucen(Point p, DiagramView dv){
        s.getCurrState().misPrevucen(p,dv);
    }

    @Override
    public void update(Object notification){
        if(notification instanceof  Package)
            openView((Package) notification);
        if (notification instanceof  String) {
            if(notification.equals("child"))
                setDiagramViews();
            else if(notification.equals("name"))
                setLabels();
            else if (notification.equals("remove")) {
                removeTabs();
            } else if(notification.equals("removeAll"))
                removeJTabb();
       // MainFrame.getInstance().setPackageView(null);
        }


    }


}
