package com.galadar.flyergame;

import android.graphics.Path;

import java.util.ArrayList;

/**
 * Created by Galadar on 9/2/2016.
 */
public class PointPath extends Path {

    public class GraphP{
        private float x,y;

        public GraphP(float dx, float dy){
            this.x=dx;
            this.y=dy;
        }

        public float getPosition(){
            return this.x;
        }

        public float getHeight(){
            return this.y;
        }

        public float getAngle(){
            return getHeight()/getPosition();
        }
    }

    ArrayList<GraphP> list;

    public PointPath() {
        super();
        list = new ArrayList<>();
        list.add(new GraphP(0, 0));
    }

    @Override
    public void rLineTo(float dx, float dy) {
        if(list.get(list.size()-1).getPosition()<dx){
            super.rLineTo(dx, dy);
            list.add(new GraphP(dx,dy));
        }
    }

    @Override
    public void moveTo(float x, float y) {
        super.moveTo(x, y);
        list.add(new GraphP(x,y));
    }

    public float seeFirstPos(){
        return this.list.get(0).getPosition();
    }

    public float seeLastPos(){
        return this.list.get(this.list.size()-1).getPosition();
    }

    public float seeLastHei(){
        return this.list.get(this.list.size()-1).getHeight();
    }

    private GraphP getNext(float pos){
        GraphP my;
        while (list.iterator().hasNext()){
            my = list.iterator().next();
            if(my.getPosition()>=pos) return my;
        }
        return null;
    }

    public void clear(float start){
        /*
         * clears all list data before the start position
         * Do not call continuusly
         */

        GraphP my;
        while (list.iterator().hasNext()){
            my = list.iterator().next();
            if(my.getPosition()<start) list.remove(my);
        }
    }

    public float getNextHeight(float pos){
        GraphP next = getNext(pos);
        if(next.getPosition()-pos<=1)return next.getHeight();
        else return next.getHeight()+(next.getAngle()*(next.getPosition()-pos));
    }

}
