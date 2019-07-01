import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

	boolean vivo = true;
	SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
	int level = ObjSpaceInvaders.RetornaLevel();
	int velocidadeAsteroide;
	
	public int VelocidadePorLvl(){		
		switch(level){
			case 1:
				return 600;
				break;
			case 2:
				return 500;
				break;
			case 3:
				return 400;
				break;
			default:
				return 600;
				break;				
		}	
	}

	@Override
	public void executar() throws Exception {
		while (vivo) {
			for (int i = 0; i <= 4; i++) {
				Thread.sleep(VelocidadePorLvl);
				andarEsquerda();
			}
			for (int i = 0; i <= 4; i++) {
				Thread.sleep(500);
				andarDireita();
			}
			Thread.sleep(500);
			andarAbaixo();

			if (getY() == 9) {
				removerMe();
                                break;
			}
		}
	}
	
	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("asteroidIcon.png");
	}
}
