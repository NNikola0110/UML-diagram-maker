package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;

import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter

public abstract class ElementPainter {

    private Shape shape;
    private double xPocetno;
    private double yPocetno;
    private DiagramElement diagramElement;
    public ElementPainter(DiagramElement diagramElement) {
        this.diagramElement=diagramElement;
    }

    public abstract void draw(Graphics2D g, DiagramView dv);
    public abstract boolean elementAt(Point2D.Double pos);


}
