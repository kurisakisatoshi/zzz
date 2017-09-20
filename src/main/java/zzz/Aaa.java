package zzz;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import zzz.domain.Birthday;
import zzz.entity.Nyan;

public class Aaa {

    static final String JSON =
            "{"
                + "\"name\":" + "\"aaa\","
                + "\"birthday\":" + "\"2017/09/18\","
                + "\"zzzType\":" + "\"XXX\","
                //+ "\"zzzCode\":" + "\"bbb\","
                + "\"zzzTypeCode\":" + "0,"
                + "\"note\":" + "\"bbb\","
                + "\"no\":" + "10"
            + "}";

    public static void main(String[] args) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        module.addDeserializer(LocalDate.class, localDateDeserializer);
        module.addDeserializer(Birthday.class, new StdDeserializer<Birthday>(Birthday.class) {
            @Override
            public Birthday deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                LocalDate localDate = localDateDeserializer.deserialize(p, ctxt);
                return Birthday.of(localDate);
            }
        });
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        System.out.println(mapper.readValue(JSON, Nyan.class));
    }
}
