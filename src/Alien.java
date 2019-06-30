import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
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
           int randomTime = (int) (Math.random() * 2 + 1);
			if (randomTime == 1) {
				ProjeteisAlien projetil = new ProjeteisAlien();
				adicionarObjetoNoMundoXY(projetil, getX(), getY());
			}
           andarEsquerda();
           }
           while(!ehFim(DIREITA)){
           Thread.sleep(450);int randomTime = (int) (Math.random() * 2 + 1);
			if (randomTime == 1) {
				ProjeteisAlien projetil = new ProjeteisAlien();
				adicionarObjetoNoMundoXY(projetil, getX(), getY());
			}
           andarDireita();
           }
        }
    }

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("alien.png");
    }  
}
