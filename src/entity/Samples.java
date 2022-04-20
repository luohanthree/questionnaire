package entity;

import lombok.Data;

/**
 * @author zhihuan
 */

@Data
public class Samples {
    private Integer sampleId;
    private Integer quesId;
    private String answer;
    private Integer formId;
}
