package zzz.domain;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
//@JsonDeserialize(as = LocalDate.class)
public class Birthday implements ValueObject<LocalDate> {

    private final LocalDate birthday;

    private Birthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static Birthday of(LocalDate birthday) {
        return new Birthday(birthday);
    }

    @Override
    public LocalDate getValue() {
        return birthday;
    }
}
