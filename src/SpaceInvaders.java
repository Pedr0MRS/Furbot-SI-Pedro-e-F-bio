
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
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
    int asteroidesMortos = 0;
    int level = 0;
    Vidas[] arrayVidas = new Vidas[qtdVidas];
    String nomeObj;
    String[] Id;
    //</editor-fold>

    public void QuantidadeVidas() {
        if (getObjeto(AQUIMESMO) != null) {
            //nomeObj = getObjeto(AQUIMESMO).toString();
            //Id = nomeObj.split("@");
            //if ("ProjeteisAlien".equals(Id[0])) {
            removerObjetoDoMundo(arrayVidas[qtdVidas - 1]);
            qtdVidas--;
            vivo = false;
            //}
        }
    }

    public void ResetarVariaveis() {
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
        qtdVidas = 3;
        asteroidesMortos = 20;
    }

    public int RetornaLevel() {
        return level;
    }

    public void RegistrarMorteAsteroide() {
        asteroidesMortos--;
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

        while (LoopPrincipal) {
            while (LoopDeFase) {
                vivo = true;
                //<editor-fold defaultstate="collapsed" desc="Criando Objetos">
                Alien alien = new Alien();
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
                    if (qtdVidas == 0) {
                    	//LoopDeFase = false;
                    }
                }
                //</editor-fold>           
            }
            if (qtdVidas == 0) {
                LoopPrincipal = false;
            }
            if (asteroidesMortos == 20) {
                diga("Fase Completa");
                level++;
                LoopDeFase = true;
                ResetarVariaveis();
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
