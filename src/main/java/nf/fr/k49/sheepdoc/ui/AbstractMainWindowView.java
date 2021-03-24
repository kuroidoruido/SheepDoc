package nf.fr.k49.sheepdoc.ui;

import javax.swing.JPanel;
import nf.fr.k49.sheepdoc.state.State;

public abstract class AbstractMainWindowView extends JPanel {
    private final State state;
    private Runnable goNextViewCallback;

    public AbstractMainWindowView(final State state) {
        this.state = state;
    }

    protected State getState() {
        return this.state;
    }

    protected void goNextView() {
        this.goNextViewCallback.run();
    }

    public AbstractMainWindowView shouldGoNextView(final Runnable callback) {
        this.goNextViewCallback = callback;
        return this;
    }
}