package raf.dsw.classycraft.app.painters.connection;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.painters.ElementPainter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

@Getter
@Setter
public abstract class ConnectionPainter extends ElementPainter {

    private Connection connection;

    public ConnectionPainter(DiagramElement diagramElement) {
        super(diagramElement);
    }

    @Override
    public abstract void draw(Graphics2D g, DiagramView dv);

    @Override
    public boolean elementAt(Point2D.Double pos){
        double distance = ((Line2D)getShape()).ptSegDist(pos.getX(), pos.getY());
        return distance <= 3.0;
    }

}
