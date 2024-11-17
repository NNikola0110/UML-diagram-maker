package raf.dsw.classycraft.app.painters.interclass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
@Getter
@Setter

public class KlasaPainter extends InterclassPainter {

    //private Klasa klasa;
    //private Rectangle2D rectangle;
    private int brojac = 0;
    public KlasaPainter(DiagramElement diagramElement) {
        super(diagramElement);
        setInterclass((Klasa)diagramElement);
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
            if(c instanceof Atribute){
                currWidth = fm.stringWidth(c.toString());
                if(currWidth > getInterclass().getMaxWidth()) {
                    getInterclass().setMaxWidth(currWidth + 20);
                }
                //c.setPomeraj(50 + getInterclass().getContent().indexOf(c) * 20);
                c.setPomeraj(50 + brojac*20);
                g.drawString(c.toString(), (float) (getInterclass().getLocation().x + 10), (float) (getInterclass().getLocation().y + c.getPomeraj()));
                brojac++;
                getInterclass().setSize(new Dimension(getInterclass().getMaxWidth(),c.getPomeraj() + 10));
            }
        }
        for(ClassContent c:getInterclass().getContent()){
            if(c instanceof Method){
                currWidth = fm.stringWidth(c.toString());
                if(currWidth > getInterclass().getMaxWidth()) {
                    getInterclass().setMaxWidth(currWidth + 20);
                }
                //c.setPomeraj(50 + getInterclass().getContent().indexOf(c) * 20);
                c.setPomeraj(50 + brojac*20);
                g.drawString(c.toString(), (float) (getInterclass().getLocation().x + 10), (float) (getInterclass().getLocation().y + c.getPomeraj()));
                brojac++;
                getInterclass().setSize(new Dimension(getInterclass().getMaxWidth(),c.getPomeraj() + 10));
            }
        }
        brojac = 0;

        Font font2 = new Font("Ariel",Font.BOLD,15);
        g.setFont(font2);
        FontMetrics fm2 = g.getFontMetrics(font2);
        currWidth = getInterclass().isApstraktna() ? fm2.stringWidth(getInterclass().getName() + " (A)"):fm2.stringWidth(getInterclass().getName());

        if(currWidth> getInterclass().getMaxWidth()){
            getInterclass().setMaxWidth(currWidth + 20);
        }
        getInterclass().setSize(new Dimension(getInterclass().getMaxWidth(),getInterclass().getSize().height));
        if(getInterclass().isApstraktna())
            g.drawString(getInterclass().getName() + " (A)", (float) ((2*(getInterclass().getLocation().x) + getInterclass().getSize().width)/2 - currWidth/2), (float) (getInterclass().getLocation().y + 20));
        else{
            g.drawString(getInterclass().getName(), (float) ((2*(getInterclass().getLocation().x) + getInterclass().getSize().width)/2 - currWidth/2), (float) (getInterclass().getLocation().y + 20));

        }
        g.setFont(font);

        setRectangle(new Rectangle2D.Double(getInterclass().getLocation().x,getInterclass().getLocation().y,getInterclass().getSize().width,getInterclass().getSize().height));
        getInterclass().setShape(super.getRectangle());
        g.draw(super.getRectangle());
        setShape(getInterclass().getShape());
        Line2D.Double line = new Line2D.Double(getInterclass().getLocation().x,getInterclass().getLocation().y + 30,getInterclass().getLocation().x + getInterclass().getSize().width,getInterclass().getLocation().y + 30);
        g.draw(line);

        getInterclass().napraviTacke();
        getInterclass().setMaxWidth(0);
    }

    @Override
    public boolean elementAt(Point2D.Double pos) {
        return getInterclass().getShape().contains(pos);

    }

}
