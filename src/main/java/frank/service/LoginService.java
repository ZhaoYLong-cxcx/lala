package frank.service;

import frank.mapper.UserMapper;
import frank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;


    public User queryByLogin(String username, String password) {
        return userMapper.queryByLogin(username, password);
    }
}
