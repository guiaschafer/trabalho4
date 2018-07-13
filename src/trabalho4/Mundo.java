package trabalho4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Object;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.sun.opengl.util.GLUT;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

import java.util.Scanner;

public class Mundo {
	GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;

	private ArrayList<ObjetoGrafico> alvos = new ArrayList<ObjetoGrafico>();
	private ArrayList<ObjetoGrafico> tiros = new ArrayList<ObjetoGrafico>();
	private Animacao animacao = new Animacao("");
	private Random gerador = new Random();

	public Mundo(GL gl, GLUT glut, GLAutoDrawable glDrawable, GLU glu) {
		this.gl = gl;
		this.glut = glut;
		this.glDrawable = glDrawable;
		this.glu = glu;
		animacao.start();
		alvos = new ArrayList<ObjetoGrafico>();
	}

	public void adicionarAlvo() {
		alvos.add(new ObjetoGrafico(gl, glut));
	}

	class Animacao extends Thread {
		public Animacao(String str) {
			super(str);
		}

		public void run() {
			while (true) {
				try {					
					ArrayList<ObjetoGrafico> removerAlvos = new ArrayList<ObjetoGrafico>();
					ArrayList<ObjetoGrafico> removerTiros = new ArrayList<ObjetoGrafico>();
					
					for (ObjetoGrafico objetoGrafico : alvos) {
						objetoGrafico.animarAlvo(gerador.nextInt(3));
						boolean chegouNaFrente = objetoGrafico.verificarAlvoNaFrente(objetoGrafico.getTranslacao());
						if(chegouNaFrente){
							removerAlvos.add(objetoGrafico);
						}
					}
					for (ObjetoGrafico objetoGrafico : tiros) {
						objetoGrafico.animarTiro();
						boolean acertouFundo = objetoGrafico.verificarTiroNoFundo(objetoGrafico.getTranslacao());
						if(acertouFundo){
							removerTiros.add(objetoGrafico);
						}
						
					}
					
					for (ObjetoGrafico objetoGrafico : tiros) {
						for (ObjetoGrafico obj : alvos) {
							
							boolean acertouAlvo = obj.verificarTiroNoAlvo(objetoGrafico.getTranslacao());
							if (acertouAlvo) {
								removerAlvos.add(obj);
								removerTiros.add(objetoGrafico);
							}
						}
					}
										
					
					for (ObjetoGrafico objetoGrafico : removerTiros) {
						tiros.remove(objetoGrafico);
					}
					
					for (ObjetoGrafico objetoGrafico : removerAlvos) {
						alvos.remove(objetoGrafico);
					}
					
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					glDrawable.display(); // redesenhar ..
				}
			}
		}
	}

	public void adicionarTiro(float xEye, float yEye, float zEye) {
		tiros.add(new ObjetoGrafico(gl, glut, xEye, yEye, zEye));
	}

	public void desenhaMundo() {
		for (ObjetoGrafico objetoGrafico : alvos) {
			objetoGrafico.desenha();
		}
		for (ObjetoGrafico objetoGrafico : tiros) {
			objetoGrafico.desenha();
		}
	}
}
