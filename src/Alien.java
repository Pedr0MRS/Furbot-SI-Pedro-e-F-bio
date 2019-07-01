import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;


public class Alien extends ObjetoDoMundoAdapter  {

    ProjeteisAlien projetil = new ProjeteisAlien();
	SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
	int level = ObjSpaceInvaders.RetornaLevel();
	int velocidadeAsteroide;
    	int random;
	
	public int VelocidadePorLvl(){		
			switch(level){
				case 1:
					return 550;
					break;
				case 2:
					return 450;
					break;
				case 3:
					return 400;
					break;
				default:
					return 550;
					break;				
			}	
		}
	
	@Override
    public void executar() throws Exception {
        while(true){           
           while(!ehFim(ESQUERDA)){
           Thread.sleep(VelocidadePorLvl());
           int randomTime = (int) (Math.random() * 2 + 1);
			if (randomTime == 1) {
				ProjeteisAlien projetil = new ProjeteisAlien();
				adicionarObjetoNoMundoXY(projetil, getX(), getY());
			}
           andarEsquerda();
           }
           while(!ehFim(DIREITA)){
           Thread.sleep(VelocidadePorLvl());
		int randomTime = (int) (Math.random() * 2 + 1);
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
