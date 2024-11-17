package raf.dsw.classycraft.app.model.implementation.interclass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Interclass extends DiagramElement implements Cloneable {

    private String vidljivost;
    private boolean apstraktna;
    private Shape shape;
    private Point2D.Double location;
    private Dimension size;
    private int maxWidth;
    List<ClassContent> content;
    List<Point2D.Double> connectionPoints;
    public Interclass(String name, ClassyNode parent) {
        super(name, parent);
        this.content = new ArrayList<>();
        this.connectionPoints = new ArrayList<>();
    }

    public void napraviTacke(){
        Point2D.Double p1 = new Point2D.Double((getLocation().x) + getSize().width/2,getLocation().y);
        Point2D.Double p2 = new Point2D.Double((getLocation().x) + getSize().width/2,getLocation().y + getSize().height);
        Point2D.Double p3 = new Point2D.Double(getLocation().x,getLocation().y + getSize().height/2);
        Point2D.Double p4 = new Point2D.Double(getLocation().x + getSize().width,getLocation().y + getSize().height/2);

        getConnectionPoints().add(p1);
        getConnectionPoints().add(p2);
        getConnectionPoints().add(p3);
        getConnectionPoints().add(p4);
    }

    public void setLocation(Point2D.Double location) {
        this.location = location;
        getParent().notifySubscribers("paint");
    }

    public void setSize(Dimension size) {
        this.size = size;
        getParent().notifySubscribers("paint");
    }

    public void setVidljivost(String vidljivost) {
        this.vidljivost = vidljivost;
        getParent().notifySubscribers("paint");
    }

    public void addAtribut(Atribute atribut){
        content.add(atribut);
        getParent().notifySubscribers("paint");
    }
    public void removeAtribut(Atribute atribut){
        content.remove(atribut);
        getParent().notifySubscribers("paint");
    }

    public void addMetod(Method metoda){
        content.add(metoda);
        getParent().notifySubscribers("paint");
    }
    public void removeMetod(Method metoda){
        content.remove(metoda);
        getParent().notifySubscribers("paint");
    }

    public void addEnumElement(EnumElement enumElement){
        content.add(enumElement);
        getParent().notifySubscribers("paint");
    }
    public void removeEnumElement(EnumElement enumElement){
        content.remove(enumElement);
        getParent().notifySubscribers("paint");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Interclass clonedInterclass = (Interclass) super.clone();

        clonedInterclass.location = new Point2D.Double(this.location.x + 20,this.location.y + 20);
        clonedInterclass.content = new ArrayList<>();
        for(ClassContent c:this.content){
            clonedInterclass.content.add((ClassContent) c.clone());
        }
        clonedInterclass.connectionPoints = new ArrayList<>(this.connectionPoints);
        return clonedInterclass;
    }
}
