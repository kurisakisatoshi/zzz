package zzz.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import zzz.domain.ZzzType;
import zzz.domain.ZzzTypeCode;

@NoArgsConstructor
@Data
public class NyanDto {

    private String name;
    private LocalDate birthday;
    private ZzzType zzzType;
    private ZzzTypeCode zzzTypeCode;
    private String nullName;
    private int zzzCode;
    private int no;
    private Double ave;
    private String note;
    private LocalDateTime now;
}
