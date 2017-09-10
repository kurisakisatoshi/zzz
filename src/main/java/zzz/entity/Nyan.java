package zzz.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zzz.domain.Birthday;
import zzz.domain.Name;
import zzz.domain.ZzzType;
import zzz.domain.ZzzTypeCode;

@Data
@AllArgsConstructor
@Builder
public class Nyan {

    private final Name name;
    private final Birthday birthday;
    private final ZzzType zzzType;
    private final ZzzTypeCode zzzTypeCode;
    private final ZzzTypeCode zzzCode;
    private final Name nullName;
    private final int no;
    private final Double ave;
    private final String note;
    private final Object nothing;
    private final LocalDateTime now;
}
