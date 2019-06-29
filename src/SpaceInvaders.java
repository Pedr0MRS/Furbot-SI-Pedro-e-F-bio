//teste

import br.furb.furbot.Furbot;
import br.furb.furbot.MundoVisual;
import br.furb.furbot.suporte.LoadImage;
import javax.swing.ImageIcon;

public class SpaceInvaders extends Furbot {
    
    boolean loop = true, pause = true, vivo = true,emPausa = false;
    int linhaAsteroide = 1;
    int colunaAsteroide = 9;
    int colunaVida = 9;
    int nAsteroidesAdc = 0;
    int nAsteroidesCriados = 0;
    int nVidasAdc = 0;
    int nVidasCriadas = 0;
    int qtdVidas = 3;
    String nomeObj;
    String[] Id;
    Morte teste = new Morte();
    @Override
    public ImageIcon buildImage() {
        return LoadImage.getInstance().getIcon("nave.png");
    }
    
//    public void QuantidadeVidas(){
//        if (getObjeto(AQUIMESMO) != null) {
//            nomeObj = getObjeto(AQUIMESMO).toString();
//                Id = nomeObj.split("@");
//                if ("ProjeteisAlien".equals(Id[0])) {   
//                   diga("Entrei");
//                   qtdVidas--;
//                   loop = false;
//                }
//        }
//    }
    
    @Override
    public void inteligencia() throws Exception {
        limparConsole();
        diga("Rodou "+ teste.RetirarVidas() );
        //1--------Criando Objetos----------
        
        Alien alien = new Alien();
        Asteroides[] arrayAsteroides = new Asteroides[20];
        Vidas[] arrayVidas = new Vidas[qtdVidas];
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
        //1---------------------------------
        
        //2--Adicionando Objetos ao mundo---
        
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
                adicionarObjetoNoMundoXY(arrayVidas[nVidasAdc],(i-1),9);
                nVidasAdc++;
                colunaVida--;
            }   
        }
        
        adicionarObjetoNoMundoXY(alien, 9, 0);
        
        //2----------------------------------
        
        //3-------Loop Principal do Jogo-----
        while (loop) {
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
                case 32:
                    Projeteis laser = new Projeteis();
                    int x = getX();
                    int y = getY();
                    adicionarObjetoNoMundoXY(laser, x, y-1);
                    break;
            }
//            QuantidadeVidas();
        }
        //3----------------------------------
        diga("MORREU!!!!");
    }
    
    public static void main(String[] args) {
        MundoVisual.iniciar("SpaceInvaders.xml");
    }

}
