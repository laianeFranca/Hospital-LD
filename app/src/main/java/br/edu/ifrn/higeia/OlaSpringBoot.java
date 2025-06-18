package br.edu.ifrn.higeia;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class OlaSpringBoot {

    @GetMapping("/")
    public String index(){
        return "Olá Spring Boot ";
    }

}
