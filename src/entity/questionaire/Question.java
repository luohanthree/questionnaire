package entity.questionaire;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhihuan
 */
@Data
public class Question {
    private List<String> options = new ArrayList<>();
    private String question_name;
    private String type;

    public void addOption(Option option) {
        options.add(option.getOption());
    }
}
