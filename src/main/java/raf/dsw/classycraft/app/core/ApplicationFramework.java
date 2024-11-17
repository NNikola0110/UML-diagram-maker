package raf.dsw.classycraft.app.core;

//import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.loger.ConsoleLogger;
import raf.dsw.classycraft.app.loger.FileLogger;
import raf.dsw.classycraft.app.loger.LoggerFactory;
import raf.dsw.classycraft.app.message.MessageGenerator;
import raf.dsw.classycraft.app.message.MessageGeneratorImplementation;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.serializer.JacksonSerializer;
import raf.dsw.classycraft.app.serializer.Serializer;

@Getter
@Setter

public class ApplicationFramework {

    private MessageGenerator messageGenerator;
    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;
    private ClassyRepository classyRepository;
    private LoggerFactory loggerFactory;
   // private ClassyRepositoryImplementation classyRepositoryImplementation;
    private static ApplicationFramework instance;
    private Serializer serializer;

    private ApplicationFramework(){

    }

    public void initialize(ClassyRepository classyRepository,MessageGenerator messageGenerator ){
        this.messageGenerator=new MessageGeneratorImplementation();
        this.classyRepository = new ClassyRepositoryImplementation();
        this.serializer=new JacksonSerializer();
        MainFrame.getInstance().setVisible(true);
        LoggerFactory loggerFactory = new LoggerFactory(messageGenerator);
        loggerFactory.createLogger("console");
        loggerFactory.createLogger("file");



    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

}
