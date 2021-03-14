package nf.fr.k49.sheepdoc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FillKeysWindow extends JFrame {
    private final List<String> keys;
    private final List<JTextField> valuesTextFields;
    private Function<Map<String,String>, Void> onGeneratedCallback;

    public FillKeysWindow(final List<String> keys) {
        super("SheepDoc");
        this.keys = keys;
        this.valuesTextFields = new ArrayList<>();
        this.setup();
    }
    
    private void setup() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,700);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.svg")));
        final BorderLayout layout = new BorderLayout();
        final JPanel keysPanel = this.createKeysPanel();
        final JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(this::onGenerateButtonClick);
        this.setLayout(layout);
        this.add(keysPanel, BorderLayout.CENTER);
        this.add(generateButton, BorderLayout.SOUTH);
    }

    private JPanel createKeysPanel() {
        final JPanel panel = new JPanel();
        final int rowCount = this.keys.size() > 1 ? this.keys.size()+1 : 1;
        final GridLayout layout = new GridLayout(rowCount, 2);
        for (String key : this.keys) {
            panel.add(new JLabel(key));
            final JTextField field = new JTextField();
            this.valuesTextFields.add(field);
            panel.add(field);
        }
        panel.setLayout(layout);
        this.add(panel);
        return panel;
    }

    public void onGenerate(Function<Map<String,String>, Void> callback) {
        this.onGeneratedCallback = callback;
    }

    private void onGenerateButtonClick(ActionEvent action) {
        final Map<String,String> state = new HashMap<>();
        for (int i=0 ; i<this.keys.size() ; i++) {
            final String key = this.keys.get(i);
            final JTextField textField = this.valuesTextFields.get(i);
            state.put(key,textField.getText());
        }
        this.onGeneratedCallback.apply(state);
    }
}