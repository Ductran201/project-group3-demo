package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.constrain.RoleName;
import ra.model.dto.request.FormSignIn;
import ra.model.dto.request.FormSignUp;
import ra.model.entity.User;
import ra.service.AuthenticService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("")
public class AuthenticController {
    @Autowired
    private AuthenticService authenticService;

    // ====================SIGN IN ========================
    @GetMapping({"", "/signIn"})
    public String formSignIn(Model model) {
        model.addAttribute("formSignIn", new FormSignIn());
        return "signin";
    }

    @PostMapping("/signIn")
    public String signIn(@Valid @ModelAttribute FormSignIn formSignIn,
                         BindingResult result, Model model, HttpSession httpSession) {
        // service . signin
        // if has user -> home page + create session account in browser // -> reset sign in page
        // Validate input
        if (result.hasErrors()) {
            // Send again model and reset sign in page
            model.addAttribute("formSignIn", formSignIn);
            return "signin";
        }
        User user = authenticService.signIn(formSignIn);

        if (user != null) {
            // Save the current user
            httpSession.setAttribute("userCurrent", user);
            // check role admin
            if (user.getRoles().stream().anyMatch(r->r.getName().equals(RoleName.ROLE_ADMIN))){
                return "redirect:/admin/dashboard";
            }

            return "redirect:/main";
        } else {
            return "404";
        }
    }

// ====================SIGN UP ========================


    @GetMapping("/signUp")
    public String formSignUp(Model model) {
        model.addAttribute("formSignUp", new FormSignUp());
        return "signup";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute FormSignUp formSignUp, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formSignUp", formSignUp);
            return "signup";
        }

        authenticService.signUp(formSignUp);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/signIn";
    }   
}
