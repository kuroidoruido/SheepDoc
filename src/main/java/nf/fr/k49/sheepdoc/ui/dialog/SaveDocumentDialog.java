package nf.fr.k49.sheepdoc.ui.dialog;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;

public class SaveDocumentDialog {

    public String show() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }
}