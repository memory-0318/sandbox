package tw.brian.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.google.common.collect.Maps.difference;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/16
 */
@SpringBootTest
class JsonComparisonTest {
    @Autowired
    private ObjectMapper objectMapper;

    private Person basePersonModel = Person.newBuilder()
        .setFirstName("Brian")
        .setLastName("Su")
        .setAge(30)
        .build();

    @Test
    void testSimpleModelByGuavaDifference() throws JsonProcessingException {
        Person copiedModel = Person.newBuilder(this.basePersonModel)
            .build();
        String rawModelJson = this.objectMapper.writeValueAsString(this.basePersonModel);
        String copiedModelJson = this.objectMapper.writeValueAsString(copiedModel);
        Map<String, Object> rawModelMap = this.objectMapper.readValue(rawModelJson, Map.class);
        Map<String, Object> copiedModelMap = this.objectMapper.readValue(copiedModelJson, Map.class);

        MapDifference<String, Object> diff = Maps.difference(rawModelMap, copiedModelMap);

        Assertions.assertTrue(diff.areEqual());
    }
}