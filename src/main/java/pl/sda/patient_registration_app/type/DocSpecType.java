package pl.sda.patient_registration_app.type;

import java.util.Arrays;

public enum DocSpecType {

    GYNECOLOGIST("ginekolog"),
    UROLOGIST("urolog"),
    DENTIST("dentysta"),
    HEMATOLOGIST("hematolog");

    private String name;

    DocSpecType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DocSpecType findByName(String name){
        DocSpecType[] values = DocSpecType.values();
        return Arrays.asList(values).stream()
                .filter(t -> t.getName().equals(name))
                .findFirst().get();
    }
}
