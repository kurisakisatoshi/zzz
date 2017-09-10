package zzz.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getValue() {
        return name;
    }
}
