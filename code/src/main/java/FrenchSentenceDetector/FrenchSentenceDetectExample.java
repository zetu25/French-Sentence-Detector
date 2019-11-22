package FrenchSentenceDetector;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Etienne PASCAL
 */
public class FrenchSentenceDetectExample {

    public static void main(String[] args) {
        try {
            new FrenchSentenceDetectExample().sentenceDetect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sentenceDetect() throws  IOException {
        String paragraph = "Le Gouvernement est attaché à la transparence du Grand Débat et cette exigence de transparence est également portée par le collège des garants.\n" +
                "Ainsi, l’ensemble des contributions au débat, qu'elles aient été envoyées par voie postale, par courriel ou via les formulaires en ligne, seront à terme accessibles à tous.\n" +
                "Les restitutions de réunions d'initiative locales, les réponses aux questionnaires, les cahiers citoyens ouverts dans les mairies, les contributions libres, seront progressivement et régulièrement mis en ligne sous licence libre.";

        // refer to model file "<language>-sent.bin", available at link http://opennlp.sourceforge.net/models-1.5/
        InputStream is = new FileInputStream("src/main/resources/custom_models/fr-sent-custom.bin");
        SentenceModel model = new SentenceModel(is);

        // load the model
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        // detect sentences in the paragraph
        String[] sentences = sdetector.sentDetect(paragraph);

        // print the sentences detected, to console
        for(int i=0;i<sentences.length;i++){
            System.out.println("Sentence "+(i+1)+" : "+sentences[i]);
        }
        is.close();
    }
}