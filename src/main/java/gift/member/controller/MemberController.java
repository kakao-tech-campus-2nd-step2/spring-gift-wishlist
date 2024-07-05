package gift.member.controller;

import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import gift.global.utils.ResponseHelper;
import gift.member.domain.Member;
import gift.member.dto.MemberRequestDTO;
import gift.member.service.MemberService;
import gift.product.domain.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public ResponseEntity<ResultResponseDto<List<Member>>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseHelper.createResponse(ResultCode.GET_ALL_MEMBERS_SUCCESS, members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponseDto<Member>> getMemberById(@PathVariable(name = "id") Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseHelper.createResponse(ResultCode.GET_MEMBER_BY_ID_SUCCESS, member);
    }

    @PostMapping("")
    public ResponseEntity<SimpleResultResponseDto> createMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        memberService.createMember(memberRequestDTO.toMemberServiceDto());
        return ResponseHelper.createSimpleResponse(ResultCode.CREATE_MEMBER_SUCCESS);
    }
}
