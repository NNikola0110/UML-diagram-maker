package raf.dsw.classycraft.app.state;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.connection.Agregacija;
import raf.dsw.classycraft.app.model.implementation.connection.Generalizacija;
import raf.dsw.classycraft.app.model.implementation.connection.Kompozicija;
import raf.dsw.classycraft.app.model.implementation.connection.Zavisnost;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.*;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

@Getter
@Setter
public class ConnectionState implements State {

    private String vrstaElementa;
    private String imePolja;
    private String kardinalnost;
    private String vidljivostPolja;
    private ConnectionPainter cp;
    private double minPath;
    @Override
    public void misKliknut(Point p, DiagramView dv) {

    }

    @Override
    public void misPritisnut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        InterclassPainter provera = provera(p,dv);
        if(provera!=null) {
            if (vrstaElementa.equals("agregacija")) {
                Agregacija a = new Agregacija("agregacija", dv.getDiagram());
                a.setImePolja(imePolja);
                a.setVidljivost(vidljivostPolja);
                a.setKardinalnost(kardinalnost);
                a.setFrom(provera.getInterclass());
                a.setStartPoint(p);
                a.setEndPoint(p);
                cp = new AgregacijaPainter(a);
                dv.getPainters().add(cp);
                provera.getConnections().add(cp);
                MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(), a);

            } else if (vrstaElementa.equals("kompozicija")) {
                Kompozicija k = new Kompozicija("kompozicija", dv.getDiagram());
                k.setImePolja(imePolja);
                k.setVidljivost(vidljivostPolja);
                k.setKardinalnost(kardinalnost);
                k.setFrom(provera.getInterclass());
                k.setStartPoint(p);
                k.setEndPoint(p);
                cp = new KompozicijaPainter(k);
                provera.getConnections().add(cp);
                dv.getPainters().add(cp);
                MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(), k);

            } else if(vrstaElementa.equals("generalizacija")){
                Generalizacija g = new Generalizacija("generalizacija", dv.getDiagram());
                g.setFrom(provera.getInterclass());
                g.setStartPoint(p);
                g.setEndPoint(p);
                cp = new GeneralizacijaPainter(g);
                provera.getConnections().add(cp);
                dv.getPainters().add(cp);
                MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(), g);

            }else if(vrstaElementa.equals("zavisnost")){
                Zavisnost z = new Zavisnost("zavisnost", dv.getDiagram());
                z.setTip(imePolja);
                z.setFrom(provera.getInterclass());
                z.setStartPoint(p);
                z.setEndPoint(p);
                cp = new ZavisnostPainter(z);
                provera.getConnections().add(cp);
                dv.getPainters().add(cp);
                MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(), z);
            }
        }
    }

    @Override
    public void misOtpusten(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        InterclassPainter provera = provera(p,dv);
        if(provera!=null){
            minPath = Double.MAX_VALUE;
            for(Point2D.Double point1:cp.getConnection().getFrom().getConnectionPoints()){
                for(Point2D.Double point2:provera.getInterclass().getConnectionPoints()){
                    //double path = point1.distance(point2);
                    double path = Math.sqrt((point2.y - point1.y) * (point2.y - point1.y) + (point2.x - point1.x) * (point2.x - point1.x));
                    if(path<minPath){
                        minPath = path;
                        cp.getConnection().setStartPoint(point1);
                        cp.getConnection().setEndPoint(point2);
                    }
                }
            }
            cp.getConnection().setTo(provera.getInterclass());
            provera.getConnections().add(cp);
            //dv.repaint();
        }else{
            dv.getPainters().remove(cp);
            MainFrame.getInstance().getClassyTree().removeDiagramChild(cp.getConnection());
            //provera.getConnections().remove(cp);
            //dv.getDiagram().removeChild(cp.getConnection());
        }
    }

    @Override
    public void misPrevucen(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        cp.getConnection().setEndPoint(p);
        dv.repaint();
    }

    public InterclassPainter provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p) && ep instanceof InterclassPainter) {
                    System.out.println("Moze da se poveze.");
                    return ((InterclassPainter) ep);
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
