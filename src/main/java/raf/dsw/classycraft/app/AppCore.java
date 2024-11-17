package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.core.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.loger.LoggerFactory;
import raf.dsw.classycraft.app.message.MessageGenerator;
import raf.dsw.classycraft.app.message.MessageGeneratorImplementation;

public class AppCore {
    public static void main(String[] args) {

        ApplicationFramework appCore =ApplicationFramework.getInstance();
       // Gui gui =new SwingGui();
        MessageGenerator messageGenerator = new MessageGeneratorImplementation();
        //LoggerFactory loggerFactory=new LoggerFactory(messageGenerator);
        ClassyRepository classyRepository = new ClassyRepositoryImplementation();
        appCore.initialize(classyRepository, messageGenerator);


    }
}
