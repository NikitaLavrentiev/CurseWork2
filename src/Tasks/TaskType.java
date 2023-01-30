package Tasks;

import javax.xml.namespace.QName;

public enum TaskType {
    PRIVATE("личная"),
    WORK("рабочая");

    String name;

    TaskType(String name) {
        this.name = name;
    }
}
