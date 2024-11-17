package raf.dsw.classycraft.app.painters.connection;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

import java.awt.*;
import java.awt.geom.*;

public class AgregacijaPainter extends ConnectionPainter {
    public AgregacijaPainter(DiagramElement diagramElement) {
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
        nacrtajStrelicu(g);
        nacrtajRomb(g);
    }

    public void nacrtajRomb(Graphics2D g){
        double angle = Math.atan2(getConnection().getEndPoint().y - getConnection().getStartPoint().y, getConnection().getEndPoint().x - getConnection().getStartPoint().x);
        AffineTransform transform = AffineTransform.getTranslateInstance(getConnection().getStartPoint().x, getConnection().getStartPoint().y);
        transform.rotate(angle + Math.toRadians(45));

        int squareSize = 10;

        Rectangle2D square = new Rectangle2D.Double(-squareSize / 2.0, -squareSize / 2.0, squareSize, squareSize);

        Shape transformedSquare = transform.createTransformedShape(square);
        g.setColor(Color.WHITE);
        g.fill(transformedSquare);
        g.setColor(new Color(43, 42, 76));
        g.draw(transformedSquare);
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
