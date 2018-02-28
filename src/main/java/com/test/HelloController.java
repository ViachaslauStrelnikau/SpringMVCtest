package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileProcServiceImpl FileService;

    @RequestMapping(value = "/*",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Начало работы");
        model.addAttribute("message1", FileService.GetFlist());
        return "hello";

    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String POSTUpload(ModelMap model)
    {
        if(FileService.FileUpload(request))
            model.addAttribute("message", "Файл успешно загружен");
        else
            model.addAttribute("message", "Ошибка при загрузки файла");

        model.addAttribute("message1", FileService.GetFlist());

        return "hello";
    }
    @RequestMapping(value = "/process",method = RequestMethod.POST)
    public String POSTProcess(ModelMap model)
    {
        if(FileService.FileUpload(request))
            model.addAttribute("message", "Файл успешно загружен");
        else
            model.addAttribute("message", "Ошибка при загрузки файла");

        model.addAttribute("message1", FileService.GetFlist());

        return "hello";
    }

}