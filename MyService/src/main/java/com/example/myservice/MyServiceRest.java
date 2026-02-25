package com.example.myservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServiceRest {

    @GetMapping("/")
    public String sayHello() {
        return "<html>" +
                "<head><style>" +
                "body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f2f5; }"
                +
                ".card { background: white; padding: 2rem; border-radius: 15px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); text-align: center; border-top: 5px solid #28a745; }"
                +
                "h1 { color: #28a745; margin-bottom: 10px; }" +
                "p { color: #555; font-size: 1.2rem; }" +
                ".version { background: #e8f5e9; color: #2e7d32; padding: 5px 15px; border-radius: 20px; font-weight: bold; display: inline-block; margin-top: 10px; }"
                +
                "</style></head>" +
                "<body>" +
                "  <div class='card'>" +
                "    <h1>Hello World !</h1>" +
                "    <p>Mon service Kubernetes fonctionne parfaitement.</p>" +
                "    <div class='version'>C'est la version 2.0.0 (Deployment OK)</div>" +
                "  </div>" +
                "</body>" +
                "</html>";
    }
}