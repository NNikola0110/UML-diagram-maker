package raf.dsw.classycraft.app.painters.connection;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

import java.awt.*;
import java.awt.geom.*;

public class GeneralizacijaPainter extends ConnectionPainter {
    public GeneralizacijaPainter(DiagramElement diagramElement) {
        super(diagramElement);
        setConnection((Connection)diagramElement);

    }

    @Override
    public void draw(Graphics2D g, DiagramView dv) {
        g.setStroke(new BasicStroke(2.5f));
        g.setColor(new Color(43, 42, 76));
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
        g.draw(line);
        nacrtajZatvorenuStrelicu(g);
    }

    public void nacrtajZatvorenuStrelicu(Graphics2D g) {

        Path2D.Double path = new Path2D.Double();
        path.moveTo(getConnection().getEndPoint().x,getConnection().getEndPoint().y);
        double x = getConnection().getEndPoint().x - 10;
        double y = getConnection().getEndPoint().y -5;
        path.lineTo(x,y);
        double x1 = getConnection().getEndPoint().x - 10;
        double y1 = getConnection().getEndPoint().y + 5;
        path.lineTo(x1,y1);
        path.closePath();

        AffineTransform af = new AffineTransform();
        double angle = Math.atan2(getConnection().getEndPoint().y - getConnection().getStartPoint().y,getConnection().getEndPoint().x - getConnection().getStartPoint().x);
        af.rotate(angle,getConnection().getEndPoint().x,getConnection().getEndPoint().y);
        path.transform(af);
        g.setColor(Color.WHITE);
        g.fill(path);
        g.setColor(new Color(43, 42, 76));
        g.draw(path);
    }

}
