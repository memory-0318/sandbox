package com.demo;

import com.demo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/30
 */
public class AssertjBasicDemoTest {
    private Person person;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testBasicAssertjUsage() {
        String firstName = "Brian";
        String lastName = "Su";
        Integer age = 30;

        Person person = Person.builder()
            .setFirstName(firstName)
            .setLastName(lastName)
            .setAge(age)
            .build();

        // 比對物件是否相同
        assertThat(
            Person.builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAge(age)
                .build())
            .isEqualTo(person);

        // 比對物件指定欄位是否相同
        assertThat(person)
            .extracting(Person::getFirstName, Person::getLastName)
            .contains(firstName, lastName);
    }

    @Test
    void testAssertList() {
        List<Person> persons = Arrays.asList(
            Person.builder()
                .setFirstName("Brian")
                .setLastName("Su")
                .setAge(30)
                .build(),
            Person.builder()
                .setFirstName("Samson")
                .setLastName("Zheng")
                .setAge(28)
                .build()
        );

        assertThat(persons)
            .hasSize(2)
            .extracting(Person::getFirstName, Person::getLastName)
            .contains(tuple("Brian", "Su"), atIndex(0));
    }
}
