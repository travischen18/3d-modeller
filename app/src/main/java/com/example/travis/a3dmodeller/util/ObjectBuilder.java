package com.example.travis.a3dmodeller.util;

import java.util.ArrayList;
import java.util.List;

import static android.opengl.GLES20.*;

/**
 * Created by Travis on 8/7/16.
 */
public class ObjectBuilder {

    private final int FLOATS_PER_VERTEX = 3;
    private final float[] vertexData;
    private final List<DrawCommand> drawCommandList = new ArrayList<DrawCommand>();
    private int offset = 0;

    private final static int PLANE_SIZE = 4;
    private final static int CUBE_SIZE = 24;

    public static class ObjectData {
        public final float[] vertexData;
        public final List<DrawCommand> drawCommandList;

        ObjectData(float[] vertexData, List<DrawCommand> drawCommandList) {
            this.vertexData = vertexData;
            this.drawCommandList = drawCommandList;
        }
    }

    public static interface DrawCommand {
        void draw();
    }

    private ObjectBuilder(int size) {
        vertexData = new float[size * FLOATS_PER_VERTEX];
    }

    // Creates a plane using two opposite corners
    // All planes are intialized orthogonal to the y axis
    public static ObjectData createPlane(Geometry.Point corner1, Geometry.Point corner2) {
        ObjectBuilder builder = new ObjectBuilder(PLANE_SIZE);
        builder.addPlane(corner1, corner2);

        return builder.build();
    }

    public static ObjectData createCube(Geometry.Point center, float width) {
        ObjectBuilder builder = new ObjectBuilder(CUBE_SIZE);

        float a = width / 2;

        // Create the vertices
        Geometry.Point p1 = center.translate(a, a, a);
        Geometry.Point p2 = center.translate(a, -a, a);
        Geometry.Point p3 = center.translate(-a, a, a);
        Geometry.Point p4 = center.translate(-a, -a, a);
        Geometry.Point p5 = center.translate(a, a, -a);
        Geometry.Point p6 = center.translate(a, -a, -a);
        Geometry.Point p7 = center.translate(-a, a, -a);
        Geometry.Point p8 = center.translate(-a, -a, -a);

        // TODO: streamline this later

        // planes orthogonal to z axis
        builder.addPlane(p1, p4);
        builder.addPlane(p5, p8);

        //planes orthogonal to y axis
        builder.addPlane(p1, p7);
        builder.addPlane(p2, p8);

        // planes orthogonal to x axis
        builder.addPlane(p1, p6);
        builder.addPlane(p3, p8);

        return builder.build();
    }

    // Private method to construct a plane
    private void addPlane(Geometry.Point corner1, Geometry.Point corner2) {

        final int NUM_VERTICES = 4;
        final int startVertex = offset / FLOATS_PER_VERTEX;

        float x1 = corner1.getX();
        float y1 = corner1.getY();
        float z1 = corner1.getZ();

        float x2 = corner2.getX();
        float y2 = corner2.getY();
        float z2 = corner2.getZ();

        vertexData[offset++] = x1;
        vertexData[offset++] = y1;
        vertexData[offset++] = z1;

        vertexData[offset++] = x2;
        vertexData[offset++] = y1;
        vertexData[offset++] = z1;

        vertexData[offset++] = x1;
        vertexData[offset++] = y1;
        vertexData[offset++] = z2;

        vertexData[offset++] = x2;
        vertexData[offset++] = y2;
        vertexData[offset++] = z2;

        drawCommandList.add(new DrawCommand() {
            @Override
            public void draw() {
                glDrawArrays(GL_TRIANGLE_STRIP, startVertex, NUM_VERTICES);
            }
        });
    }

    private ObjectData build() {
        return new ObjectData(vertexData, drawCommandList);
    }

}
