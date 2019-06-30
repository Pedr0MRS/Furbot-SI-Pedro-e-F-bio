
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class ProjeteisAlien extends ObjetoDoMundoAdapter {

    Asteroides ObjAsteroides = new Asteroides();
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    String nomeObj;
    String[] Id;
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
                Id = nomeObj.split("@");
                
                if("Asteroides".equals(Id[0])){
                    ObjSpaceInvaders.RegistrarMorteAsteroide();
                }
                if ("SpaceInvaders".equals(Id[0])) {     
                    break;
                } 
                else{
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
}
