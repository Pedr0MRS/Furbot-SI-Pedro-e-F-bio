
import br.furb.furbot.Direcao;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Alien extends ObjetoDoMundoAdapter {

    ProjeteisAlien projetil = new ProjeteisAlien();
    SpaceInvaders ObjSpaceInvaders = new SpaceInvaders();
    int level = ObjSpaceInvaders.RetornaLevel();
    int velocidadeAlien;
    int random;

    public int VelocidadePorLvl() {
        switch (level) {
            case 1:
                velocidadeAlien = 550;
                break;
            case 2:
                velocidadeAlien = 450;
                break;
            case 3:
                velocidadeAlien = 400;
                break;
            default:
                velocidadeAlien = 550;
                break;
        }

        return velocidadeAlien;
    }

    @Override
    public void executar() throws Exception {
        while (true) {
            while (!ehFim(ESQUERDA)) {
                Thread.sleep(VelocidadePorLvl());
                int randomTime = (int) (Math.random() * 2 + 1);
                if (randomTime == 1) {
                    ProjeteisAlien projetil = new ProjeteisAlien();
                    adicionarObjetoNoMundoXY(projetil, getX(), getY());
                    File tiro = new File("Tiro.wav");
                    SomTiro(tiro);
                }
                if (SpaceInvaders.pause) {
                	pausarAlien();
        		}
                andarEsquerda();
            }
            while (!ehFim(DIREITA)) {
                Thread.sleep(VelocidadePorLvl());
                int randomTime = (int) (Math.random() * 2 + 1);
                if (randomTime == 1) {
                    ProjeteisAlien projetil = new ProjeteisAlien();
                    adicionarObjetoNoMundoXY(projetil, getX(), getY());
                    File tiro = new File("Tiro.wav");
                    SomTiro(tiro);
                }
                if (SpaceInvaders.pause) {
        			pausarAlien();
        		}
                andarDireita();
            }
        }
    }

    private static void SomTiro(File Sound)
    {
    	try 
    	{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} 
    	catch (Exception e) 
    	{
			
		}
    }
    
    private void pausarAlien() throws InterruptedException {
    	while(SpaceInvaders.pause) {
    		Thread.sleep(2000);
    	}
	}

	@Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("alien.png");
    }
}
