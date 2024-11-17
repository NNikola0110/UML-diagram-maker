package raf.dsw.classycraft.app.state;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateManager {
    private State currState;
    private InterclassState interclass;
    private ConnectionState connection;
    private ContentState content;
    private DeleteState delete;
    private SelectionState selection;
    private MoveState move;
    private DuplicateState duplicate;
    private ChangeState change;
    private ZoomInState zoomIn;
    private ZoomOutState zoomOut;
    private ZoomToFitState zoomToFit;

    public StateManager() {

        interclass=new InterclassState();
        connection=new ConnectionState();
        content =new ContentState();
        delete=new DeleteState();
        selection=new SelectionState();
        move=new MoveState();
        duplicate=new DuplicateState();
        change=new ChangeState();
        zoomIn=new ZoomInState();
        zoomOut=new ZoomOutState();
        zoomToFit=new ZoomToFitState();
    }

    public void setInterclassState(String vrsta){
        interclass.setVrstaElementa(vrsta);
        currState = interclass;
    }
    public void setConnectionState(String vrsta, String imePolja, String kardinalnost, String vidljivostPolja){
        connection.setVrstaElementa(vrsta);
        connection.setImePolja(imePolja);
        connection.setKardinalnost(kardinalnost);
        connection.setVidljivostPolja(vidljivostPolja);
        currState = connection;
    }
    public void setContentState(String vrsta){
        content.setVrstaElementa(vrsta);
        currState = content;
    }

    public void setDeleteState() {
        currState = delete;
    }

    public void setSelectionState() {
        currState = selection;
    }

    public void setMoveState() { currState = move;}

    public void setDuplicateState() { currState = duplicate; }
    public void setChangeState(){currState = change;}
    public  void setZoomInState(){currState = zoomIn;}
    public void setZoomOutState(){currState = zoomOut;}
    public void setZoomToFiTState(){currState = zoomToFit;}
}
