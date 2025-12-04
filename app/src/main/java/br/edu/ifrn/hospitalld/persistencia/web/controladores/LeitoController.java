package br.edu.ifrn.hospitalld.persistencia.web.controladores;

import br.edu.ifrn.hospitalld.persistencia.modelo.Leito;
import br.edu.ifrn.hospitalld.persistencia.repositorio.LeitoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/leitos")
@RequiredArgsConstructor
public class LeitoController {
    
    private final LeitoRepository leitoRepository;
    
    // READ - Listar todos os leitos
    @GetMapping
    public String listar(
            @RequestParam(name = "minimo", required = false) Integer minimo,
            @RequestParam(name = "descricao", required = false) String descricao,
            Model model) {
        
        List<Leito> leitos;
        
        if (minimo != null && minimo > 0) {
            leitos = leitoRepository.findByNumeroLeitosGreaterThan(minimo);
            model.addAttribute("filtroMinimo", minimo);
        } else if (descricao != null && !descricao.isEmpty()) {
            String descricaoLower = descricao.toLowerCase();
            leitos = leitoRepository.findAll().stream()
                    .filter(leito -> leito.getDescricao().toLowerCase().contains(descricaoLower))
                    .toList();
            model.addAttribute("filtroDescricao", descricao);
        } else {
            leitos = leitoRepository.findAll();
        }
        
        // Calcula estatísticas
        int totalLeitos = leitos.stream().mapToInt(Leito::getNumeroLeitos).sum();
        model.addAttribute("leitos", leitos);
        model.addAttribute("totalLeitos", totalLeitos);
        model.addAttribute("quantidadeRegistros", leitos.size());
        
        return "leitos/lista-leitos";
    }
    
    // READ - Visualizar detalhes de um leito
    @GetMapping("/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        return leitoRepository.findById(id)
                .map(leito -> {
                    model.addAttribute("leito", leito);
                    return "leitos/detalhe-leito";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Leito não encontrado com ID: " + id);
                    return "error/404";
                });
    }
    
    // CREATE - Formulário para novo leito
    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("leito", new Leito());
        model.addAttribute("modo", "novo");
        return "leitos/formulario-leito";
    }
    
    // CREATE - Salvar novo leito
    @PostMapping
    public String salvar(
            @Valid @ModelAttribute Leito leito,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // Valida duplicidade da descrição
        if (leitoRepository.existsByDescricao(leito.getDescricao())) {
            result.rejectValue("descricao", "erro.duplicado", 
                    "Já existe um leito com essa descrição");
        }
        
        // Valida número de leitos
        if (leito.getNumeroLeitos() <= 0) {
            result.rejectValue("numeroLeitos", "erro.invalido", 
                    "O número de leitos deve ser maior que zero");
        }
        
        if (result.hasErrors()) {
            model.addAttribute("modo", "novo");
            return "leitos/formulario-leito";
        }
        
        try {
            leitoRepository.save(leito);
            redirectAttributes.addFlashAttribute("sucesso", 
                    "Leito criado com sucesso: " + leito.getDescricao());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", 
                    "Erro de integridade. Verifique se os dados são válidos.");
            model.addAttribute("modo", "novo");
            return "leitos/formulario-leito";
        }
        
        return "redirect:/leitos";
    }
    
    // UPDATE - Formulário para editar leito
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return leitoRepository.findById(id)
                .map(leito -> {
                    model.addAttribute("leito", leito);
                    model.addAttribute("modo", "editar");
                    return "leitos/formulario-leito";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Leito não encontrado com ID: " + id);
                    return "error/404";
                });
    }
    
    // UPDATE - Atualizar leito existente
    @PutMapping("/{id}")
    public String atualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute Leito leito,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // Valida duplicidade da descrição (exceto para o próprio leito)
        leitoRepository.findByDescricao(leito.getDescricao())
                .ifPresent(l -> {
                    if (!l.getId().equals(id)) {
                        result.rejectValue("descricao", "erro.duplicado", 
                                "Já existe outro leito com essa descrição");
                    }
                });
        
        // Valida número de leitos
        if (leito.getNumeroLeitos() <= 0) {
            result.rejectValue("numeroLeitos", "erro.invalido", 
                    "O número de leitos deve ser maior que zero");
        }
        
        if (result.hasErrors()) {
            model.addAttribute("modo", "editar");
            return "leitos/formulario-leito";
        }
        
        try {
            leito.setId(id); // Garante que o ID seja mantido
            leitoRepository.save(leito);
            
            redirectAttributes.addFlashAttribute("sucesso", 
                    "Leito atualizado com sucesso: " + leito.getDescricao());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", 
                    "Erro de integridade ao atualizar. Verifique se os dados são válidos.");
            model.addAttribute("modo", "editar");
            return "leitos/formulario-leito";
        } catch (Exception e) {
            model.addAttribute("erro", 
                    "Erro ao atualizar leito: " + e.getMessage());
            model.addAttribute("modo", "editar");
            return "leitos/formulario-leito";
        }
        
        return "redirect:/leitos";
    }
    
    // DELETE - Formulário de confirmação
    @GetMapping("/{id}/excluir")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        return leitoRepository.findById(id)
                .map(leito -> {
                    model.addAttribute("leito", leito);
                    return "leitos/confirmar-exclusao";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Leito não encontrado com ID: " + id);
                    return "error/404";
                });
    }
    
    // DELETE - Excluir leito
    @DeleteMapping("/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        
        return leitoRepository.findById(id)
                .map(leito -> {
                    try {
                        leitoRepository.deleteById(id);
                        redirectAttributes.addFlashAttribute("sucesso", 
                                "Leito excluído com sucesso: " + leito.getDescricao());
                    } catch (Exception e) {
                        redirectAttributes.addFlashAttribute("erro", 
                                "Erro ao excluir leito. Verifique se não há dependências.");
                    }
                    return "redirect:/leitos";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("erro", 
                            "Leito não encontrado com ID: " + id);
                    return "redirect:/leitos";
                });
    }
    
    // Método para buscar por descrição
    @GetMapping("/buscar")
    public String buscarPorDescricao(
            @RequestParam String descricao,
            Model model) {
        
        return leitoRepository.findByDescricao(descricao)
                .map(leito -> {
                    model.addAttribute("leito", leito);
                    return "leitos/detalhe-leito";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Leito não encontrado com descrição: " + descricao);
                    return "leitos/lista-leitos";
                });
    }
}