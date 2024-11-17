package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.state.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private PackageView packageView;
    List<ElementPainter> painters;
    List<ElementPainter> selectionModel;
    private JPanel jPanel;
    private JLabel jLabel;
    private JViewport jViewport;
    private AffineTransform affineTransform;
    private double scale = 1;

    public DiagramView(Diagram diagram) {

        this.diagram = diagram;
        this.diagram.addSubscriber(this);
        init();
    }

    private void init() {
        this.jPanel = new JPanel();
        this.jLabel = new JLabel(diagram.getName());
        this.packageView = new PackageView();
        this.painters = new ArrayList<>();
        this.selectionModel = new ArrayList<>();
        addMouseListener(new Mouse(this));
        addMouseMotionListener(new Mouse(this));
        /*addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                updatePreferredSize(e.getWheelRotation(),e.getPoint());
            }
        });*/
        this.setPreferredSize(new Dimension(2000,2000));
        affineTransform = new AffineTransform();
    }

    /*private void updatePreferredSize(int wheelRotation, Point point) {
        double d = (double)wheelRotation * 1.08;
        d = (wheelRotation>0) ? 1/d : -d;
        int w = (int) (getWidth()*d);
        int h = (int) (getHeight()*d);
        setPreferredSize(new Dimension(w,h));
        getParent().doLayout();
    }*/

    public void setTransformation(double scale){
        this.scale=scale;
        affineTransform.setToScale(scale,scale);
        //affineTransform.translate(0,0);
    }

    public Point2D.Double getOriginalCoordinates(Point2D.Double scaledPoint){
        try {
            AffineTransform inverseTransform = affineTransform.createInverse();
            Point2D.Double originalPoint = new Point2D.Double();
            inverseTransform.transform(scaledPoint,originalPoint);
            return originalPoint;
        }catch(NoninvertibleTransformException e){
            //e.printStackTrace();
            return null;
        }
    }

    public void zoomIn(){
        scale*=1.2;
        if(scale>3)scale = 3;
        setTransformation(scale);
        repaint();
    }
    public void zoomOut(){
        scale*=0.8;
        if(scale<0.4)scale = 0.4;
        setTransformation(scale);
        repaint();
    }
    @Override
    public void update(Object notification) {
        //MainFrame.getInstance().reload(packageView);
        if(notification.equals("paint"))
            repaint();
        else packageView.setDiagramViews();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.transform(affineTransform);

        for(ElementPainter x:painters){
            x.draw(graphics,this);
        }
        for(ElementPainter y:selectionModel){
            if(y instanceof InterclassPainter){
                Interclass i = ((InterclassPainter) y).getInterclass();
                float[] razmak = {7.0f};
                BasicStroke isprekidano = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, razmak, 0.0f);
                graphics.setStroke(isprekidano);
                graphics.setColor(Color.BLACK);
                Rectangle2D.Double rect = new Rectangle2D.Double(i.getLocation().x - 10,i.getLocation().y - 10,i.getSize().width + 20,i.getSize().height + 20);
                graphics.draw(rect);
                graphics.setStroke(new BasicStroke(2.5f));
            }
            else if(y instanceof ConnectionPainter){
                Connection c = ((ConnectionPainter) y).getConnection();
                float[] razmak = {7.0f};
                BasicStroke isprekidano = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, razmak, 0.0f);
                graphics.setStroke(isprekidano);
                graphics.setColor(Color.BLACK);
                Point2D.Double startDiagonalPoint = c.getStartPoint();
                Point2D.Double endDiagonalPoint = c.getEndPoint();
                if (startDiagonalPoint != null && endDiagonalPoint != null) {
                    double x1 = Math.min(startDiagonalPoint.x, endDiagonalPoint.x);
                    double y1 = Math.min(startDiagonalPoint.y, endDiagonalPoint.y);
                    double width = Math.abs(startDiagonalPoint.x - endDiagonalPoint.x);
                    double height = Math.abs(startDiagonalPoint.y - endDiagonalPoint.y);

                    Rectangle2D.Double rect = new Rectangle2D.Double(x1,y1,width,height);
                    graphics.draw(rect);
                }
                graphics.setStroke(new BasicStroke(2.5f));
            }

        }

    }
    public BufferedImage createImage() {

        int x=0;
        int y=0;

        int desiredWidth= (int) packageView.getJViewport().getSize().getWidth();

        int desiredHeight = (int) packageView.getJViewport().getSize().getHeight();

        for (ElementPainter p: painters) {
            Interclass interclass= (Interclass) p.getDiagramElement();
            int dodajNaX= (int) interclass.getSize().getWidth();
            int dodajNaY= (int) interclass.getSize().getHeight();
            int x_1= (int) interclass.getLocation().getX();
            int y_1= (int) interclass.getLocation().getY();
            int x_krajnji=x_1+dodajNaX;
            int y_krajnji=y_1+dodajNaY;

        //    int najblizaTackaX=100000;
        //   int najblizaTackaY=100000;

            if(desiredWidth-x_krajnji<0){
                desiredWidth=x_krajnji;
            }
            if(desiredHeight-y_krajnji<0){
                desiredHeight=y_krajnji;
            }
       //    if(najblizaTackaX>x_1){
       //        x=najblizaTackaX;
       //    }

       //    if(najblizaTackaY>y_1){
       //        y=najblizaTackaY;
       //    }
        }
        desiredWidth+=10;
        desiredHeight+=10;
       // x-=10;
        //y-=10;
        BufferedImage bufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

     //   g.translate(x, y);
        this.paint(g);
        return bufferedImage;
    }
}
