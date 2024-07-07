package gift.service;

import gift.controller.MemberController;
import gift.domain.Member;
import gift.domain.MemberRequest;
import gift.repository.MemberRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private JwtService jwtService;

    public MemberService(MemberRepository memberRepository,JwtService jwtService){
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    public void join(MemberRequest memberRequest) {
        memberRepository.save(memberRequest);
    }

    public ResponseEntity<String> login(MemberRequest memberRequest) {
        MemberRequest dbMember = memberRepository.findById(memberRequest.id());
        if(dbMember == null || !memberRequest.password().equals(dbMember.password())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("incorrect password or id");
        }
        else{
            String jwt = jwtService.createJWT(dbMember.id());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","basic " + jwt);
            return ResponseEntity.ok().headers(headers).body("success");
        }
    }
}