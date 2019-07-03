import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class Projeteis extends ObjetoDoMundoAdapter {

	Asteroides ObjAsteroide = new Asteroides();
	SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
	int Morto = 0;
	String nomeObj;
	String[] Id;

	@Override
	public void executar() throws Exception {
		while (!ehFim(ACIMA)) {
			if (SpaceInvaders.pause) {
				pausarTiros();
			}
			andarAcima();
			if (getObjeto(AQUIMESMO) != null) {
				nomeObj = getObjeto(AQUIMESMO).toString();
				Id = nomeObj.split("@");
				if ("Asteroides".equals(Id[0])) {
					Morto++; // codigo anterior : ObjSpaceInvaders.RegistrarMorteAsteroide();
				}
				removerObjetoDoMundo(getObjeto(AQUIMESMO));
				File explosao = new File("Explosao.wav");
                SomExplosao(explosao);
				removerMe();
				break;
			}
			Thread.sleep(50);
		}
		if (ehFim(ACIMA)) {
			removerMe();
		}
	}

	private void SomExplosao(File Sound) {
		try 
    	{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} 
    	catch (Exception e) 
    	{
			
		}	
	}

	private void pausarTiros() throws InterruptedException {
		while (SpaceInvaders.pause) {
			Thread.sleep(2000);
		}
	}

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("laserFurbot.png");
	}
}
