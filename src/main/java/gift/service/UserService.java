package gift.service;

import gift.controller.dto.UserDTO;
import gift.domain.UserInfo;
import gift.repository.UserInfoRepository;
import gift.utils.JwtTokenProvider;
import gift.utils.error.UserAlreadyExistsException;
import gift.utils.error.UserNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public UserService(UserInfoRepository userInfoRepository, JwtTokenProvider jwtTokenProvider) {
        this.userInfoRepository = userInfoRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserDTO RegisterUser(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo(userDTO.getEmail(), userDTO.getPassword());
        Boolean result = userInfoRepository.save(userInfo);
        if (!result) {
            throw new UserAlreadyExistsException("User Already Exist");
        }
        return userDTO;
    }

    public String Login(UserDTO userDTO){
        UserInfo userInfo = new UserInfo(userDTO.getEmail(),userDTO.getPassword());
        Optional<UserInfo> byEmail = userInfoRepository.findByEmail(userDTO.getPassword());
        if (byEmail.isEmpty()){
            throw new UserNotFoundException("User NOT FOUND");
        }
        return jwtTokenProvider.createToken(userDTO.getEmail());
    }


}
