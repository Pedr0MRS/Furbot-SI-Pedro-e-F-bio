
import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.Numero;
import br.furb.furbot.suporte.LoadImage;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {

    boolean LoopDeVida = true;
    boolean CriarFase = true;
    boolean LoopPrincipal = true;
    public static boolean pause = false;
    static int asteroidesMortos = 0;
    int qtdVidas = 3;
    int linhaAsteroide = 1;
    int colunaAsteroide = 9;
    int colunaVida = 9;
    int nAsteroidesAdc = 0;
    int nAsteroidesCriados = 0;
    int nVidasAdc = 0;
    int nVidasCriadas = 0;
    int nAliensCriados = 0;
    int nAliensAdc = 0;
    int infoVidas;
    int level = 1;
    Vidas[] arrayVidas = new Vidas[10];
    Alien[] arrayAlien = new Alien[3];
    String nomeObj;
    String[] Id;

    public void QuantidadeVidasHUD() {
        if (getObjetoXY(3, 9) != null) {
            removerObjetoDoMundo(getObjetoXY(3, 9));
        } else if (getObjetoXY(2, 9) != null) {
            removerObjetoDoMundo(getObjetoXY(2, 9));
        } else if (getObjetoXY(1, 9) != null) {
            removerObjetoDoMundo(getObjetoXY(1, 9));
        }
    }

    private void loopPausa() {
        while (pause) {
            int teclaPause = getUltimaTeclaPress();
            if (teclaPause == 80) {
                pause = false;
            }
        }
    }

    private static void SomTiro(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {

        }
    }

    private static void SomFimDeJogo(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {

        }
    }

    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        qtdVidas = 3;
        asteroidesMortos = 0;
        Asteroides[] arrayAsteroides = new Asteroides[60];

        while (LoopPrincipal) {
            if (CriarFase) {
                LoopDeVida = true;
                nAsteroidesAdc = 0;
                nAliensAdc = 0;
                linhaAsteroide = 1;
                colunaAsteroide = 9;
                nAliensCriados = 0;
                Numero Nivel = new Numero();
                Nivel.setValor(level);
                adicionarObjetoNoMundoXY(Nivel, 8, 9);
                

                for (nVidasCriadas = 0; nVidasCriadas < qtdVidas; nVidasCriadas++) {
                    Vidas fabricaVidas = new Vidas();
                    arrayVidas[nVidasCriadas] = fabricaVidas;
                }

                for (int i = 1; nVidasAdc < qtdVidas; i++) {
                    if (!(colunaVida < 0)) {
                        if (getObjetoXY(i, 9) == null) {
                            adicionarObjetoNoMundoXY(arrayVidas[nVidasAdc], (i), 9);
                            nVidasAdc++;
                        }
                    }
                }

                for (nAsteroidesCriados = 0; nAsteroidesCriados < 20; nAsteroidesCriados++) {
                    Asteroides fabricaAsteroide = new Asteroides();
                    arrayAsteroides[nAsteroidesCriados] = fabricaAsteroide;
                }

                for (int i = 1; nAsteroidesAdc < 20; i++) {
                    while (linhaAsteroide < 5) {
                        adicionarObjetoNoMundoXY(arrayAsteroides[nAsteroidesAdc], colunaAsteroide, linhaAsteroide);
                        linhaAsteroide++;
                        nAsteroidesAdc++;
                    }
                    colunaAsteroide--;
                    linhaAsteroide = 1;
                }

//                for (int i = 0; nAliensAdc <= 1; i++) {
//                    Alien alien = new Alien();
//                    arrayAlien[nAliensAdc] = alien;
//                    nAliensAdc++;
//                }
//                for (int i = 0; nAliensCriados <= 1; i++) {
//                    adicionarObjetoNoMundoXY(arrayAlien[nAliensCriados], 9, 0);
//                    nAliensCriados++;
//                }

                CriarFase = false;
            }

            while (LoopDeVida) {
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

                infoVidas = qtdVidas;

                if (asteroidesMortos == 20) {
                    diga("Fase "+ level +" Completa");
                    asteroidesMortos = 0;
                    for (int i = 1; qtdVidas <= 3; i++) {
                        qtdVidas++;
                    }
                    CriarFase = true;
                    LoopDeVida = false;
                    level++;
                    Thread.sleep(2000);

                }
                if (qtdVidas == 0) {
                    File fimJogo = new File("GameOver.wav");
                    SomFimDeJogo(fimJogo);
                    diga("GAMEOVER");
                    Thread.sleep(200);
                    LoopDeVida = false;
                }
            }
            if (qtdVidas == 0) {
                break;
            }
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
