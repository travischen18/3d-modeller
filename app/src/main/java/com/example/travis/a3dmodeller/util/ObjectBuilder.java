//package com.example.travis.a3dmodeller.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static android.opengl.GLES20.*;
//
///**
// * Created by Travis on 8/7/16.
// */
//public class ObjectBuilder {
//
//    private final int FLOATS_PER_VERTEX = 3;
//    private final float[] vertexData;
//    private final List<DrawCommand> drawCommandList = new ArrayList<DrawCommand>();
//    private int offset = 0;
//
//    static class ObjectData {
//        final float[] vertexData;
//        final List<DrawCommand> drawCommandList;
//
//        ObjectData(float[] vertexData, List<DrawCommand> drawCommandList) {
//            this.vertexData = vertexData;
//            this.drawCommandList = drawCommandList;
//        }
//    }
//
//    static interface DrawCommand {
//        void draw();
//    }
//
//    private ObjectBuilder(int numVertices) {
//        vertexData = new float[numVertices * FLOATS_PER_VERTEX];
//    }
//
//    // Creates a plane using the center point
//    // and the xlength (edge to edge) and zlength (edge to edge)
//    public static ObjectData createPlane(float center_x, float center_y, float center_z, float xlength, float zlength) {
//
//    }
//
//    // Private method to construct a plane
//    private void addPlane(float center_x, float center_y, float center_z, float xlength, float zlength) {
//
//        final int NUM_VERTICES = 4;
//        int startVertex = offset / FLOATS_PER_VERTEX;
//
//        vertexData[offset++]
//
//        vertexData[]
//
//        drawCommandList.add(new DrawCommand() {
//            @Override
//            public void draw() {
//                glDrawArrays(GL_TRIANGLE_STRIP, 0, NUM_VERTICES);
//            }
//        });
//    }
//
//
//}
