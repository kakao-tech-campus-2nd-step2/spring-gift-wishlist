package gift.controller.th;

import gift.dto.ProductDTO;
import gift.dto.UserDto;
import gift.entity.User;
import gift.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserListController {

    UserListService userListService;

    public UserListController(UserListService userListService) {
        this.userListService = userListService;
    }

    @GetMapping("/user/list/th")
    public ModelAndView thUserList() {
        ModelAndView mav = new ModelAndView("user-list");
        mav.addObject("userModel", userListService.getAll());
        return mav;
    }

   // @GetMapping("/user/list")
   // public List<UserDto> UserList() {
    //    return userListService.getAll();
   // }
}
