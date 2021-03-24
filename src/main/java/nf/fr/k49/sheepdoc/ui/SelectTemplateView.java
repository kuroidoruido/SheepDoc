package nf.fr.k49.sheepdoc.ui;

import java.awt.BorderLayout;
import java.util.Optional;
import javax.swing.JButton;
import nf.fr.k49.sheepdoc.state.State;
import nf.fr.k49.sheepdoc.ui.dialog.ChooseTemplateDialog;

public class SelectTemplateView extends AbstractMainWindowView {

    public SelectTemplateView(final State state) {
        super(state);
        this.setLayout(new BorderLayout());
        final JButton btn = new JButton("Select a document template");
        btn.addActionListener(action -> this.onSelectTemplateClick());
        this.add(btn, BorderLayout.CENTER);
    }

    private void onSelectTemplateClick() {
        try {
            final ChooseTemplateDialog selectInputFileDialog = new ChooseTemplateDialog();
            final String filePath = selectInputFileDialog.show();
            this.getState().templatePath = Optional.ofNullable(filePath);
            this.goNextView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}