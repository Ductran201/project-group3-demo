package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.ActorRequest;
import ra.service.impl.ActorServiceImpl;

@Controller
@RequestMapping("/admin")
public class ActorController {
    @Autowired
    private ActorServiceImpl actorService;

    @GetMapping("/actor")
    public String actor(Model model) {
        model.addAttribute("actorList", actorService.findAll());
        return "admin/actor";
    }

    @GetMapping({"/addActor", "add-actor.html"})
    public String openAddActor(Model model) {
        model.addAttribute("actorRequest", new ActorRequest());
        return "admin/add-actor";
    }

    @PostMapping({"/addActor"})
    public String addActor(@ModelAttribute ActorRequest actorRequest) {
        actorService.save(actorRequest);
        return "redirect:/admin/actor";
    }

    @GetMapping({"/editActor/{id}"})
    public String openEditForm(@PathVariable Long id, Model model){
        model.addAttribute("actorRequest",actorService.findById(id));
        return "redirect:/admin/actor";
    }

    @PostMapping({"/editActor"})
    public String edit(@ModelAttribute ActorRequest actorRequest){
        actorService.save(actorRequest);
        return "redirect:/admin/actor";
    }

    @GetMapping({"/deleteActor/{id}"})
    public String delete(@PathVariable Long id){
        actorService.delete(id);
        return "redirect:/admin/actor";
    }

}
