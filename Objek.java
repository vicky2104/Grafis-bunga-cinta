//*
//// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import static org.yourorghere.TA_Grafis.lebar;
import static org.yourorghere.TA_Grafis.tinggi;
import static org.yourorghere.TA_Grafis.pindahpot;

/**
 *
 * @author ahong
 */
public class Objek {

    static double takar = 0;
    static double bakar = 0;
    static double cakar = 0;

    static void biji(GL gl) {

        gl.glColor3d(0.566, 0.324, 0);
        float BODY_RADIUS = 0.5f;
        double SLICES = 12.34;
        int STACKS = 13;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();

        glu.gluSphere(q, BODY_RADIUS, (int) SLICES, STACKS);

    }

    static void batang(GL gl) {
        gl.glTranslated(0, 0, 0.05);
        gl.glColor3d(0.12, 1, 0);
        float BODY_LENGTH = 0.5f;
        float BODY_RADIUS = 0.025f;

        int SLICES = 15;
        int STACKS = 13;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();

        glu.gluCylinder(q, lebar, lebar,
                TA_Grafis.tinggi, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);

    }

    static void pot(GL gl) {
        float BODY_LENGTH = 2f;
        float BODY_RADIUS = 1f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        gl.glColor3d(0, 0.5, 0.5);
        GLUquadric q = glu.gluNewQuadric();
        glu.gluCylinder(q, BODY_RADIUS, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);

    }

}
