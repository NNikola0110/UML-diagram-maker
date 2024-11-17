package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.controller.actions.*;
import raf.dsw.classycraft.app.gui.swing.controller.drawingActions.*;

@Setter
@Getter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction projectAction;
    private RemoveAction removeAction;
    private ChangeNameAction changeNameAction;
    private ChangeAuthor changeAuthor;
    private AddInterclassAction addInterclassAction;
    private AddConnectionAction addConnectionAction;
    private SelectAction selectAction;
    private MoveAction moveAction;
    private AddContentAction addContentAction;
    private DeleteAction deleteAction;
    private DuplicateAction duplicateAction;
    private ChangeAction changeAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private ZoomToFitAction zoomToFitAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private CodeGeneratorAction codeGeneratorAction;
    private SaveProjectAction saveProjectAction;
    private ImageAction imageAction;

    public ActionManager(){
        initialiseAction();
    }

    private void initialiseAction(){

        exitAction=new ExitAction();
        aboutUsAction=new AboutUsAction();
        projectAction=new NewProjectAction();
        removeAction=new RemoveAction();
        changeNameAction=new ChangeNameAction();
        changeAuthor=new ChangeAuthor();
        addInterclassAction=new AddInterclassAction();
        addConnectionAction=new AddConnectionAction();
        selectAction=new SelectAction();
        moveAction=new MoveAction();
        addContentAction=new AddContentAction();
        deleteAction=new DeleteAction();
        duplicateAction=new DuplicateAction();
        changeAction=new ChangeAction();
        zoomInAction=new ZoomInAction();
        zoomOutAction=new ZoomOutAction();
        zoomToFitAction=new ZoomToFitAction();
        undoAction=new UndoAction();
        redoAction=new RedoAction();
        codeGeneratorAction=new CodeGeneratorAction();
        saveProjectAction=new SaveProjectAction();
        imageAction=new ImageAction();
    }
}
