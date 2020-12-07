package ru.stqa.pft.sandbox;

public class Point{
    public double start;
    public double end;

    public static void main(String[] args) {
        Point start = new Point(1, 2);
        Point end = new Point(3, 4);
        double d = start.distance(end);
        System.out.println(d);
    }

    public Point(double start, double end){
        this.start = start;
        this.end = end;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.start - this.start) + (p2.end - this.end));
    }
}
