package raf.dsw.classycraft.app.model.implementation.content;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;

@Getter
@Setter

public class Atribute extends ClassContent {

    private String vidljivost;
    private String tip;
    public Atribute(String name,String vidljivost, String tip) {
        super(name);
        this.vidljivost=vidljivost;
        this.tip=tip;
    }

    @Override
    public String toString() {
        String v="";
        if (vidljivost.equals("public")) v = "+";
        else if (vidljivost.equals("private")) v = "-";
        else if (vidljivost.equals("protected")) v = "#";

        return  v +" "+ super.getName() + ": "+ tip ;
    }

}
