package individual.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@ToString
@Document
public class User {
    @Id
    private Integer userID;
    @Indexed(unique = true)
    private String userName;
    private String userPWD;
    private String userEmail;
    private String userPhone;
    private String userQQ;
    private int userSex;
    private String userLastLoginIP;
    private Date userLastLoginTime;
    private List<String> roles;
}
