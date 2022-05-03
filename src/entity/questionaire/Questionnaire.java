package entity.questionaire;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "formId")
    private UUID formId;
    @JSONField(name = "userId")
    private int createUserId;
    @JSONField(name = "options")
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

}
