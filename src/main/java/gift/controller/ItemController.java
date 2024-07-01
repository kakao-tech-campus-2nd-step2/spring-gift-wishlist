package gift.controller;

import gift.Model.Item;
import gift.Model.ItemDTO;
import gift.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public String getItemList(Model model){
        model.addAttribute("list",itemService.getList());
        return "list";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model,  ItemDTO itemDTO){
        model.addAttribute("item",itemDTO);
        return "create";
    }

    @PostMapping("/create")
    public String createItem(Model model,@ModelAttribute("item") ItemDTO itemDTO){
        if(itemDTO.imgUrl().length() > 255){
            model.addAttribute("item",itemDTO);
            model.addAttribute("e","Url은 255를 넘을수 없습니다");
            return "create";
        }
        itemService.insertItem(itemDTO);
        return "redirect:/product/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Long id,Model model){
        Item item = itemService.findItem(id);
        ItemDTO itemDTO = new ItemDTO(item.getName(),item.getPrice(),item.getImgUrl());
        model.addAttribute("item",itemDTO);
        model.addAttribute("id",id);
        return "update";
    }

    @PutMapping("/update/{id}")
    public String updateItem(Model model,@PathVariable Long id,@ModelAttribute ItemDTO itemDTO){
        if(itemDTO.imgUrl().length() > 255){
            model.addAttribute("item",itemDTO);
            model.addAttribute("e","Url은 255를 넘을수 없습니다");
            return "update";
        }
        itemService.updateItem(itemDTO,id);
        return "redirect:/product/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/product/list";
    }
}
