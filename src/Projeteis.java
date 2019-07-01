import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class Projeteis extends ObjetoDoMundoAdapter {

    Asteroides ObjAsteroide = new Asteroides();
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    String nomeObj;
    String[] Id;

    @Override
    public void executar() throws Exception {
        while (!ehFim(ACIMA)) {
            andarAcima();
            if (getObjeto(AQUIMESMO) != null) {
                nomeObj = getObjeto(AQUIMESMO).toString();
                Id = nomeObj.split("@");
                if ("Asteroides".equals(Id[0])) {
                    ObjSpaceInvaders.RegistrarMorteAsteroide();
                    diga("Entrei Tiro Furbot");
                }
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
    
     @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("laser.png");
    }
}
