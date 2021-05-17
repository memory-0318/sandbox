package com.demo;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/17
 */
public class Main {
    public static void main(String[] args) {
        CoordinateTransformer coordinateTransformer = new CoordinateTransformer("EPSG:4326", "EPSG:3857");
        // 總統府的經緯度坐標
        Coordinate wgs84Coordinate = Coordinate.builder()
            .setX(121.514269d)
            .setY(25.0396344d)
            .build();

        // 將WGS84坐標轉換成TWD97系統的坐標
        Coordinate twd97Coordinate = coordinateTransformer.transform(wgs84Coordinate);
        System.out.printf("總統府坐標 (WGS84 or EPSG:4326): %s%n", wgs84Coordinate);
        System.out.printf("總統府坐標 (TWD97 or EPSG:3857): %s%n", twd97Coordinate);
    }
}
