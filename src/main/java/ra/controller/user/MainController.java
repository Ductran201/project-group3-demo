package ra.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.User;
import ra.service.impl.FilmServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private FilmServiceImpl filmService;
    @GetMapping("")
    public String index(HttpSession httpSession, Model model){
        User userCurrent = (User) httpSession.getAttribute("userCurrent");
        model.addAttribute("userCurrent", userCurrent);
        model.addAttribute("filmList",filmService.findAll());
        return "home/home";
    }

    @GetMapping("/pricing")
    public String pricing(){
        return "home/pricing";
    }

//     ====================== USER =============================
    @GetMapping({"/profile"})
    public String profile(HttpSession httpSession,Model model){
        User userCurrent = (User) httpSession.getAttribute("userCurrent");
        model.addAttribute("userCurrent",userCurrent);
        return "home/profile";
    }


}
