package gift.jwt;

import gift.model.member.Member;
import gift.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, MemberService memberService) {
        this.jwtUtil = jwtUtil;
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            sendUnauthorizedResponse(response, "Authorization header missing or invalid");
            return;
        }

        String token = header.substring(7);
        if (!jwtUtil.validateToken(token)) {
            sendUnauthorizedResponse(response, "Invalid token");
            return;
        }

        String email = jwtUtil.getEmailFromToken(token);
        Member member = memberService.findByEmail(email);
        if (member == null) {
            sendUnauthorizedResponse(response, "Member not found");
            return;
        }

        request.setAttribute("member", member);
        filterChain.doFilter(request, response);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }
}
