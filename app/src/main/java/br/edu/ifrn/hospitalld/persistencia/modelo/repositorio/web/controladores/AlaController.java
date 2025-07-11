package br.edu.ifrn.hospitalld.web.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrn.hospitalld.persistencia.modelo.Ala;
import br.edu.ifrn.hospitalld.persistencia.repositorio.AlaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alas")
public class AlaController  { // Nome da classe corrigido

    @Autowired
    private AlaRepository alaRepository;

    @GetMapping
    public String listar(Model model) {
        List<Ala> alas = alaRepository.findAll();
        model.addAttribute("alas", alas);
        return "alas/lista-alas";
    }

    @GetMapping("/nova")
    public String formulario(Model model) {
        model.addAttribute("ala", new Ala());
        return "alas/formulario-ala";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Ala ala, BindingResult result, Model model) {
        // Valida duplicidade
        // O erro "cannot find symbol" aqui era causado pela importação errada no AlaRepository.
        // Agora deve funcionar.
        if (alaRepository.findByDescricao(ala.getDescricao()).isPresent()) {
            result.rejectValue("descricao", "erro.duplicado", "Já existe uma ala com essa descrição");
        }

        if (result.hasErrors()) {
            return "alas/formulario-ala";
        }

        alaRepository.save(ala);
        return "redirect:/alas";
    }
}