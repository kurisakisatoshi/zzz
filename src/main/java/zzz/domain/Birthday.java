package zzz.domain;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Birthday {

    private final LocalDate birthday;

    private Birthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static Birthday of(LocalDate birthday) {
        return new Birthday(birthday);
    }

    public LocalDate getValue() {
        return birthday;
    }
}
