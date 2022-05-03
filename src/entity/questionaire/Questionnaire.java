package entity.questionaire;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhihuan
 */


@Data
public class Questionnaire {

    public Questionnaire() {
        formId = UUID.randomUUID();
    }
    private UUID formId;
    private int createUserId;
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

}
