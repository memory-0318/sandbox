package com.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/17
 */
class CoordinateTransformerTest {
    @ParameterizedTest
    @MethodSource("provideBlankCrsCodeArgs")
    void testCheckSourceCrsCode_whenCrsCodeIsBlank(String sourceCrsCode) {
        assertThatThrownBy(() -> new CoordinateTransformer(sourceCrsCode, ""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("來源CRS編碼不可為空");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCrsCodeArgs")
    void testCheckSourceCrsCode_whenCrsCodeIsNotValid(String sourceCrsCode) {
        assertThatThrownBy(() -> new CoordinateTransformer(sourceCrsCode, ""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("來源CRS編碼不符格式");
    }

    @ParameterizedTest
    @MethodSource("provideBlankCrsCodeArgs")
    void testCheckTargetCrsCode_whenCrsCodeIsBlank(String targetCrsCode) {
        assertThatThrownBy(() -> new CoordinateTransformer("EPSG:4326", targetCrsCode))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("目標CRS編碼不可為空");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCrsCodeArgs")
    void testCheckTargetCrsCode_whenCrsCodeIsNotValid(String targetCrsCode) {
        assertThatThrownBy(() -> new CoordinateTransformer("EPSG:4326", targetCrsCode))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("目標CRS編碼不符格式");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCoordinateArgs")
    void testTransform_whenCoordinateIsInvalid(Coordinate coordinate, String errorMsg) {
        CoordinateTransformer coordinateTransformer = new CoordinateTransformer("EPSG:4326", "EPSG:3857");
        assertThatThrownBy(() -> coordinateTransformer.transform(coordinate))
            .isInstanceOf(NullPointerException.class)
            .hasMessage(errorMsg);
    }

    @ParameterizedTest
    @MethodSource("provideValidCoordinateArgs")
    void testTransform_whenCoordinateIsValid(Coordinate wgs84Coordinate) {
        String crsCodeWgs84 = "EPSG:4326";
        String crsCodeTwd97 = "EPSG:3857";

        CoordinateTransformer wgs84ToTwd97Transformer = new CoordinateTransformer(crsCodeWgs84, crsCodeTwd97);
        CoordinateTransformer twd97ToWgs84Transformer = new CoordinateTransformer(crsCodeTwd97, crsCodeWgs84);

        final double diffThreshold = 1e-6d;
        assertThat(wgs84Coordinate)
            .matches(coordinate -> {
                Coordinate twd97Coordinate = wgs84ToTwd97Transformer.transform(coordinate);
                Coordinate result = twd97ToWgs84Transformer.transform(twd97Coordinate);

                return Math.abs(result.getX() - coordinate.getX()) < diffThreshold
                    && Math.abs(result.getY() - coordinate.getY()) < diffThreshold;
            });
    }

    private static Stream<Arguments> provideBlankCrsCodeArgs() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("     "),
            null
        );
    }

    private static Stream<Arguments> provideInvalidCrsCodeArgs() {
        return Stream.of(
            Arguments.of("EPSG:"),
            Arguments.of("ESPG:"),
            Arguments.of("EPSG"),
            Arguments.of("EPSG:abc"),
            Arguments.of("epsg:123")
        );
    }

    private static Stream<Arguments> provideInvalidCoordinateArgs() {
        return Stream.of(
            Arguments.of(null, "Coordinate不可為null"),
            Arguments.of(
                Coordinate.builder()
                    .setY(0d)
                    .build(),
                "x坐標不可為null"
            ),
            Arguments.of(
                Coordinate.builder()
                    .setX(0d)
                    .build(),
                "y坐標不可為null"
            )
        );
    }

    private static Stream<Arguments> provideValidCoordinateArgs() {
        return Stream.of(
            Arguments.of(
                Coordinate.builder()
                    .setX(121.514269d)
                    .setY(25.0396344d)
                    .build()
            ),
            Arguments.of(
                Coordinate.builder()
                    .setX(121.0054436d)
                    .setY(24.7994615d)
                    .build()
            ),
            Arguments.of(
                Coordinate.builder()
                    .setX(120.2161998d)
                    .setY(22.9983159d)
                    .build()
            ),
            Arguments.of(
                Coordinate.builder()
                    .setX(120.6582958d)
                    .setY(24.1348041d)
                    .build()
            )
        );
    }
}