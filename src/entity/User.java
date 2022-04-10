package entity;

import lombok.Data;

import java.util.UUID;

/**
 * @author zhihuan
 */

@Data
public class User {
    private String userName;
    private Integer userId;
    private String pwd;
}
