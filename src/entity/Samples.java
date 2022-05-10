package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhihuan
 */

@Data
public class Samples {
    private UUID sampleId;
    private String question_name;
    private List<String> answer = new ArrayList<>();
    private UUID formId;
    private String ip;
    private UUID uuid;
}
