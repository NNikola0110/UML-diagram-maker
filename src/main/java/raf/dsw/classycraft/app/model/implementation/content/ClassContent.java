package raf.dsw.classycraft.app.model.implementation.content;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@Getter
@Setter
public class ClassContent implements Cloneable{
    private String name;
    private int pomeraj;
    //private Font font;

    public ClassContent(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassContent clonedInterclass = (ClassContent) super.clone();
        return clonedInterclass;
    }
}
