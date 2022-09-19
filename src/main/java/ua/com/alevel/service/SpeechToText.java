package ua.com.alevel.service;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SpeechToText {

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String path = generatePathToPackageForSavedImage(multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(path));
        return path;
    }

    private static String generatePathToPackageForSavedImage(String originalFileName) {
        return "C:/D/MyProject/src/main/resources/static/files" +  originalFileName  + ".wav";
    }

    public String speechToText(String pathToAudio) throws Exception{
        // Instantiates a client
        try (SpeechClient speechClient = SpeechClient.create()) {

            // Builds the sync recognize request
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("en-US")
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(pathToAudio).build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        }
        return "";
    }
}
