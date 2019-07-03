
import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import br.furb.furbot.*;

public class ProjeteisAlien extends ObjetoDoMundoAdapter {

    Asteroides ObjAsteroides = new Asteroides();
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    int level = ObjSpaceInvaders.RetornaLevel();
    int velocidadeProjetilAlien;
    String nomeObj;
    String[] Id;
    int Y = 0;

    public int VelocidadePorLvl() {
        switch (level) {
            case 1:
                velocidadeProjetilAlien = 65;
                break;
            case 2:
                velocidadeProjetilAlien = 55;
                break;
            case 3:
                velocidadeProjetilAlien = 45;
                break;
            default:
                velocidadeProjetilAlien = 65;
                break;
        }
        return velocidadeProjetilAlien;
    }

    @Override
    public void executar() throws Exception {
        while (Y != 7) {
            if (SpaceInvaders.pause) {
                pausarAsteroides();
            }
            Y = getY();
            andarAbaixo();
            Thread.sleep(VelocidadePorLvl());
        }
        if (Y == 7) {
            //inicio add
            if ("SpaceInvaders".equals(Id[0])) {
                break; //verificar para enviar diminuição de vida
            }
            //fim add
            removerMe();
        }
    }
    
    private void pausarAsteroides() throws InterruptedException {
    	while(SpaceInvaders.pause) {
    		Thread.sleep(2000);
    	}
	}

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("laser.png");
    }
}
