package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author zhihuan
 */

@Data
@RequiredArgsConstructor
public class User {
    private final String userName;
    private final Integer userId;
    private final String pwd;
}
