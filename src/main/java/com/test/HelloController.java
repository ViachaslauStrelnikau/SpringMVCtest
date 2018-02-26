package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/*")
public class HelloController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileProcServiceImpl FileService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC - Hello World");
        return "hello";

    }
    @RequestMapping(method = RequestMethod.POST)
    public String POSTWelcome(ModelMap model)
    {
        if(FileService.FileUpload(request))
            model.addAttribute("message", "Файл успешно загружен");
        else
            model.addAttribute("message", "Ошибка при загрузки файла");

        model.addAttribute("message1", FileService.GetFlist());

        return "hello";
    }

}