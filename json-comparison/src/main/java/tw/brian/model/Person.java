package tw.brian.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;

    private Person(Builder builder) {
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setAge(builder.age);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Person copy) {
        Builder builder = new Builder();
        builder.firstName = copy.getFirstName();
        builder.lastName = copy.getLastName();
        builder.age = copy.getAge();
        return builder;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private Integer age;

        private Builder() {}

        public Builder setFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder setLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder setAge(Integer val) {
            age = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
