package com.example.travis.a3dmodeller.objects;

import com.example.travis.a3dmodeller.Constants;
import com.example.travis.a3dmodeller.data.VertexArray;
import com.example.travis.a3dmodeller.programs.ColorShaderProgram;
import com.example.travis.a3dmodeller.util.Geometry;

import static android.opengl.GLES20.*;

/**
 * Created by Travis on 8/6/16.
 */
public class IsoSphere {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;

    private static final float GR = 0.4f;
    private static final float HEIGHT = 0.25f;

    private static final int numFaces = 20;
    private static final int verticesPerTriangle = 3;

    // Points to the first open element of VERTEX_DATA
    private static int v_array_count = 0;
    private static float[] VERTEX_DATA = new float[numFaces * verticesPerTriangle * POSITION_COMPONENT_COUNT];

//    private static float[] VERTEX_DATA = {
//            // 0, 11, 5
//            -1.0f, GR, 0.0f,
//            -GR, 0.0f, 1.0f,
//            0.0f, 1.0f, GR,
//
//            // 0, 5, 1
//            -1.0f, GR, 0.0f,
//            0.0f, 1.0f, GR,
//            1.0f, GR, 0.0f,
//
//            // 0, 1, 7
//            -1.0f, GR, 0.0f,
//            1.0f, GR, 0.0f,
//            0.0f, 1.0f, -GR,
//
//            // 0, 7, 10
//            -1.0f, GR, 0.0f,
//            0.0f, 1.0f, -GR,
//            -GR, 0.0f, -1.0f,
//
//            // 0, 10, 11
//            -1.0f, GR, 0.0f,
//            -GR, 0.0f, -1.0f,
//            -GR, 0.0f, 1.0f
//    };

//    private static float[] VERTEX_DATA = {
//            0.4f, 0.0f, 0.0f,
//            0.0f, 0.0f, -GR,
//            -0.4f, 0.0f, 0.0f
//    };


    private static final Geometry.Point[] VERTICES = {
            new Geometry.Point(-HEIGHT, GR, 0),
            new Geometry.Point(HEIGHT, GR, 0),
            new Geometry.Point(-HEIGHT, -GR, 0),
            new Geometry.Point(HEIGHT, GR, 0),

            new Geometry.Point(0, -HEIGHT, GR),
            new Geometry.Point(0, HEIGHT, GR),
            new Geometry.Point(0, -HEIGHT, -GR),
            new Geometry.Point(0, HEIGHT, -GR),

            new Geometry.Point(GR, 0, -HEIGHT),
            new Geometry.Point(GR, 0, HEIGHT),
            new Geometry.Point(-GR, 0, -HEIGHT),
            new Geometry.Point(-GR, 0, HEIGHT),

    };

    private final VertexArray vertexArray;

    public IsoSphere() {
        addTrianglesToVertexArray();
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    private void addTrianglesToVertexArray() {
        addTriangleToVertexArray(0, 11, 5);
        addTriangleToVertexArray(0, 5, 1);
        addTriangleToVertexArray(0, 1, 7);
        addTriangleToVertexArray(0, 7, 10);
        addTriangleToVertexArray(0, 10, 11);

        addTriangleToVertexArray(1, 5, 9);
        addTriangleToVertexArray(5, 11, 4);
        addTriangleToVertexArray(11, 10, 2);
        addTriangleToVertexArray(10, 7, 6);
        addTriangleToVertexArray(7, 1, 8);

        addTriangleToVertexArray(3, 9, 4);
        addTriangleToVertexArray(3, 4, 2);
        addTriangleToVertexArray(3, 2, 6);
        addTriangleToVertexArray(3, 6, 8);
        addTriangleToVertexArray(3, 8, 9);

        addTriangleToVertexArray(4, 9, 5);
        addTriangleToVertexArray(2, 4, 11);
        addTriangleToVertexArray(6, 2, 10);
        addTriangleToVertexArray(8, 6, 7);
        addTriangleToVertexArray(9, 8, 1);

    }


    private static void addTriangleToVertexArray(int p1, int p2, int p3) {
        addPointToVertexArray(p1);
        addPointToVertexArray(p2);
        addPointToVertexArray(p3);

    }


    private static void addPointToVertexArray(int pointNum) {

        VERTEX_DATA[v_array_count] = VERTICES[pointNum].getX();
        VERTEX_DATA[v_array_count + 1] = VERTICES[pointNum].getY();
        VERTEX_DATA[v_array_count + 2] = VERTICES[pointNum].getZ();

        v_array_count += 3;

    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLES, 0, 60);
    }
}
