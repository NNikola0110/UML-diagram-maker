package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.painters.ElementPainter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DeliteComand extends AbstractCommand {

    private PackageView packageView;
    private DiagramView currDiagramView;
    private ArrayList<ElementPainter> selected;
    private ArrayList<ElementPainter> deleted;

    public DeliteComand(DiagramView diagramView){
        this.currDiagramView=diagramView;
        this.selected= (ArrayList<ElementPainter>) diagramView.getSelectionModel();
        deleted=new ArrayList<>();

    }

    @Override
    public void doCommand() {

    }

    @Override
    public void undoCommand() {

    }
}
