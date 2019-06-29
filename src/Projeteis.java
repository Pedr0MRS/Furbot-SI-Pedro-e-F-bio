
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class Projeteis extends ObjetoDoMundoAdapter {

    Asteroides a = new Asteroides();
    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("laser.png");
    }

    @Override
    public void executar() throws Exception {
        while (!ehFim(ACIMA)) {
            andarAcima();
            if (getObjeto(AQUIMESMO) != null) 
            {
                Thread.sleep(5);
                
                //a.Morreu();
                removerObjetoDoMundo(getObjeto(AQUIMESMO));
                removerMe();
                break;
            }
                Thread.sleep(50);
        }
        if (ehFim(ACIMA)) {
            removerMe();
        }
    }
    public boolean Morreu(){
        return true;
        
    }
}
