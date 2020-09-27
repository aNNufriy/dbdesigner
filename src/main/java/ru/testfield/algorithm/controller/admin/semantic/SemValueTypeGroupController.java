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
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeGroupRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;
import ru.testfield.algorithm.service.SemValueTypeGroupService;
import ru.testfield.web.model.cms.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemanticValueTypeGroupController")
@RequestMapping("admin/semantic/typegroup")
public class SemValueTypeGroupController extends AbstractController {

    @Autowired
    private SemValueTypeGroupService semValueTypeGroupService;

    @RequestMapping("")
    public String list(Model model, Pageable pageable){
        Iterable<SemValueTypeGroup> semValueTypeGroups = semValueTypeGroupService.find(pageable);
        model.addAttribute("semValueTypeGroups",semValueTypeGroups);
        model.addAttribute("title","List");
        return "admin/pages/semantic/valuetypegroup/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String add(Model modelForView){
        semValueTypeGroupService.populateModel(modelForView,null);
        modelForView.addAttribute("title","Add value type group");
        return "admin/pages/semantic/valuetypegroup/edit";
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView){
        SemValueTypeGroup semValueTypeGroup = semValueTypeGroupService.find(id);
        semValueTypeGroupService.populateModel(modelForView,semValueTypeGroup);
        modelForView.addAttribute("title","Edit value type group");
        return "admin/pages/semantic/valuetypegroup/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemValueTypeGroup semValueTypeGroup, RedirectAttributes attr, HttpServletRequest hsr) {
        semValueTypeGroupService.save(semValueTypeGroup);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semValueTypeGroup.getId().toString();
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        semValueTypeGroupService.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/typegroup";
    }

}
