
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

    boolean vivo = true;
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
            Thread.sleep(VelocidadePorLvl());
            andarAbaixo();
            if (getY() == 8) {
                if (getObjeto(AQUIMESMO) != null) {
                    SpaceInvaders.qtdVidas--;
                }
            }
            if (getY() == 9) {
                removerMe();
                break;
            }
            for (int i = 0; i <= 4; i++) {
                if (SpaceInvaders.pause) {
                    pausarAsteroides();
                }
                Thread.sleep(VelocidadePorLvl());
                andarDireita();
            }
            Thread.sleep(VelocidadePorLvl());
            andarAbaixo();
            if (SpaceInvaders.pause) {
                pausarAsteroides();
            }
            if (getY() == 8) {
                if (getObjeto(AQUIMESMO) != null) {
                    SpaceInvaders.qtdVidas--;
                }
            }
            if (getY() == 9) {
                SpaceInvaders.asteroidesMortos++;
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
