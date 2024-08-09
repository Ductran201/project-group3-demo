package ra.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.User;
import ra.service.impl.FilmServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/film")
public class UFilmController {
    @Autowired
    private FilmServiceImpl filmService;
    @GetMapping("/detail/{id}")
    public String detailFilm(HttpSession httpSession, @PathVariable Integer id, Model model){
        User userCurrent = (User) httpSession.getAttribute("userCurrent");
        model.addAttribute("userCurrent",userCurrent);
        model.addAttribute("filmDetail", filmService.findById(id));
        return "home/details";
    }
}
