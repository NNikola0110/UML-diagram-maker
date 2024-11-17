package raf.dsw.classycraft.app.painters.interclass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class InterclassPainter extends ElementPainter {

   private Interclass interclass;
    private Rectangle2D rectangle;
    List<ConnectionPainter> connections;
    //private Shape shape;
    public InterclassPainter(DiagramElement diagramElement) {
        super(diagramElement);
        connections = new ArrayList<>();
    }

    @Override
    public void draw(Graphics2D g, DiagramView dv) {}

    @Override
    public abstract boolean elementAt(Point2D.Double pos);
}
