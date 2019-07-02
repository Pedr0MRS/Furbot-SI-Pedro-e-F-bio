
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

    boolean vivo = true;
    boolean pause = false;
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    int level = ObjSpaceInvaders.RetornaLevel();
    int velocidadeAsteroide;

    public int VelocidadePorLvl() {
        switch (level) {
            case 1:
                velocidadeAsteroide = 600;
                break;
            case 2:
                velocidadeAsteroide = 500;
                break;
            case 3:
                velocidadeAsteroide = 400;
                break;
            default:
                velocidadeAsteroide = 600;
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
                andarEsquerda();
            }
            for (int i = 0; i <= 4; i++) {
            	if (SpaceInvaders.pause) {
        			pausarAsteroides();
        		}
                Thread.sleep(500);
                andarDireita();
            }
            if (SpaceInvaders.pause) {
    			pausarAsteroides();
    		}
            Thread.sleep(500);
            andarAbaixo();

            if (getY() == 9) {
                removerMe();
                break;
            }
        }
    }

    private void pausarAsteroides() throws InterruptedException {
    	while(SpaceInvaders.pause) {
    		Thread.sleep(2000);
    	}
	}

	@Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("asteroidIcon.png");
    }
}
