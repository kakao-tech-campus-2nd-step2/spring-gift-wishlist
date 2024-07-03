package wishlist.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import wishlist.model.ItemDTO;
import wishlist.model.ItemForm;
import wishlist.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list")
    public String getItemList(Model model){
        model.addAttribute("list",itemService.getList());
        return "list";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model, ItemForm form){
        model.addAttribute("item",form);
        return "create";
    }

    @PostMapping("/create")
    public String createItem(@Valid @ModelAttribute("item") ItemForm form, BindingResult result){
        if(result.hasErrors()){
            return "create";
        }
        itemService.insertItem(form);
        return "redirect:/product/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Long id,Model model){
        ItemDTO itemDTO = itemService.findItem(id);
        ItemForm form = new ItemForm(itemDTO.name(),itemDTO.price(),itemDTO.imgUrl());
        model.addAttribute("item",form);
        model.addAttribute("id",id);
        return "update";
    }

    @PutMapping("/update/{id}")
    public String updateItem(@PathVariable Long id,@Valid @ModelAttribute("item") ItemForm form,BindingResult result){
        if(result.hasErrors()){
            return "update";
        }
        ItemDTO itemDTO = new ItemDTO(id,form.getName(),form.getPrice(),form.getImgUrl());
        itemService.updateItem(itemDTO);
        return "redirect:/product/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/product/list";
    }
}