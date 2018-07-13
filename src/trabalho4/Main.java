package trabalho4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

public class Main implements GLEventListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	
	private Camera camera;
	private Mundo mundo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			mundo.adicionarAlvo();
			break;
		case KeyEvent.VK_ENTER:
			mundo.adicionarTiro(camera.getxEye(),camera.getyEye(),camera.getzEye());
			break;
		case KeyEvent.VK_LEFT:
			camera.IrParaEsquerda();
		
			break;
		case KeyEvent.VK_RIGHT:
			camera.IrParaDireita();
			break;
		}

		glDrawable.display();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		camera.adicionarCamera();	
		desenharAmbiete();
		mundo.desenhaMundo();
		gl.glFlush();
	}

	public void desenharAmbiete() {
		//chão
		gl.glColor3f(1.0f, 0.5f, 0.0f);
		gl.glPushMatrix();
			gl.glTranslatef(10.0f, -0.5f, 15.0f);
			gl.glScalef(20, 1, 40);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		//teto
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glPushMatrix();
			gl.glTranslated(10, 10, 15);
			gl.glScalef(20, 1, 40);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		//parede esquerda
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glPushMatrix();
			gl.glTranslated(0.5, 5, 15);
			gl.glScalef(1, 10, 40);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glPushMatrix();
			gl.glTranslated(19.5, 5, 15);
			gl.glScalef(1, 10, 40);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
		//parede direita
		gl.glColor3f(1.0f, 1.0f, 0.5f);
		gl.glPushMatrix();
			gl.glTranslated(10, 5, -5);
			gl.glScalef(20, 10, 1);
			glut.glutSolidCube(1.0f);
		gl.glPopMatrix();
	}

	public void drawAxis() {
		// eixo X - Red
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(10.0f, 0.0f, 0.0f);
		gl.glEnd();
		// eixo Y - Green
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 10.0f, 0.0f);
		gl.glEnd();
		// eixo Z - Blue
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 0.0f, 10.0f);
		gl.glEnd();
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glut = new GLUT();
		glDrawable.setGL(new DebugGL(gl));		

		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL.GL_DEPTH_TEST);
		camera = new Camera(glu);
		mundo = new Mundo(gl, glut, glDrawable,glu);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);

		glu.gluPerspective(60, width / height, 0.1, 100);

	}

}
