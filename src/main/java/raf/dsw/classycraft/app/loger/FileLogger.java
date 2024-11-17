package raf.dsw.classycraft.app.loger;


import raf.dsw.classycraft.app.message.Message;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileLogger implements Logger {


    @Override
    public void update(Object notification) {
      loger(notification.toString());
    }


    @Override
    public void loger(String error) {
        File file= new File(getClass().getResource("/file/Log.txt").getFile());
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(error+"\n");
            bw.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
