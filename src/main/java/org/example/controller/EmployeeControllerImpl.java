package org.example.controller;

import org.example.controller.interfaces.EmployeeController;
import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class EmployeeControllerImpl implements EmployeeController {

    private EmployeeService service;

    public EmployeeControllerImpl(EmployeeService service) {
        this.service = service;
    }

    public String listAll(Model model) {

        System.out.println("---listAll");

        //вернуть список
        return returnList(model);
    }

    public String getOne(Long id, Model model) {
        // найти сотрудника по ID
        Employee employee = service.getOne(id);

        //вернуть форму для добавления сотрудника с заполненными полями
        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "Update existing");
        model.addAttribute("isUpdate", true);
        model.addAttribute("employee", employee);
        return "employee/form";
    }

    public String newEmployeeForm(Model model) {
        //вернуть форму для добавления сотрудника
        model.addAttribute("mode", "New");
        model.addAttribute("modeTitle", "Add new");
        model.addAttribute("isUpdate", false);
        return "employee/form";
    }

    public String save(MultipartFile image, Employee employee, Model model) {
        System.out.println("---save, content-type: " + image.getContentType() + ", size: " + image.getSize() + ", original filename: " + image.getOriginalFilename());

        Employee saved = service.save(employee);

        if (!image.isEmpty())
            write(image, saved.getId() + "");

        return "redirect:employee";
    }

    public String delete(Long id, Model model) {

        System.out.println("----delete");

        //Удалить из хранилища по ID
        service.delete(id);

        //Вернуть список
        return returnList(model);
    }

    public HttpEntity<FileSystemResource> image(Long id) {
        FileSystemResource fileSystemResource = read(id + "");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + id);
//        try {
//            header.setContentLength(fileSystemResource.contentLength());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return new HttpEntity(fileSystemResource, header);
    }


    //============
    private String returnList(Model model) {
        List<Employee> employees = service.listAll();
        System.out.println(employees);

        model.addAttribute("employees", employees);
        return "employee/list";
    }


    public String write(MultipartFile file, String fileName) {

        String folderPath = System.getProperty("user.home");
        String filePath = folderPath + "/Pictures/" + fileName;

        try {
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private FileSystemResource read(String fileName) {
        String folderPath = System.getProperty("user.home");
        String filePath = folderPath + "/Pictures/" + fileName;

        return new FileSystemResource(new File(filePath));
    }
}
