package br.edu.ifrn.hospitalld.persistencia.web.controladores;

import br.edu.ifrn.hospitalld.persistencia.modelo.Ala;
import br.edu.ifrn.hospitalld.persistencia.repositorio.AlaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alas")
@RequiredArgsConstructor
public class AlaController {

    private final AlaRepository alaRepository;

    // === LISTAR TODAS AS ALAS ===
    @GetMapping
    public String listarAlas(Model model) {
        try {
            List<Ala> alas = alaRepository.findAll();
            model.addAttribute("alas", alas);
            return "alas/lista";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar alas: " + e.getMessage());
            return "alas/lista";
        }
    }

    // === FORMULÁRIO PARA NOVA ALA ===
    @GetMapping("nova")
    public String mostrarFormularioNovaAla(Model model) {
        model.addAttribute("ala", new Ala());
        return "alas/formulario";
    }

    // === SALVAR NOVA ALA ===
    @PostMapping("/salvar")
    public String salvarAla(@Valid @ModelAttribute("ala") Ala ala, 
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        
        // Validação básica
        if (result.hasErrors()) {
            return "alas/formulario";
        }
        
        try {
            // Verificar se nome já existe
            if (alaRepository.existsByNome(ala.getNome())) {
                model.addAttribute("erro", "Já existe uma ala com este nome: " + ala.getNome());
                return "alas/formulario";
            }
            
            alaRepository.save(ala);
            redirectAttributes.addFlashAttribute("sucesso", "Ala criada com sucesso: " + ala.getNome());
            return "redirect:/alas";
            
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao salvar ala: " + e.getMessage());
            return "alas/formulario";
        }
    }

    // === FORMULÁRIO PARA EDITAR ALA ===
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAla(@PathVariable("id") Long id, Model model) {
        try {
            return alaRepository.findById(id)
                    .map(ala -> {
                        model.addAttribute("ala", ala);
                        return "alas/editar";
                    })
                    .orElseGet(() -> {
                        model.addAttribute("erro", "Ala não encontrada com ID: " + id);
                        return "redirect:/alas";
                    });
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar ala: " + e.getMessage());
            return "redirect:/alas";
        }
    }

    // === ATUALIZAR ALA ===
    @PostMapping("/atualizar")
    public String atualizarAla(@Valid @ModelAttribute("ala") Ala ala,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "alas/editar";
        }
        
        try {
            // Verificar se outro registro tem o mesmo nome (exceto o atual)
            boolean nomeEmUso = alaRepository.findAll().stream()
                    .filter(a -> a.getNome().equals(ala.getNome()))
                    .anyMatch(a -> !a.getId().equals(ala.getId()));
            
            if (nomeEmUso) {
                model.addAttribute("erro", "Já existe outra ala com este nome: " + ala.getNome());
                return "alas/editar";
            }
            
            alaRepository.save(ala);
            redirectAttributes.addFlashAttribute("sucesso", "Ala atualizada com sucesso: " + ala.getNome());
            return "redirect:/alas";
            
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar ala: " + e.getMessage());
            return "alas/editar";
        }
    }

    // === EXCLUIR ALA ===
    @GetMapping("/excluir/{id}")
    public String excluirAla(@PathVariable("id") Long id, 
                            RedirectAttributes redirectAttributes) {
        try {
            if (alaRepository.existsById(id)) {
                alaRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("sucesso", "Ala excluída com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Ala não encontrada com ID: " + id);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir ala: " + e.getMessage());
        }
        return "redirect:/alas";
    }

    // === MÉTODO AUXILIAR PARA ANDARES ===
    @ModelAttribute("andares")
    public int[] getAndares() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}