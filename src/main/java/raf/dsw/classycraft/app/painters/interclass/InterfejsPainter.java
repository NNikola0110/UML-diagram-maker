package raf.dsw.classycraft.app.painters.interclass;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class InterfejsPainter extends InterclassPainter {
    //private Interfejs interfejs;
    private Rectangle2D rectangle;

    public InterfejsPainter(DiagramElement diagramElement) {
        super(diagramElement);
        setInterclass((Interclass) diagramElement);
    }

    @Override
    public void draw(Graphics2D g, DiagramView dv) {
        getInterclass().getConnectionPoints().clear();
        g.setPaint(getInterclass().getColor());
        g.setStroke(getInterclass().getStroke());

        Font font = new Font("Ariel",Font.PLAIN,12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);
        int currWidth = 0;

        for(ClassContent c:getInterclass().getContent()){

            currWidth = fm.stringWidth(c.toString());
            if(currWidth > getInterclass().getMaxWidth()) {
                getInterclass().setMaxWidth(currWidth + 20);
            }
            c.setPomeraj(60 + getInterclass().getContent().indexOf(c) * 20);
            g.drawString(c.toString(), (float) (getInterclass().getLocation().x + 10), (float) (getInterclass().getLocation().y + c.getPomeraj()));
            getInterclass().setSize(new Dimension(getInterclass().getMaxWidth(),c.getPomeraj() + 10));
        }
        Font font3 = new Font("interface",Font.ITALIC,10);
        FontMetrics fm3 = g.getFontMetrics(font3);
        int currWidth2 = fm3.stringWidth("<<interface>>");
        if(currWidth2> getInterclass().getMaxWidth())
            getInterclass().setMaxWidth(currWidth2 + 20);


        Font font2 = new Font("Ariel",Font.ITALIC,12);
        FontMetrics fm2 = g.getFontMetrics(font2);

        currWidth = fm2.stringWidth(getInterclass().getName());
        if(currWidth> getInterclass().getMaxWidth())
            getInterclass().setMaxWidth(currWidth + 20);

        getInterclass().setSize(new Dimension(getInterclass().getMaxWidth(),getInterclass().getSize().height));
        g.setFont(font3);
        g.drawString("<<interface>>", (float) ((2*(getInterclass().getLocation().x) + getInterclass().getSize().width)/2 - currWidth2/2), (float) (getInterclass().getLocation().y + 10));
        g.setFont(font2);
        g.drawString(getInterclass().getName(), (float) ((2*(getInterclass().getLocation().x) + getInterclass().getSize().width)/2 - currWidth/2), (float) (getInterclass().getLocation().y + 30));
        g.setFont(font);

        setRectangle(new Rectangle2D.Double(getInterclass().getLocation().x,getInterclass().getLocation().y,getInterclass().getSize().width,getInterclass().getSize().height));
        getInterclass().setShape(super.getRectangle());
        g.draw(super.getRectangle());
        setShape(getInterclass().getShape());
        //g.drawRect(klasa.getLocation().x,klasa.getLocation().y,klasa.getSize().width,klasa.getSize().height);
        //g.setFont(new Font("Ariel",Font.BOLD,20));
        Line2D.Double line = new Line2D.Double(getInterclass().getLocation().x,getInterclass().getLocation().y + 40,getInterclass().getLocation().x + getInterclass().getSize().width,getInterclass().getLocation().y + 40);
        g.draw(line);

        getInterclass().napraviTacke();
        getInterclass().setMaxWidth(0);
    }

    @Override
    public boolean elementAt(Point2D.Double pos) {
        return getInterclass().getShape().contains(pos);
    }
}
