package entity;

import lombok.Data;

/**
 * @author zhihuan
 */


@Data
public class Questionnaire {

    private int formId;
    private int quesId;
    private int createId;
    private String quesName;
    private String quesType;

}
