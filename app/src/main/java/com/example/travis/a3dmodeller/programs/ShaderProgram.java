package com.example.travis.a3dmodeller.programs;

import android.content.Context;
import android.graphics.Shader;

import com.example.travis.a3dmodeller.util.ShaderHelper;
import com.example.travis.a3dmodeller.util.TextResourceReader;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by Travis on 8/5/16.
 */
public class ShaderProgram {

    // Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_COLOR = "u_Color";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    // Shader program
    protected final int program;
    protected ShaderProgram(Context context, int vertexShaderResourceId,
        int fragmentShaderResourceId) {

        // Compile shaders and link the program
        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(
                        context, vertexShaderResourceId),
                TextResourceReader.readTextFileFromResource(
                        context, fragmentShaderResourceId)
        );
    }

    public void useProgram() {
        glUseProgram(program);
    }
}
