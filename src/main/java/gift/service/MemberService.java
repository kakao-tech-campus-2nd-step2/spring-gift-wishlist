package gift.service;

import gift.entity.Member;
import gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  private MemberRepository memberRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public Member register(String email, String password) {
    Member member = new Member();
    member.setEmail(email);
    member.setPassword(passwordEncoder.encode(password));
    return memberRepository.save(member);
  }

  public Member authenticate(String email, String password) {
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("유효하지 않은 에메일 또는 패스워드"));
    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new RuntimeException("유효하지 않은 에메일 또는 패스워드");
    }
    return member;
  }
}
