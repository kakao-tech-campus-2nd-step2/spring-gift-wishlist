package gift.service;

import org.springframework.stereotype.Service;

import gift.dao.UserDao;
import gift.domain.User;
import gift.dto.ProductDto;
import gift.dto.UserDto;
import gift.exception.InvalidUserException;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
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

    
}
