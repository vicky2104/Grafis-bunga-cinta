package org.yourorghere;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TA_Grafis extends JFrame implements KeyListener, GLEventListener, MouseListener, MouseMotionListener {

    GLRenderer glr;


    public static void main(String[] args) {
        Frame frame = new Frame("");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new TA_Grafis());
        canvas.addKeyListener(new TA_Grafis());

        frame.addKeyListener(new TA_Grafis());
        frame.add(canvas);
        frame.setSize(640, 480);

        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
// Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
    private float view_rotx = 20.0f;
    private float view_roty = 30.0f;
    private int oldMouseX;
    private int oldMouseY;
    float geserx;
    static double[][] randomakar = new double[150][4];

    public void init(GLAutoDrawable drawable) {
         for (int i = 0; i < randomakar.length; i++) {
            for (int j = 0; j < randomakar[0].length; j++) {
                randomakar[i][j] = Math.random();
            }
        }

        GL gl = drawable.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        float ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float difusse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float specular[] = {0.2f, 1.0f, 0.2f, 1.0f};
        float position[] = {20.0f, 30.0f, 20.0f, 0.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, difusse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
        float[] mambient = {0.1745f, 0.01175f, 0.01175f, 0.55f
        };
        float[] mdiffuse = {0.61424f, 0.04136f, 0.04136f, 0.55f};
        float[] mspecular = {0.727811f, 0.626959f, 0.626959f, 0.55f};
        float mshine = 76.8f;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mambient, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mdiffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mspecular, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, mshine);

        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);

        try {
            sun = TextureIO.newTexture(new File("sunflower.png"), true);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    static Texture sun = null;

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) {
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    

        static double bunga = 0;
        static double bunga1 = 0;
        static double lebar = 0;
        static double tinggi = 0;
        static double transy = 0;
        static double pindahpot = 0;
        static double gerakpot = 0;
        static boolean bolehgerakpot = false;

        static float atas = 0.0f;
        static float bawah = 0.0f;
        static float kanan = 0.0f;
        static float kiri = 0.0f;
        static float tambah = 5f;

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        gl.glEnable(GL.GL_DEPTH_TEST);
        //gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        //gl.glAlphaFunc(GL.GL_GEQUAL, 0.0f);
        GLU glu = new GLU();
// Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
// Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        glu.gluLookAt(4, 4, 4, // eye pos
                0, 0, 0, // look at
                0, 0, 1); // up
        gl.glTranslatef(-1.5f, -1.5f, -2.5f);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-0.5f, -0.5f, 1.5f);
    
        gl.glRotated(50, 0, -1, 0);
        gl.glTranslated(0, 0, -3);
        
        gl.glRotated(atas, 1, 0, 0);
        gl.glRotated(bawah, -1, 0, 0);
        gl.glRotated(kiri, 0, 0, -1);
        gl.glRotated(kanan, 0, 0, 1);

        gl.glPushMatrix();
        Objek.pot(gl);
        gl.glPopMatrix();
        
        gl.glTranslated(0, 0, 1);
        gl.glPopMatrix();
        Objek.biji(gl); // Pada fungsi yang sudah dibuat pada pembahasan sebelumnya
        gl.glPopMatrix();//Objek Bola
        
        gl.glPushMatrix();
        Objek.batang(gl); // Pada fungsi yang sudah dibuat pada pembahasan sebelumnya
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        gl.glTranslated(0, 4, -0.5);
        Objek.bunga(gl);
        gl.glPopMatrix();
        
        gl.glLoadIdentity();

        if (lebar < 0.25) {
            lebar += 0.0005;
        }
        if (tinggi <= 4) {
            tinggi += 0.005;

        } else {
            if (bunga <= 1.5) {
                bunga += 0.003;
            }
            if (bunga1 <= 0.5) {
                bunga1 += 0.001;

            }

            gl.glFlush();
        }

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaY = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaX = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    public void mouseMoved(MouseEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 87) {
            atas += tambah;
        } else if (e.getKeyCode() == 83) {
            bawah += tambah;
        } else if (e.getKeyCode() == 65) {
            kiri += tambah;
        } else if (e.getKeyCode() == 68) {
            kanan += tambah;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
