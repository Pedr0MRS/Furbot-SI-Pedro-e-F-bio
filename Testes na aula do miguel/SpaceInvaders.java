import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.suporte.LoadImage;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {

	boolean LoopPrincipal = true;
	public static boolean pause = false;
	static int asteroidesMortos = 0;
	int qtdVidas = 3;
	int linhaAsteroide = 1;
	int colunaAsteroide = 9;
	int colunaVida = 9;
	int nAsteroidesAdc = 0;
	int nAsteroidesCriados = 0;
	int nVidasAdc = 0;
	int nVidasCriadas = 0;
	int nAliensCriados = 0;
	int infoVidas;
	int level = 1;
	Vidas[] arrayVidas = new Vidas[qtdVidas];
	Alien[] arrayAlien = new Alien[2];
	String nomeObj;
	String[] Id;

	public void QuantidadeVidasHUD() {
		if (getObjetoXY(3, 9) != null) {
			removerObjetoDoMundo(getObjetoXY(3, 9));
		} else if (getObjetoXY(2, 9) != null) {
			removerObjetoDoMundo(getObjetoXY(2, 9));
		} else if (getObjetoXY(1, 9) != null) {
			removerObjetoDoMundo(getObjetoXY(1, 9));
		}
	}

	private void loopPausa() {
		while (pause) {
			int teclaPause = getUltimaTeclaPress();
			if (teclaPause == 80) {
				pause = false;
			}
		}
	}

	private static void SomTiro(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} catch (Exception e) {

		}
	}

	private static void SomFimDeJogo(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} catch (Exception e) {

		}
	}

	@Override
	public void inteligencia() throws Exception {
		limparConsole();
		qtdVidas = 3;
		asteroidesMortos = 0;
		Alien alien = new Alien();
		arrayAlien[0] = alien;
		Asteroides[] arrayAsteroides = new Asteroides[20];

		for (int nVidasCriadas = 0; nVidasCriadas < qtdVidas; nVidasCriadas++) {
			Vidas fabricaVidas = new Vidas();
			arrayVidas[nVidasCriadas] = fabricaVidas;
		}

		for (int i = 1; nVidasAdc < qtdVidas; i++) {
			if (!(colunaVida < 0)) {
				adicionarObjetoNoMundoXY(arrayVidas[nVidasAdc], (i - 1), 9);
				nVidasAdc++;
				colunaVida--;
			}
		}

		for (int nAsteroidesCriados = 0; nAsteroidesCriados < 20; nAsteroidesCriados++) {
			Asteroides fabricaAsteroide = new Asteroides();
			arrayAsteroides[nAsteroidesCriados] = fabricaAsteroide;
		}

		for (int i = 1; nAsteroidesAdc < 20; i++) {
			while (linhaAsteroide < 5) {
				adicionarObjetoNoMundoXY(arrayAsteroides[nAsteroidesAdc], colunaAsteroide, linhaAsteroide);
				linhaAsteroide++;
				nAsteroidesAdc++;
			}
			colunaAsteroide--;
			linhaAsteroide = 1;
		}

		if (nAliensCriados == 0) {
			adicionarObjetoNoMundoXY(alien, 9, 0);
			nAliensCriados++;
		}

		while (LoopPrincipal) {
			int tecla = getUltimaTeclaPress();
			switch (tecla) {
			case TECLADIREITA:
				if (!ehFim(DIREITA)) {
					andarDireita();
				}
				break;
			case TECLAESQUERDA:
				if (!ehFim(ESQUERDA)) {
					andarEsquerda();
				}
				break;
			case 80:
				pause = true;
				diga("Jogo pausado");
				loopPausa();
				limparConsole();
				break;
			case 32:
				Projeteis laser = new Projeteis();
				int x = getX();
				int y = getY();
				adicionarObjetoNoMundoXY(laser, x, y - 1);
				File tiro = new File("Tiro.wav");
				SomTiro(tiro);
				break;
			}

			if (infoVidas > qtdVidas) {
				QuantidadeVidasHUD();
			}

			infoVidas = qtdVidas;

			if (asteroidesMortos == 20) {
				diga("VOCÊ VENCEU!");
				LoopPrincipal = false;
			}

			if (qtdVidas == 0) {
				File fimJogo = new File("GameOver.wav");
				SomFimDeJogo(fimJogo);
				diga("GAMEOVER");
				Thread.sleep(200);
				LoopPrincipal = false;
			}
		}
	}

	public static void main(String[] args) {
		MundoVisual.iniciar("SpaceInvaders.xml");
	}

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("nave.png");
	}

}
