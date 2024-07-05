package gift.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("newSiteUser", new SiteUser());
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> createUser(@RequestBody SiteUser newSiteUser){
        userService.createUser(newSiteUser.getUsername(), newSiteUser.getEmail(), newSiteUser.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(""));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO user){
        SiteUser siteUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        String jwt = jwtService.createJwt(siteUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(jwt));
    }



}
