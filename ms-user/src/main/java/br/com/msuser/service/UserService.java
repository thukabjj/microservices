package br.com.msuser.service;

import br.com.msuser.domain.UserEntity;
import br.com.msuser.kafka.producer.Sender;
import br.com.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Value("${spring.kafka.topic.userCreated}")
    private String USER_CREATED_TOPIC;

    private Sender sender;

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity registerUser(UserEntity userEntity) {
        UserEntity createdUser = userRepository.saveAndFlush(userEntity);
        sender.send(USER_CREATED_TOPIC, createdUser);
        return createdUser;
    }
}
