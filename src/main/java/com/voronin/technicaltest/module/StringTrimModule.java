package com.voronin.technicaltest.module;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type String trim module.
 */
@Component
public class StringTrimModule extends SimpleModule {

    /**
     * Instantiates a new String trim module.
     */
    public StringTrimModule() {
        addDeserializer(String.class, new StdScalarDeserializer<>(String.class) {
            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
                return jsonParser.getValueAsString().trim();
            }
        });
    }
}
