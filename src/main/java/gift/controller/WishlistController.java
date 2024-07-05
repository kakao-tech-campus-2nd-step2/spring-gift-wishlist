package gift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gift.exception.InvalidProductException;
import gift.model.User;
import gift.model.Wishlist;
import gift.service.AuthService;
import gift.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public ResponseEntity<List<Wishlist>> getWishlist(@RequestHeader("Authorization") String token){
		User user = authService.getUser(token);
		List<Wishlist> wishlist = wishlistService.getWishlist(user);
		if(wishlist.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(wishlist);
	}

	@PostMapping
	public ResponseEntity<String> addWishlist(@RequestHeader("Authorization") String token, @RequestBody Wishlist wishlist){
		User user = authService.getUser(token);
		int rowsAffected = wishlistService.addWishlist(user, wishlist.getProductName(), wishlist.getQuantity());
		if(rowsAffected == 0) {
			throw new InvalidProductException("Product could not be added to wishlist.");
		}
		return ResponseEntity.ok("Product added to wishlist successfully.");
	}
	
	@DeleteMapping
	public ResponseEntity<String> removeWishlist(@RequestHeader("Authorization") String token, @RequestBody Wishlist wishlist){
		User user = authService.getUser(token);
		int rowsAffected = wishlistService.removeWishlist(user, wishlist.getProductName());
		if(rowsAffected == 0) {
			throw new InvalidProductException("Product could not be removed from wishlist.");
		}
		return ResponseEntity.ok("Product removed from wishlist successfully.");
	}
	
	@PutMapping
	public ResponseEntity<String> updateWishlist(@RequestHeader("Authorization") String token, @RequestBody  Wishlist wishlist){
		User user = authService.getUser(token);
		int rowsAffected = wishlistService.updateWishlistQuantity(user, wishlist.getProductName(), wishlist.getQuantity());
		if(rowsAffected == 0) {
			throw new InvalidProductException("Product quantity could not be updated.");
		}
		return ResponseEntity.ok("Product quantity updated successfully.");
	}
}
