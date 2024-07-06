package gift.service;

import org.springframework.stereotype.Service;

import gift.dao.UserDao;
import gift.domain.User;
import gift.dto.UserDto;
import gift.exception.InvalidUserException;
import gift.util.JwtUtil;

@Service
public class UserService {

    private UserDao userDao;
    private JwtUtil jwtUtil;

    public UserService(UserDao userDao, JwtUtil jwtUtil){
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    public UserDto findByPassword(String password){
        User user = userDao.findByPassword(password)
            .orElseThrow(() -> new InvalidUserException("Product with password " + password + " not found"));
        return user.toDto(user);
    }

    public void addUser(UserDto userDto){
         if(userDao.findByPassword(userDto.getPassword()).isEmpty()){
            User user = userDto.toEntity(userDto);
            userDao.insertUser(user); 
        }else{
            throw new InvalidUserException("Product with id " + userDto.getId() + "exists");
        }
    }

    public String generateToken(String password){
        UserDto userDto = findByPassword(password);
        return jwtUtil.generateToken(userDto);
    }
    
}
