package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Category;
import ra.service.impl.CategoryServiceImpl;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping({"/category", "category.html"})
    public String category(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "admin/category";
    }

    @GetMapping({"/addCategory", "add-category.html"})
    public String openAddCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/add-category";
    }

    @PostMapping({"/addCategory"})
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping({"/editCategory/{id}"})
    public String openEditForm(@PathVariable Integer id, Model model){
        model.addAttribute("category",categoryService.findById(id));
        return "redirect:/admin/category";
    }

    @PostMapping({"/editCategory"})
    public String edit(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping({"/deleteCategory/{id}"})
    public String delete(@PathVariable Integer id){
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

}
