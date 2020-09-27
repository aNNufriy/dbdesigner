package ru.testfield.algorithm.controller.admin.semantic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.controller.admin.AbstractController;
import ru.testfield.algorithm.model.bfap.params.semantic.SemRelation;
import ru.testfield.algorithm.repository.semantic.SemRelationRepository;
import ru.testfield.algorithm.repository.specifications.NodesRelationRepository;
import ru.testfield.web.model.cms.Notification;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemRelationController")
@RequestMapping("admin/semantic/relation")
public class SemRelationsController extends AbstractController {

    @Autowired
    private SemRelationRepository semRelationRepository;

    @Autowired
    private NodesRelationRepository nodesRelationRepository;

    @RequestMapping("")
    public String list(Model model, Pageable pageable){
        Iterable<SemRelation> semRelations = semRelationRepository.findAll(pageable);

        model.addAttribute("semRelations",semRelations);
        model.addAttribute("title","Relations");
        model.addAttribute("description","Node types relations");
        return "admin/pages/semantic/relation/list";
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        semRelationRepository.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/relation";
    }


    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String add(Model modelForView){
        modelForView.addAttribute("title","Add relation");
        return "admin/pages/semantic/relation/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemRelation semRelation, RedirectAttributes attr) {
        semRelationRepository.save(semRelation);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semRelation.getId().toString();
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView){
        SemRelation semRelation = semRelationRepository.findOne(id);
        modelForView.addAttribute("semRelation", semRelation);
        modelForView.addAttribute("title","Change data type");
        return "admin/pages/semantic/relation/edit";
    }

}
