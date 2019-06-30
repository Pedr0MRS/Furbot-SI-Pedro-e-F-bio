import javax.swing.ImageIcon;
import br.furb.furbot.ObjetoDoMundoAdapter;
import br.furb.furbot.suporte.LoadImage;

public class Asteroides extends ObjetoDoMundoAdapter {

	boolean vivo = true;

	@Override
	public ImageIcon buildImage() {
		return LoadImage.getInstance().getIcon("asteroidIcon.png");
	}

	public void Morreu() {
		vivo = false;
	}

	@Override
	public void executar() throws Exception {
		while (vivo) {
			for (int i = 0; i <= 4; i++) {
				Thread.sleep(500);
				andarEsquerda();
			}
			for (int i = 0; i <= 4; i++) {
				Thread.sleep(500);
				andarDireita();
			}
			Thread.sleep(500);
			andarAbaixo();

			if (getY() == 9) {
				removerMe();
			}
		}
	}
}
