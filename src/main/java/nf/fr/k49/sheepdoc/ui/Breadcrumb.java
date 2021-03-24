package nf.fr.k49.sheepdoc.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.alee.extended.breadcrumb.WebBreadcrumb;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;

public class Breadcrumb extends JPanel {

    private static final String SELECT_TEMPLATE = "Select template"; 
    private static final String FILL_TEMPLATE = "Fill Template"; 
    private static final String SAVE_FILE = "Save"; 

    private List<WebButton> buttons;
    private WebBreadcrumb breadcrumb;
    private Consumer<Integer> callback;

    public Breadcrumb() {
        initBreadcrumb();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this.breadcrumb);
    }

    private void initBreadcrumb() {
        this.breadcrumb = new WebBreadcrumb();
        this.buttons = new ArrayList<>();
        this.buttons.add(new WebButton(SELECT_TEMPLATE));
        this.buttons.add(new WebButton(FILL_TEMPLATE));
        this.buttons.add(new WebButton(SAVE_FILE));
        for (int i=0 ; i<this.buttons.size() ; i++) {
            final int index = i;
            final WebButton btn = this.buttons.get(i);
            btn.addActionListener((action) -> this.callback.accept(index));
            this.breadcrumb.add(btn);
        }
    }

    public void onEntrySelection(Consumer<Integer> callback) {
        this.callback = callback;
    }
}