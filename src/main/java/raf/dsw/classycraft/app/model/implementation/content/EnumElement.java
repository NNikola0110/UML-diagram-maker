package raf.dsw.classycraft.app.model.implementation.content;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.implementation.content.ClassContent;

@Getter
@Setter
public class EnumElement extends ClassContent {
    public EnumElement(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
