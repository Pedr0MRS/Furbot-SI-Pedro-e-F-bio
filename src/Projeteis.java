import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class Projeteis extends ObjetoDoMundoAdapter {

	Asteroides ObjAsteroide = new Asteroides();
	int Morto = 0;
	String nomeObj;
	String[] Id;
	boolean tiroVivo = true;
	Asteroides asteroide;
	Alien alien;
	ProjeteisAlien projAlien;

	private void SomExplosao(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} catch (Exception e) {

		}
	}

	private void pausarTiros() throws InterruptedException {
		while (SpaceInvaders.pause) {
			Thread.sleep(2000);
		}
	}
	
	@Override
	public void executar() throws Exception {
		while (!ehFim(ACIMA)) {
			if (SpaceInvaders.pause) {
				pausarTiros();
			}
			if (tiroVivo) {
				andarAcima();
			}
			if (getObjeto(AQUIMESMO) != null) {
				nomeObj = getObjeto(AQUIMESMO).toString();
				Id = nomeObj.split("@");
				if ("Projeteis".equals(Id[0])) {
					if (tiroVivo) {
						tiroVivo = false;
						removerObjetoDoMundo(getObjeto(AQUIMESMO));
					}
				} else if ("ProjeteisAlien".equals(Id[0])) {
					if (tiroVivo) {
						projAlien = getObjeto(AQUIMESMO);
						projAlien.vivo = false;
						removerObjetoDoMundo(getObjeto(AQUIMESMO));
						File explosao = new File("Explosao.wav");
						SomExplosao(explosao);
						removerMe();
					}

					tiroVivo = false;

				} else if ("Alien".equals(Id[0])) {
					if (tiroVivo) {
						alien = getObjeto(AQUIMESMO);
						alien.vivo = false;
						removerObjetoDoMundo(getObjeto(AQUIMESMO));
						File explosao = new File("Explosao.wav");
						SomExplosao(explosao);
						removerMe();
					}
					tiroVivo = false;
				} else {
					
					if (tiroVivo) {
						asteroide = getObjeto(AQUIMESMO);
						asteroide.vivo = false;
						removerObjetoDoMundo(getObjeto(AQUIMESMO));
                                                SpaceInvaders.asteroidesMortos = SpaceInvaders.asteroidesMortos + 1;
						File explosao = new File("Explosao.wav");
						SomExplosao(explosao);
						removerMe();
					}
					tiroVivo = false;
				}
			}
			Thread.sleep(50);
		}
		if (ehFim(ACIMA) && tiroVivo) {
			removerMe();
		}
	}

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("laserFurbot.png");
	}
}
