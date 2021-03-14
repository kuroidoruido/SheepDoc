package nf.fr.k49.sheepdoc;

import java.util.List;
import javax.swing.JFrame;

public class App  {
    public static void main( String[] args ) throws Exception {
        System.out.println("Starting SheepDoc...");
        
        final ChooseTemplateDialog selectInputFileDialog = new ChooseTemplateDialog();
        final String filePath = selectInputFileDialog.show();
        System.out.println("Selected: "+filePath);

        final DocumentProcessor docProcessor = new DocumentProcessor();
        final List<String> extractedKeys = docProcessor.extractKeys(filePath);

        final FillKeysWindow fillKeysWindow = new FillKeysWindow(extractedKeys);
        fillKeysWindow.onGenerate(state -> {
            final SaveDocumentDialog selectOutputFileDialog = new SaveDocumentDialog();
            final String outputPath = selectOutputFileDialog.show();
            try {
                docProcessor.replaceKeys(filePath, outputPath, state);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        fillKeysWindow.setVisible(true);
    }
}
