package raf.dsw.classycraft.app.painters.connection;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class ZavisnostPainter extends ConnectionPainter {
    public ZavisnostPainter(DiagramElement diagramElement) {
        super(diagramElement);
        setConnection((Connection)diagramElement);

    }

    @Override
    public void draw(Graphics2D g, DiagramView dv) {

        if(getConnection().getTo() != null) {
            double minPath = Double.MAX_VALUE;
            for (Point2D.Double point1 : getConnection().getFrom().getConnectionPoints()) {
                for (Point2D.Double point2 : getConnection().getTo().getConnectionPoints()) {
                    double path = point1.distance(point2);
                    if (path < minPath) {
                        minPath = path;
                        getConnection().setStartPoint(point1);
                        getConnection().setEndPoint(point2);
                    }
                }
            }
        }
        Line2D line = new Line2D.Double(getConnection().getStartPoint().x,getConnection().getStartPoint().y,getConnection().getEndPoint().x,getConnection().getEndPoint().y);
        setShape(line);
        g.setColor(new Color(43, 42, 76));
        float[] razmak = {7.0f};
        BasicStroke isprekidano = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, razmak, 0.0f);
        g.setStroke(isprekidano);
        g.draw(line);
        nacrtajStrelicu(g);
    }

    public void nacrtajStrelicu(Graphics2D g){
        double arrowSize = 10;

        double angle = Math.atan2(getConnection().getEndPoint().y - getConnection().getStartPoint().y, getConnection().getEndPoint().x - getConnection().getStartPoint().x);

        double x3 = getConnection().getEndPoint().x - arrowSize * Math.cos(angle - Math.PI / 6);
        double y3 = getConnection().getEndPoint().y - arrowSize * Math.sin(angle - Math.PI / 6);
        double x4 = getConnection().getEndPoint().x - arrowSize * Math.cos(angle + Math.PI / 6);
        double y4 = getConnection().getEndPoint().y - arrowSize * Math.sin(angle + Math.PI / 6);

        Line2D.Double line = new Line2D.Double(getConnection().getEndPoint().x, getConnection().getEndPoint().y, (int) x3, (int) y3);
        g.draw(line);
        Line2D.Double line2 = new Line2D.Double(getConnection().getEndPoint().x, getConnection().getEndPoint().y, (int) x4, (int) y4);
        g.draw(line2);

    }
}
