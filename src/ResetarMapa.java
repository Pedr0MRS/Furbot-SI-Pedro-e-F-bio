
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.Numero;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class ResetarMapa extends ObjetoDoMundoAdapter {
    int linhas = 0,volta = 0;
    @Override
    public void executar() throws Exception {
        while(!ehFim(DIREITA)){
            removerObjetoDoMundo(getObjeto(AQUIMESMO));
            andarDireita();
        }
        while(!ehFim(ESQUERDA)){
            removerObjetoDoMundo(getObjeto(AQUIMESMO));
            andarEsquerda();
        }
        removerMe();
    }

    @Override
    public ImageIcon buildImage() {
        return null;
    }

}
