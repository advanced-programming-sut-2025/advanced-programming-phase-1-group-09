package controllers.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SecurityQuestionManager {
    private static final List<String> questions;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = SecurityQuestionManager.class.getClassLoader()
                    .getResourceAsStream("data/security_questions.json");
            if (is == null) {
                throw new RuntimeException("File not found in resources!");
            }
            questions = mapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load security questions", e);
        }
    }

    public static List<String> getAllQuestions() {
        return questions;
    }

    public static String getQuestion(int index) {
        if (index < 0 || index >= questions.size()) return null;
        return questions.get(index);
    }
}
