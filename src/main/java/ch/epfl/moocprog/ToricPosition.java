package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Vec2d;

public final class ToricPosition {
    private Vec2d vec2d;

    public ToricPosition(Vec2d vec2d) {
        this.vec2d = vec2d;
    }
    public ToricPosition() {
        this.vec2d = new Vec2d(0,0);
    }
    public ToricPosition(double x, double y) {
        this.vec2d = new Vec2d(x,y);
    }

    private static Vec2d clampedPosition(double x, double y){
        return new Vec2d(x,y);
    }

    private ToricPosition add(ToricPosition that){
        return that;
    }

//    private ToricPosition add(Vec2d vec){
//        return vec;
//    }

    public Vec2d toVec2d(){
        return vec2d;
    }

    private Vec2d toricVector(ToricPosition that){
        return vec2d;
    }


    public double toricDistance(ToricPosition position) {
        return 0;
    }
}
