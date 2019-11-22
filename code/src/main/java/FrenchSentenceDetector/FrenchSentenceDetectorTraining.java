package FrenchSentenceDetector;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Etienne PASCAL
 */
public class FrenchSentenceDetectorTraining {

	public static void main(String[] args) {
		try {
			new FrenchSentenceDetectorTraining().trainSentDectectModel("fr-sent-custom.bin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method generates s custom model file for sentence detection, in directory "custom_models".
	 * The training data used is "french_sentences/french_sentences.txt". Training data contains a sentence per line in the text file.
	 * @throws IOException
	 */
    private void trainSentDectectModel(String trained_model_name) throws IOException {
		// directory to save the model file that is to be generated, create this directory in prior
		File destDir = new File("src/main/resources/custom_models");

		// training data
		InputStreamFactory in = new MarkableFileInputStreamFactory(new File("src/main/resources/french_sentences/french_sentences.txt"));

		// parameters used by machine learning algorithm, Maxent, to train its weights
		TrainingParameters mlParams = new TrainingParameters();
		mlParams.put(TrainingParameters.ITERATIONS_PARAM, Integer.toString(15));
		mlParams.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(1));

		// train the model
		SentenceModel sentdetectModel = SentenceDetectorME.train(
				"fr",
				new SentenceSampleStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8)),
				true,
				null,
				mlParams);

		// save the model, to a file, "fr-sent-custom.bin", in the destDir : "custom_models"
		File outFile = new File(destDir, trained_model_name);
		FileOutputStream outFileStream = new FileOutputStream(outFile);
		sentdetectModel.serialize(outFileStream);
	} 
}

///home/lascapzetu/.IntelliJIdea2019.2/config/idea.properties