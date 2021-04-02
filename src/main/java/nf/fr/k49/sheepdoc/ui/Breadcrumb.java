package nf.fr.k49.sheepdoc.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Breadcrumb extends JPanel {

    private static final String SELECT_TEMPLATE = "Select template"; 
    private static final String FILL_TEMPLATE = "Fill Template"; 
    private static final String SAVE_FILE = "Save"; 

    private List<JButton> buttons;
    private Consumer<Integer> callback;

    public Breadcrumb() {
        initBreadcrumb();
        this.setLayout(new GridLayout(1,3));
    }

    private void initBreadcrumb() {
        this.buttons = new ArrayList<>();
        this.buttons.add(new JButton(SELECT_TEMPLATE));
        this.buttons.add(new JButton(FILL_TEMPLATE));
        this.buttons.add(new JButton(SAVE_FILE));
        for (int i=0 ; i<this.buttons.size() ; i++) {
            final int index = i;
            final JButton btn = this.buttons.get(i);
            btn.addActionListener((action) -> this.callback.accept(index));
            this.add(btn);
        }
    }

    public void onEntrySelection(Consumer<Integer> callback) {
        this.callback = callback;
    }
}