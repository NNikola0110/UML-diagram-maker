package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.command.implementation.DeliteComand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.model.implementation.content.Atribute;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;
import raf.dsw.classycraft.app.model.implementation.content.EnumElement;
import raf.dsw.classycraft.app.model.implementation.content.Method;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State{
    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);

        if(dv.getSelectionModel().isEmpty()) {
            ElementPainter provera = provera(p, dv);
            if (provera != null) {
                if (provera instanceof InterclassPainter) {
                    Object[] obj = {"interclass", "content"};
                    int s = JOptionPane.showOptionDialog(null, "Izaberi sta hoces da obrises: ", "Izaberi!",
                            0, 3, null, obj, obj[0]);

                    if (s == 1) {
                        if (!(((InterclassPainter) provera).getInterclass().getContent().isEmpty())) {
                            Object[] obj2 = ((InterclassPainter) provera).getInterclass().getContent().toArray();
                            Object s2 = JOptionPane.showInputDialog(null, "Izaberi sta hoces da obrises: ", "Izaberi!", JOptionPane.QUESTION_MESSAGE, null, obj2, obj2[0]);
                            if (s2 instanceof Atribute)
                                ((InterclassPainter) provera).getInterclass().removeAtribut((Atribute) s2);
                            else if (s2 instanceof Method)
                                ((InterclassPainter) provera).getInterclass().removeMetod((Method) s2);
                            else if (s2 instanceof EnumElement)
                                ((InterclassPainter) provera).getInterclass().removeEnumElement((EnumElement) s2);
                            //dv.repaint();
                        } else {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
                        }
                    } else if (s == 0) {
                        obrisiVezu((InterclassPainter) provera,dv);
                        dv.getPainters().remove(provera);
                        MainFrame.getInstance().getClassyTree().removeDiagramChild(((InterclassPainter) provera).getInterclass());
                    }
                } else if (provera instanceof ConnectionPainter) {
                    dv.getPainters().remove(provera);
                    MainFrame.getInstance().getClassyTree().removeDiagramChild(((ConnectionPainter) provera).getConnection());

                }
            }
        }else{
                for (ElementPainter ep:dv.getSelectionModel()) {
                    dv.getPainters().remove(ep);
                    if (ep instanceof InterclassPainter) {
                        obrisiVezu((InterclassPainter) ep,dv);
                        dv.getPainters().remove(ep);
                        MainFrame.getInstance().getClassyTree().removeDiagramChild(((InterclassPainter) ep).getInterclass());

                    } else if (ep instanceof ConnectionPainter) {
                        dv.getPainters().remove(ep);
                        MainFrame.getInstance().getClassyTree().removeDiagramChild(((ConnectionPainter) ep).getConnection());
                    }
                }
          //  DeliteComand dc=new DeliteComand(dv);

            dv.getSelectionModel().clear();
            dv.repaint();
        }
    }

    private void obrisiVezu(InterclassPainter provera, DiagramView dv) {
        for(ConnectionPainter cp:provera.getConnections()){
            if(dv.getPainters().contains(cp)){
                dv.getPainters().remove(cp);
                MainFrame.getInstance().getClassyTree().removeDiagramChild(cp.getConnection());
            }
        }
    }

    @Override
    public void misPritisnut(Point p, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point p, DiagramView dv) {

    }

    @Override
    public void misPrevucen(Point p, DiagramView dv) {

    }

    public InterclassPainter vratiPainter(Interclass i, DiagramView dv){
        for(ElementPainter ep:dv.getPainters()){
            if(ep instanceof InterclassPainter){
                InterclassPainter ip = (InterclassPainter) ep;
                if(i.equals(ip.getInterclass())){
                    return ip;
                }
            }
        }
        return null;
    }

    public ElementPainter provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p)) {
                   return ep;
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
