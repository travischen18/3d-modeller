package com.example.travis.a3dmodeller.objects;

import com.example.travis.a3dmodeller.Constants;
import com.example.travis.a3dmodeller.data.VertexArray;
import com.example.travis.a3dmodeller.programs.ColorShaderProgram;
import com.example.travis.a3dmodeller.util.*;
import com.example.travis.a3dmodeller.util.ObjectBuilder;

import java.util.List;

import static android.opengl.GLES10.GL_TRIANGLE_STRIP;
import static android.opengl.GLES10.glDrawArrays;

/**
 * Created by Travis on 8/7/16.
 */
public class Cube {
    private static final int NUM_VERTICES = 4;
    private static final int FLOATS_PER_VERTEX = 3;
    private static final int STRIDE = (FLOATS_PER_VERTEX) * Constants.BYTES_PER_FLOAT;

    private float[] VERTEX_DATA;

    private final VertexArray vertexArray;
    private final List<ObjectBuilder.DrawCommand> drawCommandList;

    public Cube(Geometry.Point center, float width) {
        ObjectBuilder.ObjectData objectData = ObjectBuilder.createCube(center, width);

        vertexArray = new VertexArray(objectData.vertexData);
        drawCommandList = objectData.drawCommandList;
    }

//    private void buildVertexData(Geometry.Point center, float x_factor, float z_factor) {
//        float x_center = center.getX();
//        float y_center = center.getY();
//        float z_center = center.getZ();
//
//        VERTEX_DATA = new float[]{
//                x_center + x_factor, y_center, z_center + z_factor,
//                x_center + x_factor, y_center, z_center - z_factor,
//                x_center - x_factor, y_center, z_center + z_factor,
//                x_center - x_factor, y_center, z_center - z_factor,
//        };
//
//    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                FLOATS_PER_VERTEX,
                STRIDE);
    }

    public void draw() {
        for (ObjectBuilder.DrawCommand drawCommand: drawCommandList) {
            drawCommand.draw();
        }
    }
}
