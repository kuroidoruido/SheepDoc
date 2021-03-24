package nf.fr.k49.sheepdoc.helper;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@FunctionalInterface
public interface ContentChangeListener extends DocumentListener {
    void update(String e);
    
    default void extractContent(DocumentEvent e) {
        try {
            update(e.getDocument().getText(0, e.getDocument().getLength())); 
        } catch (Exception ex) {
            ex.printStackTrace();
            update(null);
        }
    }

    @Override
    default void insertUpdate(DocumentEvent e) {
        extractContent(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e) {
        extractContent(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e) {
        extractContent(e);
    }
}