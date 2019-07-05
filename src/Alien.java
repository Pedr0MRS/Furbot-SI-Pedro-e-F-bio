
import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Alien extends ObjetoDoMundoAdapter {

	ProjeteisAlien projetil = new ProjeteisAlien();
	boolean vivo = true;
	int random;
	
	int velocidadeAlien;
	int level = ObjSpaceInvaders.RetornarLevel();

	public int VelocidadePorLvl() {
		switch (level) {
		    case 1:
			velocidadeProjetilAlien = 550;
			break;
		    case 2:
			velocidadeProjetilAlien = 450;
			break;
		    case 3:
			velocidadeProjetilAlien = 400;
			break;
		    default:
			velocidadeProjetilAlien = 550;
			break;
		}
        return velocidadeAlien;
    	}

	@Override
	public void executar() throws Exception {
		while (vivo) {
			while (!ehFim(ESQUERDA) && vivo) {
				Thread.sleep(VelocidadePorLvl());
				if (vivo) {
					int randomTime = (int) (Math.random() * 2 + 1);
					if (randomTime == 1) {
						ProjeteisAlien projetil = new ProjeteisAlien();
						adicionarObjetoNoMundoXY(projetil, getX(), getY());
						File tiro = new File("Tiro.wav");
						SomTiro(tiro);
					}
				}
				if (SpaceInvaders.pause) {
					pausarAlien();
				}
				if (vivo) {
					andarEsquerda();
				}
			}

			while (!ehFim(DIREITA) && vivo) {
				Thread.sleep(VelocidadePorLvl());
				if (vivo) {
					int randomTime = (int) (Math.random() * 2 + 1);
					if (randomTime == 1) {
						ProjeteisAlien projetil = new ProjeteisAlien();
						adicionarObjetoNoMundoXY(projetil, getX(), getY());
						File tiro = new File("Tiro.wav");
						SomTiro(tiro);
					}
				}
				if (SpaceInvaders.pause) {
					pausarAlien();
				}
				if (vivo) {
					andarDireita();
				}
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

	private void pausarAlien() throws InterruptedException {
		while (SpaceInvaders.pause) {
			Thread.sleep(2000);
		}
	}

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("alien.png");
	}
}
