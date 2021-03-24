package nf.fr.k49.sheepdoc.ui;

import java.awt.BorderLayout;
import java.util.Optional;
import javax.swing.JButton;
import nf.fr.k49.sheepdoc.state.State;
import nf.fr.k49.sheepdoc.ui.dialog.SaveDocumentDialog;

public class SaveDocumentView extends AbstractMainWindowView {

    public SaveDocumentView(final State state) {
        super(state);
        this.setLayout(new BorderLayout());
        final JButton btn = new JButton("Select directory and filename for your document");
        btn.addActionListener(action -> this.onSelectOutputDocumentClick());
        this.add(btn, BorderLayout.CENTER);
    }

    private void onSelectOutputDocumentClick() {
        try {
            final SaveDocumentDialog selectInputFileDialog = new SaveDocumentDialog();
            final String filePath = selectInputFileDialog.show();
            this.getState().outputDocumentPath = Optional.ofNullable(filePath);
            this.goNextView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}