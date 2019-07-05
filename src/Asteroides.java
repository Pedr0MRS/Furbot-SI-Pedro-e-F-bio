
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

	boolean vivo = true;
	SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
	int velocidadeAsteroide;

	@Override
	public void executar() throws Exception {
		while (vivo) {
			for (int i = 0; i <= 4; i++) {
				if (SpaceInvaders.pause) {
					pausarAsteroides();
				}
				Thread.sleep(600);
				if (vivo) {
					andarEsquerda();
				}
			}
			for (int i = 0; i <= 4; i++) {
				if (SpaceInvaders.pause) {
					pausarAsteroides();
				}
				Thread.sleep(600);
				if (vivo) {
					andarDireita();
				}
			}
			if (SpaceInvaders.pause) {
				pausarAsteroides();
			}
			Thread.sleep(600);
			if (vivo) {
				andarAbaixo();
			}
			
			if (getY() == 9) {
				removerMe();
				break;
			}
		}
	}

	private void pausarAsteroides() throws InterruptedException {
		while (SpaceInvaders.pause) {
			Thread.sleep(2000);
		}
	}

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("asteroidIcon.png");
	}
}
