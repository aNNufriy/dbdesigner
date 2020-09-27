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
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypesRelation;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypesRelationRepository;
import ru.testfield.algorithm.repository.semantic.SemRelationRepository;
import ru.testfield.web.model.cms.Notification;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemNodeTypesRelationController")
@RequestMapping("admin/semantic/semnodetypes_relation")
public class SemNodeTypesRelationController extends AbstractController {

    @Autowired
    private SemNodeTypesRelationRepository semNodeTypesRelationRepository;

    @Autowired
    private SemNodeTypeRepository semNodeTypeRepository;

    @Autowired
    private SemRelationRepository semRelationRepository;

    @RequestMapping("")
    public String list(Model model, Pageable pageable){
        Iterable<SemNodeTypesRelation> semNodeTypesRelations = semNodeTypesRelationRepository.findAll(pageable);

        model.addAttribute("semNodeTypesRelations",semNodeTypesRelations);
        model.addAttribute("title","Node type relations");
        model.addAttribute("description","");
        return "admin/pages/semantic/semnodetypes_relation/list";
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        semNodeTypesRelationRepository.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/semnodetypes_relation";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String add(Model modelForView){
        modelForView.addAttribute("title","Add relation");
        modelForView.addAttribute("semNodeTypes",semNodeTypeRepository.findAll());
        modelForView.addAttribute("semRelations",semRelationRepository.findAll());
        return "admin/pages/semantic/semnodetypes_relation/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemNodeTypesRelation semNodeTypesRelation, RedirectAttributes attr) {
        semNodeTypesRelationRepository.save(semNodeTypesRelation);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semNodeTypesRelation.getId().toString();
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView){
        SemNodeTypesRelation semNodeTypesRelation = semNodeTypesRelationRepository.findOne(id);
        modelForView.addAttribute("semNodeTypesRelation", semNodeTypesRelation);
        modelForView.addAttribute("title","Change relation");
        modelForView.addAttribute("semNodeTypes",semNodeTypeRepository.findAll());
        modelForView.addAttribute("semRelations",semRelationRepository.findAll());
        return "admin/pages/semantic/semnodetypes_relation/edit";
    }

}
