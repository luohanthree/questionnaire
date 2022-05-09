package entity.questionaire;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.net.URL;
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
    @JSONField(name = "formName")
    private String formName;
    @JSONField(name = "question")
    private List<Question> questions = new ArrayList<>();
    @JSONField(name = "url")
    private URL url;
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String toJSONString() {
        return "{formId:\" " +  formId.toString() +"\"," + "createUserId:\"" + this.createUserId + ",\""
                + "questions:\"" + questions.toString() +"\"}";
    }
    public String toString() {
        return "{formId:" + this.getFormId().toString() + ",formName:" + this.getFormName() + ",createUserId:" + this.createUserId + ", questions:"
                + getQuestions().toString() + "}";
    }
}
