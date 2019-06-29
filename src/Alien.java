
import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
import static br.furb.furbot.ObjetoDoMundoAdapter.ABAIXO;
import static br.furb.furbot.ObjetoDoMundoAdapter.ACIMA;
import static br.furb.furbot.ObjetoDoMundoAdapter.DIREITA;
import static br.furb.furbot.ObjetoDoMundoAdapter.ESQUERDA;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;


public class Alien extends ObjetoDoMundoAdapter  {

    ProjeteisAlien projetil = new ProjeteisAlien();
    int random;
    @Override
    public void executar() throws Exception {
        while(true){           
           while(!ehFim(ESQUERDA)){
           Thread.sleep(450);
           ProjeteisAlien projetil = new ProjeteisAlien();
           adicionarObjetoNoMundoXY(projetil,getX(),getY());
           andarEsquerda();
           }
           while(!ehFim(DIREITA)){
           Thread.sleep(450);
           ProjeteisAlien projetil = new ProjeteisAlien();
           adicionarObjetoNoMundoXY(projetil,getX(),getY());
           andarDireita();
           }
        }
    }

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("alien.png");
    }
    
    private void andar(Direcao direcao) {

        switch (direcao) {
            case DIREITA:
                andarDireita();
                break;
            case ESQUERDA:
                andarEsquerda();
                break;
            case ACIMA:
                andarAcima();
                break;
            case ABAIXO:
                andarAbaixo();
                break;
            default:
                System.out.println("DIREÇÃO INVÁLIDA");
                break;
        }
    }

    private Direcao mudarDirecao(Direcao direcao) {

        switch (direcao) {
            case DIREITA:
                direcao = ESQUERDA;
                break;
            case ESQUERDA:
                direcao = DIREITA;
                break;
            case ACIMA:
                direcao = ABAIXO;
                break;
            case ABAIXO:
                direcao = ACIMA;
                break;
            default:
                System.out.println("DIREÇÃO INVÁLIDA");
                break;
        }
        return direcao;
    }
    
}
