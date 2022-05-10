package entity;

import lombok.Data;

import java.util.*;

/**
 * @author zhihuan
 */

@Data
public class Results {
    int nums = 0;
    private Map<String, List<String>> Q_A = new HashMap<>();
    private UUID formId;
}
