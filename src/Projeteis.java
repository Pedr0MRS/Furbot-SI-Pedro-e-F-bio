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
                            if ("Alien".equals(Id[0])||"ProjeteisAlien".equals(Id[0])) {
                                removerObjetoDoMundo(getObjeto(AQUIMESMO));
				removerMe();
				break;
                            }
                                SpaceInvaders.asteroidesMortos++;
                                diga(""+SpaceInvaders.asteroidesMortos);
				removerObjetoDoMundo(getObjeto(AQUIMESMO));
				removerMe();
				break;
			}
			Thread.sleep(50);
		}
		if (ehFim(ACIMA)) {
			removerMe();                      
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
