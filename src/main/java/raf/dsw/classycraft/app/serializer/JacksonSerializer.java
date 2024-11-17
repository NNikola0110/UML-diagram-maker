package raf.dsw.classycraft.app.serializer;

//import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.model.implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer{

      //  private final ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public Project loadProject(File file) {
      return null;
    }

    @Override
    public void saveProject(Project node) {
        try (FileWriter writer = new FileWriter(String.valueOf(node.getProjectFile()))){
      //      objectMapper.writeValue(writer,node);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
