package gift.api.member;

import jakarta.validation.Valid;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberDao memberDao;

    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        if (memberDao.hasMemberByEmail(memberRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        memberDao.insert(memberRequestDto);
        var credentials = memberRequestDto.getEmail() + ":" + memberRequestDto.getPassword();
        return ResponseEntity.ok().body(new MemberResponseDto(Base64.getEncoder()
                                                                    .encodeToString(credentials.getBytes())));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberRequestDto memberRequestDto, @RequestHeader("Authorization") String token) {
        if (memberDao.hasMemberByEmailAndPassword(memberRequestDto)) {
            var credentials = memberRequestDto.getEmail() + ":" + memberRequestDto.getPassword();
            if (token.split(" ")[1].equals(Base64.getEncoder().encodeToString(credentials.getBytes()))) {
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
