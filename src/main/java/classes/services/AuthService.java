package classes.services;

import classes.entities.UsersEntity;
import classes.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    UsersRepository usersRepository;

    public UsersEntity loadUserByUserName(String username) {
        return usersRepository.findByUserName(username);
    }

    public boolean checkExistingUserName(String userName) {
        return usersRepository.existsByUserName(userName);
    }

    public boolean checkExistingEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public UsersEntity saveUser(UsersEntity user) {
        return usersRepository.save(user);
    }
}
