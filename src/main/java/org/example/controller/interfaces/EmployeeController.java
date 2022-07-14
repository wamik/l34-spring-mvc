package org.example.controller.interfaces;


import org.example.model.Employee;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/employee")
public interface EmployeeController {

    @GetMapping
    String listAll( Model model);

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model);

    @GetMapping("/new")
    String newEmployeeForm(Model model);

    @PostMapping
    String save(@RequestParam("image") MultipartFile image, Employee employee, Model model);

    @GetMapping("/delete/{id}")
    String delete(@PathVariable Long id, Model model);

    @GetMapping(value = "/image/{id}")
    HttpEntity<FileSystemResource> image(@PathVariable Long id);

}
