
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;


public class Vidas extends ObjetoDoMundoAdapter {

    @Override
    public void executar() throws Exception {
        
    }

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("vida.png");
    }
    
}
