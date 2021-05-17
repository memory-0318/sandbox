package com.demo;

import org.apache.commons.lang3.StringUtils;
import org.osgeo.proj4j.*;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/17
 */
public final class CoordinateTransformer {
    private static final Pattern EPSG_PATTERN = Pattern.compile("^(EPSG:)[0-9]+");

    private final String sourceCrsCode;
    private final String targetCrsCode;
    private final CoordinateTransform coordinateTransform;

    public CoordinateTransformer(String sourceCrsCode, String targetCrsCode) {
        this.checkSourceCrsCode(sourceCrsCode);
        this.checkTargetCrsCode(targetCrsCode);
        this.sourceCrsCode = sourceCrsCode;
        this.targetCrsCode = targetCrsCode;

        CoordinateTransformFactory coordinateTransformFactory = new CoordinateTransformFactory();
        CRSFactory crsFactory = new CRSFactory();

        CoordinateReferenceSystem sourceCrs = crsFactory.createFromName(sourceCrsCode);
        CoordinateReferenceSystem targetCrs = crsFactory.createFromName(targetCrsCode);

        this.coordinateTransform = coordinateTransformFactory.createTransform(sourceCrs, targetCrs);
    }

    public Coordinate transform(Coordinate coordinate) {
        this.checkCoordinate(coordinate);

        ProjCoordinate sourceProjCoor = new ProjCoordinate(coordinate.getX(), coordinate.getY());
        ProjCoordinate targetProjCoor = new ProjCoordinate();
        targetProjCoor = this.coordinateTransform.transform(sourceProjCoor, targetProjCoor);

        return Coordinate.builder()
            .setX(targetProjCoor.x)
            .setY(targetProjCoor.y)
            .setZ(targetProjCoor.z)
            .build();
    }

    private void checkSourceCrsCode(String sourceCrsCode) {
        if (StringUtils.isBlank(sourceCrsCode)) {
            throw new IllegalArgumentException("來源CRS編碼不可為空");
        }
        this.checkCrsCodePattern(sourceCrsCode, "來源CRS編碼不符格式");
    }

    private void checkTargetCrsCode(String targetCrsCode) {
        if (StringUtils.isBlank(targetCrsCode)) {
            throw new IllegalArgumentException("目標CRS編碼不可為空");
        }
        this.checkCrsCodePattern(targetCrsCode, "目標CRS編碼不符格式");
    }

    private void checkCrsCodePattern(String crsCode, String errorMsg) {
        if (!EPSG_PATTERN.matcher(crsCode).matches()) {
            throw new IllegalArgumentException(StringUtils.defaultIfBlank(errorMsg, "輸入CRS編碼為空"));
        }
    }

    private void checkCoordinate(Coordinate coordinate) {
        Objects.requireNonNull(coordinate, "Coordinate不可為null");
        Objects.requireNonNull(coordinate.getX(), "x坐標不可為null");
        Objects.requireNonNull(coordinate.getY(), "y坐標不可為null");
    }

    public String getSourceCrsCode() {
        return sourceCrsCode;
    }

    public String getTargetCrsCode() {
        return targetCrsCode;
    }
}
