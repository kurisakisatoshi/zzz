package zzz.domain;

import lombok.Value;

@Value//(staticConstructor="of")
//@ToString
//@EqualsAndHashCode
public class Name implements ValueObject<String> {

    private final String value;

    private Name(String name) {
        this.value = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    public String getValue() {
        return value;
    }
}
