package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.service.SpeechToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @PostMapping("/getTextWithAudio")
    public void redirectToNewAccountPage(Model model, WebRequest request, @RequestParam("voice") MultipartFile file) throws Exception {
        SpeechToText speechToText = new SpeechToText();
        String path = speechToText.saveFile(file);
        System.out.println("path = " + path);
        String textWithAudio = speechToText.speechToText(path);
        System.out.println("path = " + textWithAudio);
    }
}
