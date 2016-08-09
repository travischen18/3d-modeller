package com.example.travis.a3dmodeller;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.travis.a3dmodeller.objects.Cube;
import com.example.travis.a3dmodeller.objects.IsoSphere;
import com.example.travis.a3dmodeller.objects.Plane;
import com.example.travis.a3dmodeller.programs.ColorShaderProgram;
import com.example.travis.a3dmodeller.util.Geometry;
import com.example.travis.a3dmodeller.util.MatrixHelper;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Travis on 8/4/16.
 */
public class ModellerRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];

    private final float[] viewMatrix = new float[16];
    private final float[] viewProjectionMatrix = new float[16];
    private final float[] modelViewProjectionMatrix = new float[16];

    private ColorShaderProgram colorProgram;

    private Plane plane;
    private IsoSphere sphere;
    private Cube cube;

    public ModellerRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        // Clear background
        glClearColor(0.0f, 0.1f, 0.1f, 1.0f);

        colorProgram = new ColorShaderProgram(context);
        plane = new Plane(new Geometry.Point(0, 0, 0), 1.0f, 1.0f);

        sphere = new IsoSphere();
        cube = new Cube(new Geometry.Point(0, 0, 0), 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Set the viewport to fill the entire surface
        glViewport(0, 0, width, height);

        // Generate a perspective matrix
        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 10f);

        // Set the view matrix
        setLookAtM(viewMatrix, 0, 0f, 2f, 3f, 0f, 0f, 0f, 0f, 1f, 0f);

    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        // Clear the rendering surface
        glClear(GL_COLOR_BUFFER_BIT);

        // Put the result of projectionMatrix * viewMatrix into viewProjectionMatrix
        multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        positionPlaneInScene();
        colorProgram.useProgram();
//        colorProgram.setUniforms(modelViewProjectionMatrix, 1f, 0f, 0f);
//
//        sphere.bindData(colorProgram);
//        sphere.draw();



        colorProgram.setUniforms(modelViewProjectionMatrix, 1f, 1f, 0.7f);
        plane.bindData(colorProgram);
        plane.draw();

    }

    private void positionPlaneInScene() {

        // Set the model matrix to identity
        setIdentityM(modelMatrix, 0);
        //rotateM(modelMatrix, 0, -90f, 1f, 0f, 0f);

        // Multiply projection matrix by model matrix
        multiplyMM(modelViewProjectionMatrix, 0, viewProjectionMatrix,
                0, modelMatrix, 0);
    }

}
