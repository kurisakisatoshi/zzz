package zzz_e2d;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import zzz.Entity2DtoConverter;
import zzz.domain.Birthday;
import zzz.domain.Name;
import zzz.domain.ZzzType;
import zzz.domain.ZzzTypeCode;
import zzz.dto.NyanDto;
import zzz.entity.Nyan;

@RunWith(JUnit4.class)
public class Entity2ConverterTest {

    @Test
    public void test() {

        Nyan nya = Nyan.builder()
                        .name(Name.of("にゃーん"))
                        .birthday(Birthday.of(LocalDate.of(2017, 9, 10)))
                        .zzzType(ZzzType.YYY)
                        .zzzTypeCode(ZzzTypeCode.AAA)
                        .zzzCode(ZzzTypeCode.BBB)
                        .no(3)
                        .ave(3.85)
                        .note("Memo")
                        .nothing(new Object())
                        .now(LocalDateTime.now())
                        .build();

        NyanDto dto =
                Entity2DtoConverter.<Nyan, NyanDto>converter()
                    .from(nya)
                    .to(NyanDto.class)
                    .convert();

        System.out.println(dto);

        assertThat(dto.getName(), is(nya.getName().getValue()));
        assertThat(dto.getBirthday(), is(nya.getBirthday().getValue()));
        assertThat(dto.getZzzType(), is(nya.getZzzType()));
        assertThat(dto.getZzzTypeCode(), is(nya.getZzzTypeCode()));
        assertThat(dto.getZzzCode(), is(nya.getZzzCode().getValue()));
        assertThat(dto.getNullName(), is(nya.getNullName()));
        assertThat(dto.getNo(), is(nya.getNo()));
        assertThat(dto.getAve(), is(nya.getAve()));
        assertThat(dto.getNote(), is(nya.getNote()));
        assertThat(dto.getNow(), is(nya.getNow()));
    }

    @Test
    public void test_list() {

        Nyan nya1 = Nyan.builder()
                        .name(Name.of("にゃーん"))
                        .birthday(Birthday.of(LocalDate.of(2017, 9, 10)))
                        .zzzType(ZzzType.YYY)
                        .zzzTypeCode(ZzzTypeCode.AAA)
                        .zzzCode(ZzzTypeCode.BBB)
                        .no(3)
                        .ave(3.85)
                        .note("Memo")
                        .nothing(new Object())
                        .now(LocalDateTime.now())
                        .build();

        Nyan nya2 = Nyan.builder()
                .name(Name.of("にゃーん"))
                .birthday(Birthday.of(LocalDate.of(2017, 9, 10)))
                .zzzType(ZzzType.YYY)
                .zzzTypeCode(ZzzTypeCode.AAA)
                .zzzCode(ZzzTypeCode.BBB)
                .no(3)
                .ave(3.85)
                .note("Memo")
                .nothing(new Object())
                .now(LocalDateTime.now())
                .build();

        List<Nyan> nyanList = Arrays.asList(nya1, nya2);

        List<NyanDto> dtoList =
                Entity2DtoConverter.<Nyan, NyanDto>listConverter()
                    .from(nyanList)
                    .to(NyanDto.class)
                    .convert();

        System.out.println(dtoList);

        for (int i=0; i<dtoList.size(); i++) {

            NyanDto dto = dtoList.get(i);
            Nyan nya = nyanList.get(i);

            assertThat(dto.getName(), is(nya.getName().getValue()));
            assertThat(dto.getBirthday(), is(nya.getBirthday().getValue()));
            assertThat(dto.getZzzType(), is(nya.getZzzType()));
            assertThat(dto.getZzzTypeCode(), is(nya.getZzzTypeCode()));
            assertThat(dto.getZzzCode(), is(nya.getZzzCode().getValue()));
            assertThat(dto.getNullName(), is(nya.getNullName()));
            assertThat(dto.getNo(), is(nya.getNo()));
            assertThat(dto.getAve(), is(nya.getAve()));
            assertThat(dto.getNote(), is(nya.getNote()));
            assertThat(dto.getNow(), is(nya.getNow()));
        }
    }
}
