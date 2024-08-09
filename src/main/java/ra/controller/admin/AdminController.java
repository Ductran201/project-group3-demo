package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.service.impl.CategoryServiceImpl;
import ra.service.impl.FilmServiceImpl;
import ra.service.impl.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private FilmServiceImpl filmService;

    @GetMapping({"/dashboard", "dashboard.html"})
    public String dashboard() {
        return "admin/dashboard";
    }


    @GetMapping({"/users", "users.html"})
    public String users(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin/users";
    }

    @GetMapping({"/comments", "comments.html"})
    public String comments() {
        return "admin/comments";
    }

    @GetMapping({"/reviews", "reviews.html"})
    public String reviews() {
        return "admin/reviews";
    }

    @GetMapping({"/settings", "settings.html"})
    public String settings() {
        return "admin/settings";
    }

    @GetMapping({"/home"})
    public String pages() {
        return "redirect:/main";
    }


}
