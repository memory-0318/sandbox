# Proj4j

WGS84與TWD97坐標系統間的轉換，詳細範例請參考`CoordinateTransformerTest.java`

## Maven設定

```xml

<project>
    <!-- other settings -->
    <dependencies>
        <!-- other dependencies... -->
        <!-- 引入Proj4j依賴 -->
        <dependency>
            <groupId>org.osgeo</groupId>
            <artifactId>proj4j</artifactId>
            <version>${proj4j.version}</version>
        </dependency>
    </dependencies>
</project>
```

## `CoordinateTransformer`簡易使用範例

`CoordinateTransformer`將Proj4j的坐標轉換細節包裝起來，提供更簡便的操作
*Main.java*

```java
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
```