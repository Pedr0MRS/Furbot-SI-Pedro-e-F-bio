
import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class Alien extends ObjetoDoMundoAdapter {

    ProjeteisAlien projetil = new ProjeteisAlien();
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    int level = ObjSpaceInvaders.RetornaLevel();
    int velocidadeAlien;
    int random;

    public int VelocidadePorLvl() {
        switch (level) {
            case 1:
                velocidadeAlien = 550;
                break;
            case 2:
                velocidadeAlien = 450;
                break;
            case 3:
                velocidadeAlien = 400;
                break;
            default:
                velocidadeAlien = 550;
                break;
        }

        return velocidadeAlien;
    }

    @Override
    public void executar() throws Exception {
        while (true) {
            while (!ehFim(ESQUERDA)) {
                Thread.sleep(VelocidadePorLvl());
                int randomTime = (int) (Math.random() * 2 + 1);
                if (randomTime == 1) {
                    ProjeteisAlien projetil = new ProjeteisAlien();
                    adicionarObjetoNoMundoXY(projetil, getX(), getY());
                }
                andarEsquerda();
            }
            while (!ehFim(DIREITA)) {
                Thread.sleep(VelocidadePorLvl());
                int randomTime = (int) (Math.random() * 2 + 1);
                if (randomTime == 1) {
                    ProjeteisAlien projetil = new ProjeteisAlien();
                    adicionarObjetoNoMundoXY(projetil, getX(), getY());
                }
                andarDireita();
            }
        }
    }

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("alien.png");
    }
}
