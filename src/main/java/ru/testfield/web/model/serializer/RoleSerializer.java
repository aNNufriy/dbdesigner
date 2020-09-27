package ru.testfield.web.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.testfield.web.model.cms.Role;

import java.io.IOException;
import java.util.Set;

/**
 * Created by J.Bgood on 12/3/17.
 */
public class RoleSerializer extends JsonSerializer<Set<Role>> {
    @Override
    public void serialize(Set<Role> roles, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for(Role role: roles){
            jgen.writeString(role.getRolename());
        }
        jgen.writeEndArray();
    }
}