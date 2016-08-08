package com.example.travis.a3dmodeller.util;

/**
 * Created by Travis on 8/5/16.
 */
public class Geometry {
    public static class Point {
        public final float x, y, z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point translate(float distX, float distY, float distZ) {
            return new Point(x + distX, y + distY, z + distZ);
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getZ() {
            return z;
        }

    }

    public static class Plane {
        public final Point center;
        public final float x;
        public final float y;

        public Plane(Point center, float x, float y) {
            this.center = center;
            this.x = x;
            this.y = y;
        }

        public Plane scale(float xFactor, float yFactor) {
            return new Plane(center, x * xFactor, y * yFactor);
        }
    }

}
