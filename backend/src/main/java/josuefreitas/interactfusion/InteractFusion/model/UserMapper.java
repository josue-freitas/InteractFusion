package josuefreitas.interactfusion.InteractFusion.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "numberOfPosts", ignore = true)
    @Mapping(target = "followersList", ignore = true)
    @Mapping(target = "followingList", ignore = true)
    @Mapping(target = "postsLiked", ignore = true)
    @Mapping(target = "postsSaved", ignore = true)
    @Mapping(target = "blockedUsers", ignore = true)
    @Mapping(target = "creationDateAccount", ignore = true)
    @Mapping(target = "statusAccount", ignore = true)
    @Mapping(target = "profileImageUrl", ignore = true)
    //UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);
}