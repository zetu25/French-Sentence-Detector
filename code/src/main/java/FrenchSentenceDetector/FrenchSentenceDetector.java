package FrenchSentenceDetector;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FrenchSentenceDetector {

    public static FrenchSentenceDetector sentenceDetector;

    public SentenceDetectorME sdetector;

    private FrenchSentenceDetector() throws IOException {
        InputStream is = new FileInputStream("src/main/resources/custom_models/fr-sent-custom.bin");
        SentenceModel model = new SentenceModel(is);
        sdetector = new SentenceDetectorME(model);
    }

    public static FrenchSentenceDetector getInstance() throws IOException {
        if (sentenceDetector == null)
            sentenceDetector = new FrenchSentenceDetector();
        return sentenceDetector;
    }


}
