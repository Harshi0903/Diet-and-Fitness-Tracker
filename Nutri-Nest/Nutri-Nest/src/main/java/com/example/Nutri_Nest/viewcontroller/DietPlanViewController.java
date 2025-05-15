//package com.example.Nutri_Nest.viewcontroller;
//
//import com.example.Nutri_Nest.dto.DietPlanDTO;
//import com.example.Nutri_Nest.service.DietPlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/view/dietplans")
//public class DietPlanViewController {
//
//    @Autowired
//    private DietPlanService dietPlanService;
//
//    @GetMapping
//    public String showDietPlans(Model model) {
//        List<DietPlanDTO> plans = dietPlanService.getAllDietPlans().getBody();
//        model.addAttribute("dietPlans", plans);
//        model.addAttribute("newDietPlan", new DietPlanDTO());
//        return "dietplans";
//    }
//
//    @PostMapping("/add")
//    public String addDietPlan(@ModelAttribute DietPlanDTO dto) {
//        dietPlanService.createDietPlan(dto);
//        return "redirect:/view/dietplans";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateDietPlan(@PathVariable Long id, @ModelAttribute DietPlanDTO dto) {
//        dietPlanService.fullUpdateDietPlan(id, dto);
//        return "redirect:/view/dietplans";
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String deleteDietPlan(@PathVariable Long id) {
//        dietPlanService.deleteDietPlanById(id);
//        return "redirect:/view/dietplans";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editDietPlan(@PathVariable Long id, Model model) {
//        DietPlanDTO plan = dietPlanService.getDietPlanById(id).getBody();
//        List<DietPlanDTO> plans = dietPlanService.getAllDietPlans().getBody();
//        model.addAttribute("dietPlans", plans);
//        model.addAttribute("newDietPlan", plan);
//        model.addAttribute("editing", true);
//        return "dietplans";
//    }
//}
package com.example.Nutri_Nest.viewcontroller;

import com.example.Nutri_Nest.dto.DietPlanDTO;
import com.example.Nutri_Nest.service.DietPlanService;
import com.example.Nutri_Nest.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/diet-plans") // Root mapping for all diet plan-related views
public class DietPlanViewController {

    @Autowired
    private DietPlanService dietPlanService;

    @Autowired
    private FoodItemService foodItemService;

    // This method renders the view for listing all diet plans
    @GetMapping
    public String getAllDietPlans(Model model) {
        List<DietPlanDTO> dietPlans = dietPlanService.getAllDietPlans().getBody();
        model.addAttribute("dietPlans", dietPlans);  // Make sure the dietPlans list includes userId
        return "diet-plans";  // Return the view name for listing diet plans
    }

    // Create Diet Plan
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("dietPlanDTO", new DietPlanDTO());
        model.addAttribute("foodItems", dietPlanService.getAllFoodItems());
        model.addAttribute("users", dietPlanService.getAllUsers());
        return "create-diet-plan"; // This is your create form page
    }

    @PostMapping("/create")
    public String createDietPlan(@ModelAttribute DietPlanDTO dietPlanDTO, RedirectAttributes redirectAttributes) {
        try {
            dietPlanService.createDietPlan(dietPlanDTO);
            redirectAttributes.addFlashAttribute("message", "Diet plan created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error creating diet plan.");
        }
        return "redirect:/diet-plans";
    }


    // Edit Diet Plan
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            DietPlanDTO dietPlanDTO = dietPlanService.getDietPlanById(id).getBody();
            model.addAttribute("dietPlanDTO", dietPlanDTO);
            model.addAttribute("foodItems", dietPlanService.getAllFoodItems());
            model.addAttribute("users", dietPlanService.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error loading diet plan.");
            return "error-page"; // Handle this error gracefully
        }
        return "edit-diet-plan"; // This is your edit form page
    }

    @PostMapping("/edit/{id}")
    public String updateDietPlan(@PathVariable Long id, @ModelAttribute DietPlanDTO dietPlanDTO, RedirectAttributes redirectAttributes) {
        try {
            dietPlanService.fullUpdateDietPlan(id, dietPlanDTO);
            redirectAttributes.addFlashAttribute("message", "Diet plan updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error updating diet plan.");
        }
        return "redirect:/diet-plans";
    }

    @PostMapping("/delete/{id}")
    public String deleteDietPlan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        dietPlanService.deleteDietPlanById(id);
        redirectAttributes.addFlashAttribute("message", "Diet plan deleted successfully!");
        return "redirect:/diet-plans";
    }
}



