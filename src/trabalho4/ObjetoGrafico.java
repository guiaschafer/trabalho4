package trabalho4;

import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;

public final class ObjetoGrafico {

	GL gl;
	private GLUT glut;

	// 0 = alvo 1 = tiro
	private int tipo = 0;
	private Random gerador = new Random();
	private float translacao[] = { 0.0f, 0.0f, 0.0f };
	private float escala[] = { 2.0f, 2.0f, 2.0f };

	private float translacaoSuporte[] = { 0.0f, 0.0f, 0.0f };
	private float escalaSuporte[] = { 1.0f, 2.5f, 1.0f };

	private float r, g, b;

	public ObjetoGrafico(GL gl, GLUT glut, float xEye, float yEye, float zEye) {
		escala[0] = 0.1f;
		escala[1] = 0.1f;
		escala[2] = 0.5f;

		translacao[0] = xEye;
		translacao[1] = yEye;
		translacao[2] = zEye;

		tipo = 1;
		this.gl = gl;
		this.glut = glut;
	}

	public ObjetoGrafico(GL gl, GLUT glut) {
		escala[0] = 5.0f;
		escala[1] = 5.0f;
		escala[2] = 1.0f;

		float random = 5 + gerador.nextFloat() * (15 - 5);
		translacao[0] = random;
		translacao[1] = 5.0f;
		translacao[2] = 5.0f;

		translacaoSuporte[0] = random;
		translacaoSuporte[1] = 8.5f;
		translacaoSuporte[2] = 5.0f;

		r = gerador.nextFloat();
		g = gerador.nextFloat();
		b = gerador.nextFloat();
		this.gl = gl;
		this.glut = glut;
	}

	public void desenha() {
		if (tipo == 0) {
			// desenha alvo
			gl.glColor3f(r, g, b);
			gl.glPushMatrix();
				gl.glTranslated(translacao[0], translacao[1], translacao[2]);
				gl.glScalef(escala[0], escala[1], escala[2]);
				glut.glutSolidCube(1.0f);
			gl.glPopMatrix();

			// desenha suporte
			gl.glColor3f(0, 0, 0);
			gl.glPushMatrix();
				gl.glTranslated(translacaoSuporte[0], translacaoSuporte[1], translacaoSuporte[2]);
				gl.glScalef(escalaSuporte[0], escalaSuporte[1], escalaSuporte[2]);
				glut.glutSolidCube(1.0f);
			gl.glPopMatrix();
		} else {
			//desenha tiro
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glPushMatrix();
				gl.glTranslated(translacao[0], translacao[1], translacao[2]);
				gl.glScalef(escala[0], escala[1], escala[2]);
				glut.glutSolidCube(1.0f);
			gl.glPopMatrix();
		}
	}

	public void atribuirGL(GL gl, GLUT glut) {
		this.gl = gl;
		this.glut = glut;
	}

	public void animarAlvo(int sorteio) {
		switch (sorteio) {
		case 0:
			IrEsquerda();
			break;
		case 1:
			IrDireita();
			break;
		case 2:
			Aproximar();
			break;
		default:
			System.out.println(sorteio);
			break;
		}
	}

	public void animarTiro() {
		if (translacao[2] > 0.0f) {
			translacao[2] = translacao[2] - 3.0f;
		}
	}

	public void IrEsquerda() {
		if ((translacao[0] - 2.5f) > 1.0f) {
			translacaoSuporte[0] = translacaoSuporte[0] - 0.5f;
			translacao[0] = translacao[0] - 0.5f;
		}
	}

	public void IrDireita() {
		if ((translacao[0] + 2.5f) < 19.0f) {
			translacaoSuporte[0] = translacaoSuporte[0] + 0.5f;
			translacao[0] = translacao[0] + 0.5f;
		}
	}

	public void Aproximar() {
		if (translacao[2] < 30.0f) {
			translacaoSuporte[2] = translacaoSuporte[2] + 1.5f;
			translacao[2] = translacao[2] + 1.5f;
		}
	}

	public boolean verificarTiroNoFundo(float translacaoTiro[]) {
		if (translacaoTiro[2] <= 0.0f) {
			return true;
		}

		return false;
	}

	public boolean verificarAlvoNaFrente(float translacaoAlvo[]) {
		if (translacaoAlvo[2] >= 30.0f) {
			return true;
		}

		return false;
	}

	public boolean verificarTiroNoAlvo(float translacaoTiro[]) {
		if (translacaoTiro[2] <= translacao[2]) {
			if (translacaoTiro[0] >= (translacao[0] - 2.5f) && translacaoTiro[0] <= (translacao[0] + 2.5f))
				return true;
		}

		return false;
	}

	public boolean verificarAlvoNoTiro(float translacaoAlvo[]) {
		if (translacaoAlvo[2] == translacao[2]) {
			if ((translacaoAlvo[0] - 2.5f) >= translacao[0] && (translacaoAlvo[0] + 2.5f) <= translacao[0])
				return true;
		}

		return false;
	}

	public float[] getTranslacao() {
		return translacao;
	}

	public void setTranslacao(float[] translacao) {
		this.translacao = translacao;
	}

	public float[] getEscala() {
		return escala;
	}

	public void setEscala(float[] escala) {
		this.escala = escala;
	}
}
