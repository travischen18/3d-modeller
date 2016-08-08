package com.example.travis.a3dmodeller.objects;

import com.example.travis.a3dmodeller.Constants;
import com.example.travis.a3dmodeller.data.VertexArray;
import com.example.travis.a3dmodeller.programs.ColorShaderProgram;

import static android.opengl.GLES10.*;

/**
 * Created by Travis on 8/5/16.
 */
public class Plane {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            // Order of coordinates: X, Y, Z

            // Triangle Fan
            0.5f, 0f, -0.8f,
            -0.5f, 0f, 0.8f,
            0.5f, 0f, 0.8f,
            0.5f,  0f, -0.8f,
            -0.5f, 0f, -0.8f,
            -0.5f, 0f, 0.8f};

    private final VertexArray vertexArray;

    public Plane() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLES, 0, 6);
    }

}
