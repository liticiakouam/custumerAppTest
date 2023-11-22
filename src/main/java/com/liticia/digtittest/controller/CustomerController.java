package com.liticia.digtittest.controller;

import com.liticia.digtittest.entity.Customer;
import com.liticia.digtittest.exception.UserAlreadyExistException;
import com.liticia.digtittest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping("/save")
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 RedirectAttributes redirectAttributes,
                                 Model model) throws UserAlreadyExistException {
        try {
            customerService.save(customer);
        } catch (UserAlreadyExistException ex) {
            redirectAttributes.addFlashAttribute("error", "Désolé, L'utilisateur existe déjà");
            model.addAttribute("customers", customerService.getAll());
        }
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int id) {
        customerService.delete(id);
        return "redirect:/customers";
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "update";
    }

    @PostMapping("/updateForm/{id}")
    public String showUpdateFor(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.update(customer);
        return "redirect:/customers";
    }
}
