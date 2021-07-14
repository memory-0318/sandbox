# MapStruct

在系統開發過程中，難免會遇到各類物件的轉換，如DB Entity物件轉換成領域物件 (Domain Object)或值物件 (Value Object)、領域物件、值物件轉換成
DTO物件，在物件的轉換過程中常伴隨著反覆的物件創建與設值，如果物件的欄位較多，就容易出現有些欄位設定遺漏，此時若沒有透過單元測試等手段驗證，就容易 造成在系統內部物件轉換或與外部系統溝通的傳遞過程中遺失資訊。

MapStruct可以處理上述提到的問題，簡化物件間轉換欄位映射 (mapping)過程。MapStruct採用annotation processor技術，在Java編譯時期自動生成對應
的Setter、Getter與Constructor等相關程式碼。

## Maven設定

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Note: 僅保留與MapStruct有關的設定 -->
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <mapstruct.version>1.4.2.Final</mapstruct.version>
        <lombok.version>1.18.20</lombok.version>
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
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>

                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## MapStruct基本使用

這邊以DTO與VO物件互相轉換為例，準備DTO以及VO:

*PersonDTO.java*

```java
// omit import
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class PersonDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
}
```

*PersonVO.java*

```java
// omit import
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class PersonVO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
}
```

接著撰寫物件轉換Mapper:

*PersonMapper.java*
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

接著即可直接透過`PersonMapper.INSTANCE`將DTO與VO互相轉換:

`PersonMapperTest.java`

```java
class PersonMapperTest {

    @Test
    void testToDTO() {
        PersonVO personVO = PersonVO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();
        PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(personVO);

        assertThat(personDTO)
            .extracting(PersonDTO::getFirstName, PersonDTO::getLastName, PersonDTO::getAge, PersonDTO::getGender)
            .containsExactly(personVO.getFirstName(), personVO.getLastName(), personVO.getAge(), personVO.getGender());
    }

    @Test
    void testToVO() {
        PersonDTO personDTO = PersonDTO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();
        PersonVO personVO = PersonMapper.INSTANCE.toVO(personDTO);

        assertThat(personVO)
            .extracting(PersonVO::getFirstName, PersonVO::getLastName, PersonVO::getAge, PersonVO::getGender)
            .containsExactly(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAge(),
                personDTO.getGender());
    }
}
```

## MapStruct指定欄位轉換

這邊說明物件欄位轉換時，如何使用`@Mapping`指定兩物件間的欄位對應。

*CountryDTO.java*

```java
// omit import
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class CountryDTO {
    private String countryName;
    private String countryCode;
    private CountryArea countryArea;
    private GeoPosition geoPosition;
}
```

*CountryVO.java*

```java
// omit import
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class CountryVO {
    private String name;
    private String code;
    private CountryArea area;
    private GeoPosition position;
}
```

接著撰寫物件轉換Mapper:

*CountryMapper.java*
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
target欄位則表示目標物件的欄位名稱。若一次要轉換多個欄位，可以使用`@Mappings({...})`將多個@Mapping設定包裝起來。

轉換範例:

`CountryMapperTest.java`

```java
class CountryMapperTest {

    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void testToDTO(CountryVO countryVO) {
        CountryDTO countryDTO = CountryMapper.INSTANCE.toDTO(countryVO);

        assertThat(countryDTO)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .containsExactly(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition());
    }

    @Test
    void testToVO() {
        CountryDTO countryDTO = CountryDTO.builder()
            .setCountryCode("TW")
            .setCountryName("Taiwan")
            .setCountryArea(CountryArea.builder()
                .setArea(123.0)
                .setUnit("km2")
                .build())
            .setGeoPosition(GeoPosition.builder()
                .setLongitude(121.597366d)
                .setLatitude(25.105497d)
                .build())
            .build();

        CountryVO countryVO = CountryMapper.INSTANCE.toVO(countryDTO);

        assertThat(countryVO)
            .extracting(CountryVO::getCode, CountryVO::getName, CountryVO::getArea,
                CountryVO::getPosition)
            .containsExactly(countryDTO.getCountryCode(), countryDTO.getCountryName(), countryDTO.getCountryArea(),
                countryDTO.getGeoPosition());
    }

    private static Stream<Arguments> provideVOArguments() {
        return Stream.of(
            Arguments.of(
                CountryVO.builder()
                    .setCode("TW")
                    .setName("Taiwan")
                    .setArea(CountryArea.builder()
                        .setArea(123.0)
                        .setUnit("km2")
                        .build())
                    .setPosition(GeoPosition.builder()
                        .setLongitude(121.597366d)
                        .setLatitude(25.105497d)
                        .build())
                    .build()
            )
        );
    }
}
```

## Collection類型轉換

MapStruct可以處理轉換`List`或`Set`等`Collection`型別的轉換，擴充`CountryMapper`，增加`toDTOList()`與`toDTOSet()`兩方法:

*CountryMapper.java*
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

    // 增加兩個轉換方法，分別處理List與Set的轉換
    List<CountryDTO> toDTOList(List<CountryVO> voList);

    Set<CountryDTO> toDTOSet(Set<CountryVO> voSet);
}
```

接著即可透過`CountryMapper.INSTANCE.toDTOList(...)`與`CountryMapper.INSTANCE.toDTOSet(...)`轉換物件。

`CountryMapperTest.java`:

```java
class CountryMapperTest {
    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void toDTOList(CountryVO countryVO) {
        List<CountryDTO> countryDTOs = CountryMapper.INSTANCE.toDTOList(Collections.singletonList(countryVO));

        assertThat(countryDTOs)
            .hasSize(1)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .contains(tuple(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition()));
    }

    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void toDTOSet(CountryVO countryVO) {
        Set<CountryDTO> countryDTOs = CountryMapper.INSTANCE.toDTOSet(Collections.singleton(countryVO));

        assertThat(countryDTOs)
            .hasSize(1)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .contains(tuple(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition()));
    }

    private static Stream<Arguments> provideVOArguments() {
        return Stream.of(
            Arguments.of(
                CountryVO.builder()
                    .setCode("TW")
                    .setName("Taiwan")
                    .setArea(CountryArea.builder()
                        .setArea(123.0)
                        .setUnit("km2")
                        .build())
                    .setPosition(GeoPosition.builder()
                        .setLongitude(121.597366d)
                        .setLatitude(25.105497d)
                        .build())
                    .build()
            )
        );
    }
}
```

## 多來源物件聚合

MapStruct可以將多個來源物件聚合成一個物件，這邊範例將`PersonVO`以及`CountryVO`聚合成`CompositeDTO`。

*CompositeDTO.java*

```java
// omit import
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class CompositeDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String belongingCountryName;
    private String belongingCountryCode;
    private GeoPosition countryPosition;
}
```

*CompositeMapper.java*
```java
// omit import
@Mapper
public interface CompositeMapper {
    CompositeMapper INSTANCE = Mappers.getMapper(CompositeMapper.class);

    @Mappings({
        @Mapping(source = "countryVO.name", target = "belongingCountryName"),
        @Mapping(source = "countryVO.code", target = "belongingCountryCode"),
        @Mapping(source = "countryVO.position", target = "countryPosition")
    })
    CompositeDTO toDTO(PersonVO personVO, CountryVO countryVO);
}
```

使用方式與前述範例相同:

*CompositeMapperTest.java*
```java
class CompositeMapperTest {

    @Test
    void testToDTO() {
        PersonVO personVO = PersonVO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();

        CountryVO countryVO = CountryVO.builder()
            .setName("Taiwan")
            .setCode("TW")
            .setArea(CountryArea.builder()
                .setArea(123.0)
                .setUnit("km2")
                .build())
            .setPosition(GeoPosition.builder()
                .setLongitude(121.597366d)
                .setLatitude(25.105497d)
                .build())
            .build();

        CompositeDTO compositeDTO = CompositeMapper.INSTANCE.toDTO(personVO, countryVO);
        assertThat(compositeDTO)
            .extracting(CompositeDTO::getFirstName, CompositeDTO::getLastName, CompositeDTO::getAge,
                CompositeDTO::getBelongingCountryName, CompositeDTO::getBelongingCountryCode,
                CompositeDTO::getCountryPosition)
            .containsExactly(personVO.getFirstName(), personVO.getLastName(), personVO.getAge(), countryVO.getName(),
                countryVO.getCode(), countryVO.getPosition());
    }
}
```