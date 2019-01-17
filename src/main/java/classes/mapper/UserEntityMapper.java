package classes.mapper;

import classes.entities.UsersEntity;
import classes.model.UserModel;

public class UserEntityMapper {

    public static UserModel getUserModelFromEntity(UsersEntity usersEntity )
    {
        UserModel model = new UserModel();
        model.setName( usersEntity.getName() );
        model.setUsername( usersEntity.getUserName() );
        model.setRole( usersEntity.getRole() );
        return model;
    }
}
