package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.message.Message;
import raf.dsw.classycraft.app.message.MessageGenerator;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;
    private ActionManager actionManager;
    private ClassyTree classyTree;
    private MessageGenerator messageGenerator;
    private PackageView packageView;


    private MainFrame(){

    }

    private void initialize(){
        classyTree=new ClassyTreeImplementation();
       actionManager=new ActionManager();
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);

        packageView=new PackageView();

        initializeGUI();
    }

    private void initializeGUI(){
        Toolkit kit =Toolkit.getDefaultToolkit();
        Dimension screenSize =kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int) (screenWidth/1.4), (int) (screenHeight/1.4));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        PackageViewToolbar pvToolBar = new PackageViewToolbar();
        add(pvToolBar,BorderLayout.EAST);

        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JPanel right = packageView;





        //dodavanje prozora i Linije za splitovanje ta dva dela
        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,200));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,right);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);


    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

   /* public void reload(PackageView pw){
        packageView = pw;
    }*/
    @Override
    public void update(Object notification) {

        Message msg = (Message) notification;
        for (MessagesType eventType : MessagesType.values()) {
             if (eventType.equals(msg.getMessagesType())) {
                 if(msg.getMessagesType().equals(MessagesType.ERROR)){
                     JOptionPane.showMessageDialog(this, msg.getText(), msg.getMessagesType().toString(), JOptionPane.ERROR_MESSAGE);
                 }
                 else if(msg.getMessagesType().equals(MessagesType.NODE_CANNOT_BE_DELETED)){
                     JOptionPane.showMessageDialog(this, msg.getText(), msg.getMessagesType().toString(), JOptionPane.ERROR_MESSAGE);
                 }else
                JOptionPane.showMessageDialog(this, msg.getText(), msg.getMessagesType().toString(), JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
