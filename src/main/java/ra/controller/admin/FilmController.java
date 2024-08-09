package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.FilmRequest;
import ra.service.impl.ActorServiceImpl;
import ra.service.impl.CategoryServiceImpl;
import ra.service.impl.FilmServiceImpl;

@Controller
@RequestMapping("/admin")
public class FilmController {
    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ActorServiceImpl actorService;

    @GetMapping({"film", "film.html"})
    public String film(Model model) {
        model.addAttribute("filmList", filmService.findAll());
        return "admin/film";
    }

    @GetMapping({"/addFilm", "add-film.html"})
    public String openAddFilm(Model model) {
        model.addAttribute("filmRequest", new FilmRequest());
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("actorList",actorService.findAll());
        return "admin/add-film";
    }

    @PostMapping({"/addFilm"})
    public String addFilm(@ModelAttribute FilmRequest filmRequest) {
        filmService.save(filmRequest);
        return "redirect:/admin/film";
    }

    @GetMapping({"/editFilm/{id}"})
    public String openEditForm(@PathVariable Integer id, Model model){
        model.addAttribute("filmRequest",filmService.findById(id));
        return "redirect:/admin/film";
    }

    @PostMapping({"/editFilm"})
    public String edit(@ModelAttribute FilmRequest filmRequest){
        filmService.save(filmRequest);
        return "redirect:/admin/film";
    }

    @GetMapping({"/deleteFilm/{id}"})
    public String delete(@PathVariable Integer id){
        filmService.delete(id);
        return "redirect:/";
    }

}
