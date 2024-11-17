package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.model.implementation.Project;

import java.io.File;

public interface Serializer {
    Project loadProject (File file);
    void saveProject (Project node);
}
