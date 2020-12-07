package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point start  = new Point(3, 5);
        Point end  = new Point(8, 9);
        Assert.assertEquals(start.distance(end), 3);
    }

    @Test
    public void testDistance2() {
        Point start  = new Point(3, 0);
        Point end  = new Point(0, 9);
        Assert.assertEquals(start.distance(end), 2.449489742783178);
    }

    @Test
    public void testDistance3() {
        Point start  = new Point(0, 0);
        Point end  = new Point(0, 0);
        Assert.assertEquals(start.distance(end), 0);
    }

    @Test
    public void testDistance4() {
        Point start  = new Point(0, 0);
        Point end  = new Point(0, 9);
        Assert.assertEquals(start.distance(end), 3);
    }

    @Test
    public void testDistance5() {
        Point start  = new Point(1, 0);
        Point end  = new Point(0, 1);
        Assert.assertEquals(start.distance(end), 0);
    }
}

