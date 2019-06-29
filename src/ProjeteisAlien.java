
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;
import static br.furb.furbot.ObjetoDoMundoAdapter.AQUIMESMO;

public class ProjeteisAlien extends ObjetoDoMundoAdapter {

    Asteroides a = new Asteroides();
    SpaceInvaders Obj = new SpaceInvaders();
    String nomeObj;
    int Y = 0;

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("laser.png");
    }

    @Override
    public void executar() throws Exception {
        while (Y != 7) {
            Y = getY();
            andarAbaixo();

            if (getObjeto(AQUIMESMO) != null) {
                nomeObj = getObjeto(AQUIMESMO).toString();
                String[] Id = nomeObj.split("@");

                if ("SpaceInvaders".equals(Id[0])) {     
                    break;
                } else {

                    Thread.sleep(5);
                    removerObjetoDoMundo(getObjeto(AQUIMESMO));
                    removerMe();
                    break;
                }
            }
            Thread.sleep(50);
        }
        if (Y == 7) {
            removerMe();
        }
    }

    public boolean Morreu() {
        return true;

    }
}
