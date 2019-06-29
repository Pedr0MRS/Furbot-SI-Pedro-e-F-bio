
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {

    boolean loop = true, pause = true, vivo = true;
    int linhas = 1;
    int coluna = 9;
    int ct = 0;
    int vidas = 3;

    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("nave.png");
    }

    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        boolean emPausa = false;

        //int teclaAcima = getUltimaTeclaPress();
        //if (teclaAcima == TECLACIMA) {

        //}

        Alien alien = new Alien();
        adicionarObjetoNoMundoXY(alien, 9, 0);
        Asteroides[] asteroides = new Asteroides[20];
        int a = 0;
        while (a < 20) {
            Asteroides Asteroide0 = new Asteroides();
            asteroides[a] = Asteroide0;
            a++;
        }
        for (int i = 1; ct < 20; i++) {

            while (linhas < 5) {
                adicionarObjetoNoMundoXY(asteroides[ct], coluna, linhas);
                linhas++;
                ct++;

            }
            coluna--;
            linhas = 1;
        }
        while (loop) {

            if (getObjeto(AQUIMESMO) != null) {
                vidas--;
                
            }

            int tecla = getUltimaTeclaPress();
            switch (tecla) {
                case TECLADIREITA:
                    if (!ehFim(DIREITA)) {
                        andarDireita();
                    }
                    break;
                case TECLAESQUERDA:
                    if (!ehFim(ESQUERDA)) {
                        andarEsquerda();
                    }
                    break;
                case 32:
                    Projeteis laser = new Projeteis();
                    int x = getX();
                    int y = getY();
                    adicionarObjetoNoMundoXY(laser, x, y);
                    break;
            }
//            }
        }
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("SpaceInvaders.xml");
    }

}
