package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;

public interface ClassyRepository {
     public ProjectExplorer getRoot();
}