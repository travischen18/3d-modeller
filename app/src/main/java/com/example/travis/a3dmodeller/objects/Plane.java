package com.example.travis.a3dmodeller.objects;

import com.example.travis.a3dmodeller.Constants;
import com.example.travis.a3dmodeller.data.VertexArray;
import com.example.travis.a3dmodeller.programs.ColorShaderProgram;
import com.example.travis.a3dmodeller.util.Geometry;

import static android.opengl.GLES10.*;

/**
 * Created by Travis on 8/5/16.
 */
public class Plane {
    private static final int NUM_VERTICES = 4;
    private static final int FLOATS_PER_VERTEX = 3;
    private static final int STRIDE = (FLOATS_PER_VERTEX) * Constants.BYTES_PER_FLOAT;

    private float[] VERTEX_DATA;

    private final VertexArray vertexArray;

    // Planes are always initialized flat on the x-z plane
    public Plane(Geometry.Point center, float x_length, float z_length) {
        buildVertexData(center, x_length / 2, z_length / 2);
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    private void buildVertexData(Geometry.Point center, float x_factor, float z_factor) {
        float x_center = center.getX();
        float y_center = center.getY();
        float z_center = center.getZ();

        VERTEX_DATA = new float[]{
                x_center + x_factor, y_center, z_center + z_factor,
                x_center + x_factor, y_center, z_center - z_factor,
                x_center - x_factor, y_center, z_center + z_factor,
                x_center - x_factor, y_center, z_center - z_factor,
        };

    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                FLOATS_PER_VERTEX,
                STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_STRIP, 0, NUM_VERTICES * FLOATS_PER_VERTEX);
    }

}
