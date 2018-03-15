import javax.swing.SwingUtilities;
import gui.TFFAGui;

public class Tffa{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {new TFFAGui();}
		});
	}
}