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
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeTypeGroup;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeGroupRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;
import ru.testfield.web.model.cms.Notification;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminSemNodeTypeGroupController")
@RequestMapping("admin/semantic/nodetypegroup")
public class SemNodeTypeGroupController extends AbstractController {

    @Autowired
    private SemNodeTypeGroupRepository semNodeTypeGroupRepository;

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @RequestMapping("")
    public String list(Model model, Pageable pageable){
        Iterable<SemNodeTypeGroup> groups = semNodeTypeGroupRepository.findAll(pageable);

        model.addAttribute("groups",groups);
        model.addAttribute("title","Data types groups");
        model.addAttribute("description","groups list");
        return "admin/pages/semantic/nodetypegroup/list";
    }

    @RequestMapping(value = "{id}/delete", method= RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id){
        semNodeTypeGroupRepository.delete(id);
        HashMap<String, UUID> result = new HashMap<>();
        result.put("id",id);
        return "redirect:/admin/semantic/nodetypegroup";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model modelForView){
        modelForView.addAttribute("title","Add group");
        return "admin/pages/semantic/nodetypegroup/edit";
    }

    @RequestMapping(value = "/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute @Valid SemNodeTypeGroup semNodeTypeGroup, RedirectAttributes attr) {
        semNodeTypeGroupRepository.save(semNodeTypeGroup);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS,"Changes saved"))
        );
        return "redirect:edit/"+ semNodeTypeGroup.getId().toString();
    }

    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView, RedirectAttributes attr){
        SemNodeTypeGroup group = semNodeTypeGroupRepository.findOne(id);

        modelForView.addAttribute("group", group);
        modelForView.addAttribute("title","Change group");
        return "admin/pages/semantic/nodetypegroup/edit";
    }

}
