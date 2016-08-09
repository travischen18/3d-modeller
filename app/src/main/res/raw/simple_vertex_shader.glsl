uniform mat4 u_Matrix;

attribute vec4 a_Position;
attribute vec4 a_Color;

void main()
{
    // Draws the coordinate to the screen using the projection matrix
    // multipled by the virtual coordinate
    gl_Position = u_Matrix * a_Position;

}