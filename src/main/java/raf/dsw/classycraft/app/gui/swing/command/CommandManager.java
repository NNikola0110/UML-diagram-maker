package raf.dsw.classycraft.app.gui.swing.command;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends AbstractCommand {

    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        while (currentCommand<commands.size()){
            commands.remove(currentCommand);
        }
        commands.add(command);
        doCommand();
    }

    @Override
    public void doCommand() {
        if(currentCommand<commands.size()){
            commands.get(currentCommand++).doCommand();

        }
    }

    @Override
    public void undoCommand() {
        if(currentCommand>0){
            commands.get(currentCommand--).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
        }
    }
}
