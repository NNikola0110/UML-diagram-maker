package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ClassyRepository;

import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;
@Getter
@Setter

public class ClassyRepositoryImplementation implements ClassyRepository {

    private ProjectExplorer root;

    public ClassyRepositoryImplementation() {
        root = new ProjectExplorer("My Project Explorer");
    }

    /*  public ClassyRepositoryImplementation(ProjectExplorer root) {
          this.root = root;
      }*/


    @Override
    public ProjectExplorer getRoot() {
        return root;
    }
}
