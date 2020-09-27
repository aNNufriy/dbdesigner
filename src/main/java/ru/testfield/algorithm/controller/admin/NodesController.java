package ru.testfield.algorithm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.PersistentFile;
import ru.testfield.algorithm.model.bfap.params.ClassifierValue;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.*;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.service.NodeService;
import ru.testfield.web.model.cms.Notification;
import ru.testfield.web.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by J.Bgood on 12/6/17.
 */
@Controller("adminModelsController")
@RequestMapping("admin/data/node")
public class NodesController extends AbstractController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private SemNodeTypeRepository semNodeTypeRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("title", "Data");
        model.addAttribute("description", "data list");
        model.addAttribute("nodes", nodeRepository.findAll());
        model.addAttribute("semNodeTypes",semNodeTypeRepository.findAll());
        return "admin/pages/data/node/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model modelForView) {
        Node node = nodeRepository.findOne(id);
        if (node == null) {
            return "redirect:";
        }
        Map<SemValueType, List<Value>> valuesMap = new HashMap<>();
        for (Value value : node.getValues()) {
            SemValueType semValueType = value.getSemValueType();
            List<Value> values = valuesMap.get(value.getSemValueType());
            if (values == null) {
                values = new LinkedList<>();
                valuesMap.put(semValueType, values);
            }
            values.add(value);
        }

        Iterable<SemNodeType> semNodeTypes = semNodeTypeRepository.findAll();
        modelForView.addAttribute("node", node);
        modelForView.addAttribute("semNodeTypes", semNodeTypes);
        Map<UUID, PersistentFile> files = new HashMap<>();
        for (Value value : node.getValues()) {
            if (value.getSemValueType().getInnerType() == InnerType.FILE) {
                files.put(value.getId(), storageService.getPersistentFileById(value.getUuidValue()));
            }
        }
        modelForView.addAttribute("files", files);
        populateModel(modelForView, node.getSemNodeType(), node);

        modelForView.addAttribute("title", "Change node: \"" + node.getSemNodeType().getName() + "\"");
        modelForView.addAttribute("valuesMap", valuesMap);
        modelForView.addAttribute("semNodeType", node.getSemNodeType());
        return "admin/pages/data/node/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Node node, HttpServletRequest request, RedirectAttributes attr) {
        nodeService.mapValues(node, request);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:edit/" + node.getId().toString();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String node() {
        return "redirect:/admin";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id) {
        nodeRepository.delete(id);
        return "redirect:/admin/data/node";
    }

    @RequestMapping(value = "/addbytype/{id}", method = RequestMethod.GET)
    public String addByType(@PathVariable("id") UUID id, Model model) {
        SemNodeType semNodeType = semNodeTypeRepository.findOne(id);
        model.addAttribute("title", "Add  node: \"" + semNodeType.getName() + "\"");
        model.addAttribute("semNodeType", semNodeType);
        populateModel(model,semNodeType, null);
        return "admin/pages/data/node/edit";
    }

    @RequestMapping(value = "/addbytype", method = RequestMethod.POST)
    public String addbytypePost(@ModelAttribute @Valid Node node, RedirectAttributes attr) {
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:/admin/data/node";
    }

    private void populateModel(Model model, SemNodeType semNodeType, Node node) {
        Map<SemValueType,List<ClassifierValue>> classifierValuesMap = new HashMap<>();

        Set<UUID> selectedClassifierValues = new HashSet<>();

        for(SemValueTypeGroup semValueTypeGroup: semNodeType.getSemValueTypeGroups()){
            for(SemValueType semValueType: semValueTypeGroup.getSemValueTypes()){
                if(semValueType.getInnerType() == InnerType.CLASSIFIER){
                    List<ClassifierValue> classifierValuesByType =
                            classifierValueRepository.findByClassifierOrderByCode(semValueType.getClassifier());
                    classifierValuesMap.put(semValueType,classifierValuesByType);

                    if(node!=null) {
                        Set<Value> values = valueRepository.findByNode_IdAndSemValueType_Id(node.getId(), semValueType.getId());
                        for (Value value : values) {
                            selectedClassifierValues.add(value.getUuidValue());
                        }
                    }
                }
            }
        }
        model.addAttribute("classifierValuesMap",classifierValuesMap);
        model.addAttribute("selectedClassifierValues",selectedClassifierValues);
    }

}
