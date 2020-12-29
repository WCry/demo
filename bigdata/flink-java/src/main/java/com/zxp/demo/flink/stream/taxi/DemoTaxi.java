package com.zxp.demo.flink.stream.taxi;

import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author zhangxuepei
 * @since 3.0
 * 出租车的GPS 点位数据运算
 * GPS 点位数据存在突然很大情况
 */
public class DemoTaxi {
    private static final String dataFormatter = "yyyy-MM-dd HH:mm:ss";
    private static Geometry geometryUnion;

    public static void main(String[] args) throws IOException {
        String fileName = "D:\\文档\\taxi\\Taxi_070220\\Taxi_105";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fileReader);
        String taxiLine = bf.readLine();
        GeometryFactory geometryFactory = new GeometryFactory();
        String[] taxiContent = taxiLine.split(",");
        Point prePoint = getPoint(taxiContent, geometryFactory);
        LinkedList<Coordinate> coordinateLinkedList = new LinkedList<>();
        LocalDateTime preLocalDatTime = LocalDateTime.parse(taxiContent[1], DateTimeFormatter.ofPattern(dataFormatter));
        Long maxSeconds = 0L;
        int i = 0;
        int maxLine = 0;
        while (true) {
            i++;
            taxiLine = bf.readLine();
            if (StringUtils.isEmpty(taxiLine)) {
                break;
            }
            LocalDateTime currentLocalDatTime = LocalDateTime.parse(taxiContent[1], DateTimeFormatter.ofPattern(dataFormatter));
            Long currSeconds = preLocalDatTime.until(currentLocalDatTime, ChronoUnit.MILLIS);
            if (currSeconds > maxSeconds) {
                maxSeconds = currSeconds;
                maxLine = i;
            }
            if (currSeconds > 60000L) {
                printLinString(geometryFactory, coordinateLinkedList);
                coordinateLinkedList = new LinkedList<>();
            }
            preLocalDatTime = currentLocalDatTime;
            taxiContent = taxiLine.split(",");
            Coordinate coordinate = getCoordinate(taxiContent);
            Point currentPoint = geometryFactory.createPoint(coordinate);
            //geometry = geometry.union(currentPoint);
            if (!prePoint.equals(currentPoint)) {
                coordinateLinkedList.add(coordinate);
            }
            prePoint = currentPoint;
        }
        printLinString(geometryFactory, coordinateLinkedList);
        WKTWriter wktWriter=new WKTWriter();
        System.out.println(wktWriter.write(geometryUnion));
        System.out.println(maxLine);
        System.out.println(maxSeconds + "最大时间延迟");
    }

    private static Coordinate getCoordinate(String[] taxiContent) {
        return new Coordinate(Double.parseDouble(taxiContent[2]), Double.parseDouble(taxiContent[3]));
    }

    private static Point getPoint(String[] taxiContent, GeometryFactory geometryFactory) {
        Coordinate coordinate = new Coordinate(Double.parseDouble(taxiContent[2]), Double.parseDouble(taxiContent[3]));
        Point point = geometryFactory.createPoint(coordinate);
        return point;
    }

    private static void printLinString(GeometryFactory geometryFactory, LinkedList<Coordinate> coordinateLinkedList) {
        if (coordinateLinkedList.size() > 2) {
            LineString lineString = geometryFactory.createLineString(coordinateLinkedList.toArray(new Coordinate[0]));
            if (Objects.isNull(geometryUnion)) {
                geometryUnion = lineString;
            } else {
                geometryUnion = geometryUnion.union(lineString);
            }
            WKTWriter wktWriter = new WKTWriter();
            System.out.println(wktWriter.write(lineString));
        }
    }
}
