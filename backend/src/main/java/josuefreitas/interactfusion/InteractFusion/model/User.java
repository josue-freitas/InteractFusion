package josuefreitas.interactfusion.InteractFusion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user")
@Data
public class User {
    @Id
    public String id;

    //unique username of the user
    @Indexed(unique = true)
    public String userName;

    //actual name of the user
    public String name;

    //biographic information
    public String bio;

    //total number of publications made by the user
    public int numberOfPosts;

    //list of users that follow the user
    public List<String> followersList;

    //list of the users followed by the user
    public List<String> followingList;

    //url of the profile image
    public String profileImageUrl;

    //email of the user
    public String email;

    //password of the user
    public String password;

    //cellphone of the user
    public String cellPhone;

    //date of birth of the user
    public Date dob;

    //list of posts liked by the user
    public List<String> postsLiked;

    //list of posts saved by the user
    public List<String> postsSaved;

    //list of users blocked
    public List<String> blockedUsers;

    //date of the creation account
    public String creationDateAccount;

    //status of account
    public String statusAccount;
}
