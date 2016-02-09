package com.galadar.flyergame;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Galadar on 9/2/2016.
 * List of points to create path
 */

public class PointList extends ArrayList<Point>{

    public PointList() {
    }

    private Point first() {
        Iterator it = this.iterator();
        Point min, temp;
        min = PointList.this.get(0);
        if(min!=null) {
            while (it.hasNext()) {
                temp = (Point) it.next();
                if (temp.x < min.x) min = temp;
            }
            //PointList.this.remove(min);
            return min;
        } else return null;
    }

    public Point getfirst() {
        Iterator it = this.iterator();
        Point min, temp;
        min = PointList.this.get(0);
        if(min!=null) {
            while (it.hasNext()) {
                temp = (Point) it.next();
                if (temp.x < min.x) min = temp;
            }
            PointList.this.remove(min);
            return min;
        } else return null;
    }

    private Point last() {
        Iterator it = this.iterator();
        Point min, temp;
        min = PointList.this.get(0);
        if(min!=null) {
            while (it.hasNext()) {
                temp = (Point) it.next();
                if (temp.x > min.x) min = temp;
            }
            //PointList.this.remove(min);
            return min;
        } else return null;
    }

    public Point getlast() {
        Iterator it = this.iterator();
        Point min, temp;
        min = PointList.this.get(0);
        if(min!=null) {
            while (it.hasNext()) {
                temp = (Point) it.next();
                if (temp.x > min.x) min = temp;
            }
            PointList.this.remove(min);
            return min;
        } else return null;
    }

    public boolean addPoint(Point p){
        Point max = this.last();
        if(max==null) return this.add(p);
        if(max.x<p.x) return false;
        else return this.add(p);
    }

}
