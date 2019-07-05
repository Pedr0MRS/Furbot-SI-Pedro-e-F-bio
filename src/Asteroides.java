
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

	boolean vivo = true;
	SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
	int velocidadeAsteroide;
	int level = ObjSpaceInvaders.RetornarLevel();

	public int VelocidadePorLvl() {
		switch (level) {
		    case 1:
			velocidadeProjetilAlien = 600;
			break;
		    case 2:
			velocidadeProjetilAlien = 500;
			break;
		    case 3:
			velocidadeProjetilAlien = 400;
			break;
		    default:
			velocidadeProjetilAlien = 600;
			break;
		}
        return velocidadeAsteroide;
    	}
	
	@Override
	public void executar() throws Exception {
		while (vivo) {
			for (int i = 0; i <= 4; i++) {
				if (SpaceInvaders.pause) {
					pausarAsteroides();
				}
				Thread.sleep(VelocidadePorLvl());
				if (vivo) {
					andarEsquerda();
				}
			}
			for (int i = 0; i <= 4; i++) {
				if (SpaceInvaders.pause) {
					pausarAsteroides();
				}
				Thread.sleep(VelocidadePorLvl());
				if (vivo) {
					andarDireita();
				}
			}
			if (SpaceInvaders.pause) {
				pausarAsteroides();
			}
			Thread.sleep(VelocidadePorLvl());
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
