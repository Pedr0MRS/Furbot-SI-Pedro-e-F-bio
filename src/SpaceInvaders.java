import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.Numero;
import br.furb.furbot.suporte.LoadImage;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {

    //<editor-fold defaultstate="collapsed" desc="Declarando Variaveis">
    boolean LoopDeFase = true;
    boolean LoopPrincipal = true;
    public static boolean pause = false;
    public static int qtdVidas = 3;
    public static int asteroidesMortos = 0;
    boolean vivo = true;
    int linhaAsteroide = 1;
    int colunaAsteroide = 9;
    int colunaVida = 9;
    int nAsteroidesAdc = 0;
    int nAsteroidesCriados = 0;
    int nVidasAdc = 0;
    int nVidasCriadas = 0;
    int nAliensCriados = 0;
    int infoVidas;
    int level = 1;
    Vidas[] arrayVidas = new Vidas[qtdVidas];
    Alien[] arrayAlien = new Alien[2];
    String nomeObj;
    String[] Id;
    //</editor-fold>

    public void QuantidadeVidasHUD() {
    	if (getObjetoXY(3, 9) != null) {
            removerObjetoDoMundo(getObjetoXY(3, 9));
        } else if (getObjetoXY(2, 9) != null) {
            removerObjetoDoMundo(getObjetoXY(2, 9));
        } else {
            removerObjetoDoMundo(getObjetoXY(1, 9));
        }
    }
    
     public int RetornaLevel() {
        return level;
    }

    public void ResetarVariaveis() throws InterruptedException {
        LoopDeFase = true;
        pause = false;
        vivo = true;
        linhaAsteroide = 1;
        colunaAsteroide = 9;
        colunaVida = 9;
        nAsteroidesAdc = 0;
        nAsteroidesCriados = 0;
        nVidasAdc = 0;
        nVidasCriadas = 0;
        nAliensCriados = 0;
        asteroidesMortos = 0;
        Thread.sleep(2000);
    }
    
    private void loopPausa() {
        while (pause) {
            int teclaPause = getUltimaTeclaPress();
            if (teclaPause == 80) {            	
                pause = false;
            }
        }
    }

    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        qtdVidas = 3;
        asteroidesMortos = 0;
        Numero Nivel = new Numero();
        Nivel.setValor(level);
        adicionarObjetoNoMundoXY(Nivel, 8, 9);
        while (LoopPrincipal) {
            while (LoopDeFase) {                
                //<editor-fold defaultstate="collapsed" desc="Criando Objetos">
                Alien alien = new Alien();
                arrayAlien[0] = alien;
                Asteroides[] arrayAsteroides = new Asteroides[20];

                while (nAsteroidesCriados < 20) {
                    Asteroides fabricaAsteroide = new Asteroides();
                    arrayAsteroides[nAsteroidesCriados] = fabricaAsteroide;
                    nAsteroidesCriados++;
                }
                while (nVidasCriadas < qtdVidas) {
                    Vidas fabricaVidas = new Vidas();
                    arrayVidas[nVidasCriadas] = fabricaVidas;
                    nVidasCriadas++;
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Colocando Objetos no mundo">
                for (int i = 1; nAsteroidesAdc < 20; i++) {
                    while (linhaAsteroide < 5) {
                        adicionarObjetoNoMundoXY(arrayAsteroides[nAsteroidesAdc], colunaAsteroide, linhaAsteroide);
                        linhaAsteroide++;
                        nAsteroidesAdc++;
                    }
                    colunaAsteroide--;
                    linhaAsteroide = 1;
                }

                for (int i = 1; nVidasAdc < qtdVidas; i++) {
                    if (!(colunaVida < 0)) {
                        adicionarObjetoNoMundoXY(arrayVidas[nVidasAdc], (i - 1), 9);
                        nVidasAdc++;
                        colunaVida--;
                    }
                }

                if (nAliensCriados == 0) {
                    adicionarObjetoNoMundoXY(alien, 9, 0);
                    nAliensCriados++;
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Loop de Vida do Furbot">
                while (vivo) {
                    int tecla = getUltimaTeclaPress();
                    switch (tecla) {
                        case TECLADIREITA:
                            if (!ehFim(DIREITA)) {
                                andarDireita();
                            }
                            break;
                        case TECLAESQUERDA:
                            if (!ehFim(ESQUERDA)) {
                                andarEsquerda();
                            }
                            break;
                        case 80:
                            pause = true;
                            diga("Jogo pausado");
                            loopPausa();
                            limparConsole();
                            break;
                        case 32:
                            Projeteis laser = new Projeteis();
                            int x = getX();
                            int y = getY();
                            adicionarObjetoNoMundoXY(laser, x, y - 1);
                            File tiro = new File("Tiro.wav");
                            SomTiro(tiro);
                            break;
                    }
                    if (infoVidas > qtdVidas) {
                        QuantidadeVidasHUD();
                    }
                    if (asteroidesMortos == 20) {
                        diga("FASE COMPLETA");
                        vivo = false;
                    }
                    infoVidas = qtdVidas;
                    if (qtdVidas == 0) {
                        vivo = false;
                    }
                }
                //</editor-fold>           
            }
            if (qtdVidas == 0) {
                LoopPrincipal = false;
            }
            if (asteroidesMortos == 20) {
                ResetarVariaveis();
                level++;
            }
        }
        if (qtdVidas == 0) {
        	File fimJogo = new File("GameOver.wav");
        	SomFimDeJogo(fimJogo);
            diga("GAMEOVER");
            LoopPrincipal = false;
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
	
	private void SomFimDeJogo(File Sound) {
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

    public static void main(String[] args) {
        MundoVisual.iniciar("SpaceInvaders.xml");
    }

    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("nave.png");
    }

}
