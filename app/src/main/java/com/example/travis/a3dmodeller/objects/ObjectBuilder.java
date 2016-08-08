package com.example.travis.a3dmodeller.objects;

/**
 * Created by Travis on 8/5/16.
 */
public class ObjectBuilder {
    private static final int FLOATS_PER_VERTEX = 3;
    private final float[] vertexData;
    private int offset = 0;

    private ObjectBuilder(int sizeInVertices) {
        vertexData = new float[sizeInVertices];
    }

}
