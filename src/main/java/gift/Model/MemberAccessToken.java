package gift.Model;

public class MemberAccessToken {
    private final boolean accessToken;
    public MemberAccessToken(boolean accessToken){
        this.accessToken = accessToken;
    }

    public boolean getAccessToken() {
        return accessToken;
    }
}
