package web.controller;

import app.model.User;
import app.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private EntityService<User> userService;

    @GetMapping(value = "/")
    public String getIndex(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        model.addAttribute("user", new User());
        return "/add";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute("user") User user, Model model) {
        userService.add(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String postEdit(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
