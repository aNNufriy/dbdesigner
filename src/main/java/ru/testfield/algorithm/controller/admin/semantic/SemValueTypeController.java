package ru.testfield.algorithm.controller.admin.semantic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.controller.admin.AbstractController;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.ClassifierRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeGroupRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;
import ru.testfield.web.model.cms.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemanticValueTypeController")
@RequestMapping("admin/semantic/valuetype")
public class SemValueTypeController extends AbstractController {

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @Autowired
    private SemValueTypeGroupRepository semValueTypeGroupRepository;

    @Autowired
    private ClassifierRepository classifierRepository;

    @RequestMapping("")
    public String list(Model model){
        Iterable<SemValueType> semValueTypes = semValueTypeRepository.findAll();

        model.addAttribute("semValueTypes",semValueTypes);
        model.addAttribute("title","Value types");
        model.addAttribute("description","value types list");
        return "admin/pages/semantic/valuetype/list";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public String add(Model modelForView){
        Iterable<SemValueTypeGroup> semValueTypeGroups = semValueTypeGroupRepository.findAll();
        populateModel(modelForView, null);
        modelForView.addAttribute("title","Add value type");
        return "admin/pages/semantic/valuetype/edit";
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView){
        SemValueType semValueType = semValueTypeRepository.findOne(id);
        populateModel(modelForView, semValueType);
        modelForView.addAttribute("title","Change value type");
        return "admin/pages/semantic/valuetype/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemValueType semValueType, RedirectAttributes attr, HttpServletRequest hsr) {
        semValueTypeRepository.save(semValueType);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semValueType.getId().toString();
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        semValueTypeRepository.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/valuetype";
    }

    private void populateModel(Model modelForView, SemValueType semValueType) {
        Iterable<SemValueTypeGroup> semValueTypeGroups = semValueTypeGroupRepository.findAll();

        Map<SemValueTypeGroup,Boolean> semValueTypeGroupsMap = new HashMap<>();
        for (SemValueTypeGroup semValueTypeGroup : semValueTypeGroups) {
            Boolean selected = false;
            if(semValueType!=null){
                selected = semValueTypeGroup.equals(semValueType.getSemValueTypeGroup());
            }
            semValueTypeGroupsMap.put(semValueTypeGroup,selected);
        }

        modelForView.addAttribute("classifiers", classifierRepository.findAll());
        modelForView.addAttribute("semValueType", semValueType);
        modelForView.addAttribute("innerTypes", InnerType.values());
        modelForView.addAttribute("semValueTypeGroupsMap", semValueTypeGroupsMap);
    }
}
