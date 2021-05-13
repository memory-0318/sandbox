# MapStruct

MapStruct可以有效處理物件間轉換的屬性設定，避免重複撰寫setter方法設值

## Maven設定

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Note: 僅保留與MapStruct有關的設定 -->
    <properties>
        <mapstruct.version>1.4.2.Final</mapstruct.version>
        <lombok.version>1.18.12</lombok.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source> <!-- depending on your project -->
                    <target>1.8</target> <!-- depending on your project -->
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                        <!-- other annotation processors -->
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## MapStruct基本使用說明

這邊以DTO與VO物件互相轉換為例，準備DTO以及VO

*PersonDTO*

```java
// omit import
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class PersonDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
}
```

*PersonVO*

```java
// omit import
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class PersonVO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
}
```

接著撰寫物件轉換Mapper

```java
// omit import
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(PersonVO personVO);

    PersonVO toVO(PersonDTO personDTO);
}
```

因為有引入`org.mapstruct.mapstruct-processor`，所以會自動生成轉換程式碼。

接著即可直接透過`PersonMapper.INSTANCE`將DTO與VO互相轉換。

## MapStruct進階使用說明

這邊說明物件欄位轉換時，如何使用`@Mapping`指定兩物件間的欄位對應。
*CountryDTO*

```java
// omit import
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class CountryDTO {
    private String countryName;
    private String countryCode;
    private CountryArea countryArea;
    private GeoPosition geoPosition;
}
```

*CountryVO*

```java
// omit import
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class CountryVO {
    private String name;
    private String code;
    private CountryArea area;
    private GeoPosition position;
}
```

接著撰寫物件轉換Mapper

*CountryMapper*

```java

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mappings({
        @Mapping(target = "name", source = "dto.countryName"),
        @Mapping(target = "code", source = "dto.countryCode"),
        @Mapping(target = "area", source = "dto.countryArea"),
        @Mapping(target = "position", source = "dto.geoPosition")
    })
    CountryVO toVO(CountryDTO dto);

    @Mappings({
        @Mapping(target = "countryName", source = "vo.name"),
        @Mapping(target = "countryCode", source = "vo.code"),
        @Mapping(target = "countryArea", source = "vo.area"),
        @Mapping(target = "geoPosition", source = "vo.position")
    })
    CountryDTO toDTO(CountryVO vo);
}
```

使用`@Mapping(source=<source field name>, target=<target field name>)`指定兩物件的欄位轉換，其中source欄位表示來源物件的欄位名稱，
target欄位則表示目標物件的欄位名稱。若一次要轉換多個欄位，則使用`@Mappings({...})`將多個@Mapping設定包裝起來。