package trabalho4;

import javax.media.opengl.glu.GLU;

public class Camera {
	private GLU glu;
	private float xEye, yEye, zEye;
	public float getxEye() {
		return xEye;
	}

	public void setxEye(float xEye) {
		this.xEye = xEye;
	}

	public float getyEye() {
		return yEye;
	}

	public void setyEye(float yEye) {
		this.yEye = yEye;
	}

	public float getzEye() {
		return zEye;
	}

	public void setzEye(float zEye) {
		this.zEye = zEye;
	}

	public float getxCenter() {
		return xCenter;
	}

	public void setxCenter(float xCenter) {
		this.xCenter = xCenter;
	}

	public float getyCenter() {
		return yCenter;
	}

	public void setyCenter(float yCenter) {
		this.yCenter = yCenter;
	}

	public float getzCenter() {
		return zCenter;
	}

	public void setzCenter(float zCenter) {
		this.zCenter = zCenter;
	}

	public float getxUp() {
		return xUp;
	}

	public void setxUp(float xUp) {
		this.xUp = xUp;
	}

	public float getyUp() {
		return yUp;
	}

	public void setyUp(float yUp) {
		this.yUp = yUp;
	}

	public float getzUp() {
		return zUp;
	}

	public void setzUp(float zUp) {
		this.zUp = zUp;
	}

	private float xCenter, yCenter, zCenter,xUp,yUp,zUp;
	
	public Camera(GLU glu ){
		this.glu = glu;
		xEye = 10.0f;
		yEye = 5.0f;
		zEye = 40.0f;
		xCenter = 10.0f;
		yCenter = 0.0f;
		zCenter = 0.0f;
		xUp = 0.0f;
		yUp = 1.0f;
		zUp = 0.0f;
	}
	
	public void adicionarCamera(){
		glu.gluLookAt(xEye, yEye, zEye, xCenter, yCenter, zCenter, 0.0f, 1.0f, 0.0f);
	}
	
	public void IrParaEsquerda(){
		if (xEye > 3.0f) {
			xEye = xEye - 1.0f;
		}
	}
	
	public void IrParaDireita(){
		if (xEye < 17.0f) {
			xEye = xEye + 1.0f;
		}
	}
}
