
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.Numero;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {

    //<editor-fold defaultstate="collapsed" desc="Declarando Variaveis">
    boolean LoopDeFase = true;
    boolean LoopPrincipal = true;
    public static boolean pause = false;
    boolean vivo = true;
    int linhaAsteroide = 1;
    int colunaAsteroide = 9;
    int colunaVida = 9;
    int nAsteroidesAdc = 0;
    int nAsteroidesCriados = 0;
    int nVidasAdc = 0;
    int nVidasCriadas = 0;
    int nAliensCriados = 0;
    int qtdVidas = 3;
    int infoVidas;
    int asteroidesMortos = 0;
    int level = 1;
    Vidas[] arrayVidas = new Vidas[qtdVidas];
    Alien[] arrayAlien = new Alien[2];
    String nomeObj;
    String[] Id;
    //</editor-fold>

    public void QuantidadeVidas() {
        if (getObjeto(AQUIMESMO) != null) {
            removerObjetoDoMundo(arrayVidas[qtdVidas - 1]);
            qtdVidas--;
            vivo = false;
        }
    }
    
     public int RetornaLevel() {
        return level;
    }

    public void ResetarVariaveis() throws InterruptedException {
        LoopDeFase = true;
        pause = true;
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
        ResetarMapa resetar = new ResetarMapa();
        adicionarObjetoNoMundoXY(resetar,0,0);
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
        Numero Nivel = new Numero();
        Nivel.setValor(level);
        adicionarObjetoNoMundoXY(Nivel, 8, 9);
        while (LoopPrincipal) {
            while (LoopDeFase) {
                vivo = true;
                
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
                            break;
                    }
                    QuantidadeVidas();
                    if (qtdVidas < infoVidas) {
                        LoopDeFase = false;
                    } 
                    if (qtdVidas == 0) {
                    	LoopDeFase = false;
                    }
                }
                //</editor-fold>           
            }
            if (qtdVidas == 0) {
                LoopPrincipal = false;
            }
        }
        if (qtdVidas == 0) {
            diga("GAMEOVER");
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
