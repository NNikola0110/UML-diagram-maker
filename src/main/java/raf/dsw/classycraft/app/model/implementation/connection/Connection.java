package raf.dsw.classycraft.app.model.implementation.connection;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;

import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter
public class Connection extends DiagramElement {

    private Interclass from;
    private Interclass to;
    private Point2D.Double startPoint;
    private Point2D.Double endPoint;
    public Connection(String name, ClassyNode parent) {

        super(name, parent);
        this.setColor(new Color(104,149,197));
    }


    public void setFrom(Interclass from) {
        this.from = from;
        getParent().notifySubscribers("paint");
    }

    public void setTo(Interclass to) {
        this.to = to;
        getParent().notifySubscribers("paint");
    }

    public void setStartPoint(Point2D.Double startPoint) {
        this.startPoint = startPoint;
        getParent().notifySubscribers("paint");
    }

    public void setEndPoint(Point2D.Double endPoint) {
        this.endPoint = endPoint;
        getParent().notifySubscribers("paint");
    }
}
