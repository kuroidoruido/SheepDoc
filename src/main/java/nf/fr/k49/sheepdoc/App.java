package nf.fr.k49.sheepdoc;

import com.alee.laf.WebLookAndFeel;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import nf.fr.k49.sheepdoc.state.State;
import nf.fr.k49.sheepdoc.ui.MainWindow;

public class App  {
    public static void main( String[] args ) {
        System.out.println("Starting SheepDoc...");
        final State state = new State();
        SwingUtilities.invokeLater(() -> {
            try {
                WebLookAndFeel.install();
                final MainWindow mainWindow = new MainWindow(state);
                mainWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
