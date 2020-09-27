package ru.testfield.algorithm.controller.admin.semantic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.controller.admin.AbstractController;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypesRelation;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypesRelationRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeGroupRepository;
import ru.testfield.web.model.cms.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemanticNodetypeController")
@RequestMapping("admin/semantic/nodetype")
public class SemNodeTypeController extends AbstractController {

    @Autowired
    private SemNodeTypeRepository semNodeTypeRepository;

    @Autowired
    private SemValueTypeGroupRepository semValueTypeGroupRepository;

    @Autowired
    private SemNodeTypesRelationRepository semNodeTypesRelationRepository;

    @RequestMapping("")
    public String list(Model model, Pageable pageable){
        Iterable<SemNodeType> nodeTypes = semNodeTypeRepository.findAll(pageable);

        model.addAttribute("nodeTypes",nodeTypes);
        model.addAttribute("title","Node types (entities)");
        model.addAttribute("description","node types list");
        return "admin/pages/semantic/nodetype/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String add(Model modelForView){
        Iterable<SemValueTypeGroup> semValueTypeGroups = semValueTypeGroupRepository.findAll();
        populateModel(modelForView, null);
        modelForView.addAttribute("title","Add node type (entity)");
        return "admin/pages/semantic/nodetype/edit";
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView, RedirectAttributes attr){
        SemNodeType semNodeType = semNodeTypeRepository.findOne(id);
        populateModel(modelForView, semNodeType);
        modelForView.addAttribute("title","Edit node type");
        return "admin/pages/semantic/nodetype/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemNodeType semNodeType, RedirectAttributes attr, HttpServletRequest request) {
        semNodeTypeRepository.save(semNodeType);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semNodeType.getId().toString();
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        List<SemNodeTypesRelation> bySemNodeType = semNodeTypesRelationRepository.findBySemNodeType(id);
        for (SemNodeTypesRelation semNodeTypesRelation : bySemNodeType) {
            semNodeTypesRelationRepository.delete(semNodeTypesRelation.getId());
        }
        semNodeTypeRepository.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/nodetype";
    }

    private void populateModel(Model modelForView, SemNodeType semNodeType) {
        Iterable<SemValueTypeGroup> semValueTypeGroups = semValueTypeGroupRepository.findAll();
        Map<SemValueTypeGroup,Boolean> semValueTypeGroupsMap = new HashMap<>();
        for (SemValueTypeGroup semValueTypeGroup : semValueTypeGroups) {
            Boolean selected = false;
            if(semNodeType!=null){
                selected = semNodeType.getSemValueTypeGroups().contains(semValueTypeGroup);
            }
            semValueTypeGroupsMap.put(semValueTypeGroup,selected);
        }

        modelForView.addAttribute("semNodeType", semNodeType);
        modelForView.addAttribute("semNodeTypes",semNodeTypeRepository.findAll());
        modelForView.addAttribute("semValueTypeGroupsMap", semValueTypeGroupsMap);
    }

}
