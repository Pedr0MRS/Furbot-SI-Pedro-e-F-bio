
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;
import java.util.Scanner;

public class Asteroides extends ObjetoDoMundoAdapter {

    boolean vivo = true;
    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("asteroidIcon.png");
    }

    public void Morreu(){
        vivo = false;
    }
    
    @Override
    public void executar() throws Exception {
        int ct = 0, regulador = -1;
        Direcao direcao = ESQUERDA;
        boolean cofirmacao;
        
        while (vivo) {

            
            for (int i = 0; i <= 4; i++) {
               Thread.sleep(500);
                andar(ESQUERDA);
            }
            for (int i = 0; i <= 4; i++) {
                Thread.sleep(500);
                andar(DIREITA);            
            }
            Thread.sleep(500);
            andar(ABAIXO);
            
        }
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
