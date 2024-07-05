package gift.domain.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * 토큰에서 추출한 사용자 정보를 담음
 */
public class UserInfo {

    private String email;
    private Long id;
}
