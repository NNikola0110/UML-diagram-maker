package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.Package;
import raf.dsw.classycraft.app.model.implementation.Project;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;

import javax.swing.*;
import java.util.Random;

public class FactoryUtiles {

    public static ClassyNodeFactory getFactory(ClassyNode classyNode){
     if(classyNode instanceof ProjectExplorer){
         return  new ProjectExplorerFactory("Project" , classyNode);
    } else if (classyNode instanceof Project) {
        return  new ProjectFactory("Package", classyNode);
    } else if (classyNode instanceof Package){
         Object[] obj={"diagram","paket"};
         int s = JOptionPane.showOptionDialog(null, "Izaberi", "Izaberi!",
                 0, 3, null, obj, obj[0]);


          if(s==1)
             return  new ProjectFactory("Package", classyNode);
         else if(s==0) {
              return new PackageFactory("Diagram", classyNode);
          }


    }
        return null;
    }
}
