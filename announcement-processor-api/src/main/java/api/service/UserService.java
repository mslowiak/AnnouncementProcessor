package api.service;

import api.entity.User;
import api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public boolean checkIfUsernameExist(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean checkIfEmailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
