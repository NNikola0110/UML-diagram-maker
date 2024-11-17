package raf.dsw.classycraft.app.painters.interclass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class LassoPainter extends ElementPainter{
    private Point2D.Double start;
    private Point2D.Double end;
    public LassoPainter(Point2D.Double start) {
        super(null);
        this.start=start;
    }

    public void draw(Graphics2D g, DiagramView dv) {
        float[] razmak = {7.0f};
        BasicStroke isprekidano = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, razmak, 0.0f);
        g.setStroke(isprekidano);
        g.setColor(Color.BLACK);
        double x = Math.min(start.x, end.x);
        double y = Math.min(start.y, end.y);
        double width = Math.abs(start.x - end.x);
        double height = Math.abs(start.y - end.y);
        Rectangle2D.Double rect = new Rectangle2D.Double(x,y,width,height);
        g.draw(rect);
        setShape(rect);
        g.setStroke(new BasicStroke(2.5f));
    }

    public boolean elementAt(Point2D.Double pos) {
        return false;
    }
}
