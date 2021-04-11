package nf.fr.k49.sheepdoc.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocumentProcessor {

    private static Pattern KEY_PATTERN_EXTRACTOR = Pattern.compile("\\$\\{([a-zA-Z0-9 _-]+)\\}");

    public List<String> extractKeys(final String filePath) throws FileNotFoundException, IOException {
        final Set<String> keys = new HashSet<>();

        final XWPFDocument docx = new XWPFDocument(new FileInputStream(filePath));
        final XWPFWordExtractor we = new XWPFWordExtractor(docx);
        final String docText = we.getText();
        
        final Matcher matcher = KEY_PATTERN_EXTRACTOR.matcher(docText);
        while (matcher.find()) {
            keys.add(matcher.group(1));
        }
        
        return new ArrayList<>(keys);
    }

    public void replaceKeys(final String inputFilePath, final String outputFilePath, Map<String,String> keyValues) throws FileNotFoundException, IOException {
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(inputFilePath)))) {
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            // Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    if (docText != null) {
                        // replacement and setting position
                        for (Map.Entry<String,String> entry : keyValues.entrySet()) {
                            docText = docText.replace("${"+entry.getKey()+"}", entry.getValue());
                        }
                        xwpfRun.setText(docText, 0);
                    }
                }
            }

            // save the docs
            String validOutputFilePath = outputFilePath;
            final int lastDotPositionInInputPath = inputFilePath.lastIndexOf(".");
            if (lastDotPositionInInputPath > -1 && lastDotPositionInInputPath < inputFilePath.length()-1) {
                final String inputFileExtension = inputFilePath.substring(lastDotPositionInInputPath);
                if (!validOutputFilePath.endsWith(inputFileExtension)) {
                    validOutputFilePath += inputFileExtension;
                }
            }
            try (FileOutputStream out = new FileOutputStream(validOutputFilePath)) {
                doc.write(out);
            }

        }

    }
}