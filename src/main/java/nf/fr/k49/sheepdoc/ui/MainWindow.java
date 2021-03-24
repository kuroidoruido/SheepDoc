package nf.fr.k49.sheepdoc.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.alee.laf.label.WebLabel;
import nf.fr.k49.sheepdoc.state.State;
import nf.fr.k49.sheepdoc.helper.DocumentProcessor;
import nf.fr.k49.sheepdoc.ui.AbstractMainWindowView;

public class MainWindow extends JFrame {
    private final State state;
    private final Breadcrumb breadcrumb;
    private final JPanel currentViewContainer;
    private AbstractMainWindowView currentView;
    private List<Function<State,AbstractMainWindowView>> views;
    private final DocumentProcessor docProcessor;

    public MainWindow(final State state) {
        this.state = state;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,700);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.svg")));
        this.setLayout(new BorderLayout());
        this.breadcrumb = new Breadcrumb();
        this.add(this.breadcrumb, BorderLayout.NORTH);
        this.currentViewContainer = new JPanel();
        this.add(this.currentViewContainer, BorderLayout.CENTER);
        this.initView();
        this.selectView(0, true);
        this.breadcrumb.onEntrySelection(this::selectView);
        this.docProcessor = new DocumentProcessor();
    }

    private void initView() {
        this.views = new ArrayList<>();
        this.views.add(state -> new SelectTemplateView(state).shouldGoNextView(() -> {
            if (this.state.templatePath.isPresent()) {
                try {
                    this.state.placeholders = this.docProcessor.extractKeys(this.state.templatePath.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(this.state.placeholders.size() > 0) {
                    this.state.placeholderValues = new HashMap<>();
                    this.selectView(1);
                }
            }
        }));
        this.views.add(state -> new FillTemplatePlaceholderView(state).shouldGoNextView(() -> this.selectView(2)));
        this.views.add(state -> new SaveDocumentView(state).shouldGoNextView(() -> this.saveDocument()));
    }

    public void selectView(final int viewIndex, final boolean initial) {
        if (viewIndex >= this.views.size()) {
            return;
        }
        if (!initial) {
            this.currentViewContainer.remove(this.currentView);
        }
        this.currentView = this.views.get(viewIndex).apply(this.state);
        this.currentViewContainer.setLayout(new BorderLayout());
        this.currentViewContainer.add(this.currentView, 0);
        if (!initial) {
            this.currentViewContainer.validate();
            this.currentViewContainer.repaint();
        }
    }

    public void selectView(final int viewIndex) {
        this.selectView(viewIndex, false);
    }
    
    private void saveDocument() {
        if (this.state.templatePath.isPresent() && this.state.outputDocumentPath.isPresent() && this.state.placeholderValues.size() > 0) {
            try {
                this.docProcessor.replaceKeys(this.state.templatePath.get(), this.state.outputDocumentPath.get(), this.state.placeholderValues);
                final JDialog dialog = new JDialog (this);
                dialog.add(new WebLabel("Document saved!", WebLabel.CENTER));
                dialog.setSize(200,100);
                dialog.setLocationRelativeTo(this);
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}