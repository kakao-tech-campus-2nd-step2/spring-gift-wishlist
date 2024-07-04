package gift.dto;

public record MemberResponse(String token) {
    public static MemberResponse from(String token) {
        return new MemberResponse(token);
    }
}
