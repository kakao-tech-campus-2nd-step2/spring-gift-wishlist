package gift.controller;

import jakarta.validation.constraints.NotNull;

public class WishlistNameRequest {

    @NotNull(message = "Member ID를 입력하세요")
    private Long memberId;
    @NotNull(message = "Item Name을 입력하세요")
    private String itemName;

    public WishlistNameRequest(Long memberId, String itemName) {
        this.memberId = memberId;
        this.itemName = itemName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
