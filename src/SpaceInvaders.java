import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {
    
    //<editor-fold defaultstate="collapsed" desc="Declarando Variáveis">
    boolean loop = true, pause = true, vivo = true;
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
    Morte teste = new Morte();
    //</editor-fold>
    
    
    
    public void QuantidadeVidas() {
        if (getObjeto(AQUIMESMO) != null) {
            nomeObj = getObjeto(AQUIMESMO).toString();
            Id = nomeObj.split("@");
            if ("ProjeteisAlien".equals(Id[0])) {
                removerObjetoDoMundo(arrayVidas[qtdVidas - 1]);
                qtdVidas--;
                vivo = false;
            }
        }
    }
    
    public int RetornaLevel(){
        return level;
    }
    
    public void ResetarVariaveis(){
        
    }
    
    public void RegistrarMorteAsteroide() {
        asteroidesMortos++;
    }

    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        
        while (loop) {
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
                if ((arrayAsteroides.length - asteroidesMortos) == 0) {
                    diga("Fase Completa");
                    break;
                }
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
                    	break;
                    case 32:
                        Projeteis laser = new Projeteis();
                        int x = getX();
                        int y = getY();
                        adicionarObjetoNoMundoXY(laser, x, y - 1);
                        break;
                }
                QuantidadeVidas();
            }
            //</editor-fold>

            if (qtdVidas == 0) {
                loop = false;
            }
        }
        if (qtdVidas == 0) {
            diga("GAMEOVER");
        }
        if (asteroidesMortos == 20) {
            diga("Fase Completa");
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

    public static void main(String[] args) {
        MundoVisual.iniciar("SpaceInvaders.xml");
    }
    
    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("nave.png");
    }

}
