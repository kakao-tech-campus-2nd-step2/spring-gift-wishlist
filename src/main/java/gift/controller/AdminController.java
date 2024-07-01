package gift.controller;


import gift.model.Gift;
import gift.service.GiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class AdminController {
    private GiftService giftService;

    AdminController(GiftService giftService){
        this.giftService = giftService;
    }

    @GetMapping
    public String index(){
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String adminHome(Model model){
        Collection<Gift> giftlist = giftService.getAllGifts();
        model.addAttribute("giftlist", giftlist);
        return "admin";
    }

    @GetMapping("/admin/gift/create")
    public String giftCreate(){
        return "create_form";
    }

    @PostMapping("/admin/gift/create")
    public String giftCreate(@ModelAttribute Gift gift) {
        giftService.addGift(gift);
        return "redirect:/admin";
    }


    @GetMapping("/admin/gift/detail/{id}")
    public String detail(Model model,@PathVariable("id") Long id){
        Gift gift = giftService.getGift(id);
        model.addAttribute("gift", gift);
        return "gift_detail";
    }


    @GetMapping("/admin/gift/modify/{id}")
    public String giftModify(Model model,@PathVariable("id") Long id){
        Gift gift = giftService.getGift(id);
        model.addAttribute("gift", gift);
        return "modify_form";

    }
    @PutMapping("/admin/gift/modify/{id}")
    public String giftModify(@PathVariable("id") Long id,
                             @ModelAttribute Gift gift){
        giftService.updateGift(gift, id);
        return "redirect:/admin";

    }
    @DeleteMapping("/admin/gift/delete/{id}")
    public String giftDelete(@PathVariable("id") Long id){
        giftService.deleteGift(id);
        return "redirect:/admin";
    }
}
