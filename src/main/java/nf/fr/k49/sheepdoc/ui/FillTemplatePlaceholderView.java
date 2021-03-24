package nf.fr.k49.sheepdoc.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import nf.fr.k49.sheepdoc.helper.ContentChangeListener;
import nf.fr.k49.sheepdoc.state.State;

public class FillTemplatePlaceholderView extends AbstractMainWindowView {

    public FillTemplatePlaceholderView(final State state) {
        super(state);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(this.setupGrid(this.getState())), BorderLayout.CENTER);
    }

    private JPanel setupGrid(final State state) {
        final JPanel grid = new JPanel();
        if (state.placeholders.size() > 0) {
            grid.setLayout(new GridLayout(state.placeholders.size(),2));
            for (String key : state.placeholders) {
                grid.add(new JLabel(key));
                final String currentValue = state.placeholderValues.get(key);
                final JTextField textField = new JTextField(currentValue);
                textField.getDocument().addDocumentListener((ContentChangeListener) newContent -> {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    state.placeholderValues.put(key, newContent);
                });
                grid.add(textField);
            }
        } else {
            grid.setLayout(new BorderLayout());
            this.add(new JLabel("No placeholders to fill"), BorderLayout.CENTER);

        }
        return grid;
    }
}