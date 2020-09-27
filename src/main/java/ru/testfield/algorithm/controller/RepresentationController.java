package ru.testfield.algorithm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.NodeViewModel;
import ru.testfield.algorithm.model.bfap.PersistentFile;
import ru.testfield.algorithm.model.bfap.params.Classifier;
import ru.testfield.algorithm.model.bfap.params.ClassifierValue;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.ClassifierRepository;
import ru.testfield.algorithm.repository.ClassifierValueRepository;
import ru.testfield.algorithm.repository.NodeRepository;
import ru.testfield.algorithm.repository.ValueRepository;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.service.NodeService;
import ru.testfield.web.exception.EntityNotFoundException;
import ru.testfield.web.model.cms.MenuItem;
import ru.testfield.web.model.cms.Notification;
import ru.testfield.web.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("storage")
public class RepresentationController {

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

    @Autowired
    private ClassifierRepository classifierRepository;

    UUID PS_ID = UUID.fromString("c46dcf8a-7679-43d0-baa1-f87f606be11c");

    @ModelAttribute
    public void addMenuItems(HttpServletRequest request, Model model){
        List<MenuItem> menu = new ArrayList<>();
        String servletPath = request.getServletPath();

        for (SemNodeType semNodeType : semNodeTypeRepository.findAll()) {
            menu.add(new MenuItem("/storage/"+semNodeType.getId(),semNodeType.getName(),"fa-user", servletPath));
        }

        model.addAttribute("menu",menu);
    }

    @RequestMapping("ps")
    public String list(Model model) {
        model.addAttribute("title", "Data");
        model.addAttribute("description", "data list");
        model.addAttribute("nodes", NodeViewModel.mapNodes(nodeRepository.findBySemNodeType_Id(PS_ID)));

        return "admin/pages/bap/ps/list";
    }

    @RequestMapping(value = "/{id}")
    public String listNodes(Model model, @PathVariable("id") UUID semNodeTypeId) {
        List<Node> nodesList = nodeRepository.findBySemNodeType_Id(semNodeTypeId);
        model.addAttribute("title", "Data");
        model.addAttribute("description", "data list");
        model.addAttribute("semNodeTypeId",semNodeTypeId);
        model.addAttribute("nodes", nodesList);

        return "admin/pages/bap/ps/list";
    }

    @RequestMapping(value = "ps/edit/{id}", method = RequestMethod.GET)
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

        modelForView.addAttribute("title", "Change \"" + node.getSemNodeType().getName() + "\"");
        modelForView.addAttribute("valuesMap", valuesMap);
        modelForView.addAttribute("semNodeType", node.getSemNodeType());
        return "admin/pages/bap/ps/edit";
    }

    @RequestMapping(value = "ps/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Node node, HttpServletRequest request, RedirectAttributes attr) {
        nodeService.mapValues(node, request);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:edit/" + node.getId().toString();
    }

    @RequestMapping(value = "ps/edit", method = RequestMethod.GET)
    public String node() {
        return "redirect:/admin";
    }

    @RequestMapping(value = "ps/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id) {
        nodeRepository.delete(id);
        return "redirect:/storage/ps";
    }

    @RequestMapping(value = "ps/add", method = RequestMethod.GET)
    public String addByType(Model model) {
        SemNodeType semNodeType = semNodeTypeRepository.findOne(PS_ID);
        model.addAttribute("title", "Add \"" + semNodeType.getName() + "\"");
        model.addAttribute("semNodeType", semNodeType);
        populateModel(model,semNodeType, null);
        return "admin/pages/bap/ps/edit";
    }

    @RequestMapping(value = "ps/add", method = RequestMethod.POST)
    public String addbytypePost(@ModelAttribute @Valid Node node, RedirectAttributes attr) {
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:/storage/ps";
    }

    @RequestMapping("cl")
    public String listCl(Model model) {
        model.addAttribute("title", "Classifiers");
        model.addAttribute("description", "classifiers list");
        model.addAttribute("classifiers", classifierRepository.findAll());
        return "admin/pages/bap/cl/list";
    }

    @RequestMapping(value = "cl/{id}/edit", method = RequestMethod.GET)
    public String editCl(@PathVariable("id") Classifier classifier, Model model) throws EntityNotFoundException {
        if(classifier==null) {
            throw new EntityNotFoundException("object not found");
        }
        model.addAttribute("title", "Edit classifier");
        model.addAttribute("classifierValues", classifierValueRepository.findByClassifierOrderByCode(classifier));
        model.addAttribute("classifier", classifierRepository.findOne(classifier.getId()));

        return "admin/pages/bap/cl/edit";
    }

    @RequestMapping(value = "cl/edit", method = RequestMethod.POST)
    public String editCl(Classifier postedClassifier, HttpServletRequest request, RedirectAttributes attr) {
        Classifier classifier = classifierRepository.save(postedClassifier);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:/storage/cl/" + classifier.getId() + "/edit";
    }

    @RequestMapping(value = "cl/edit", method = RequestMethod.GET)
    public String editCl() {
        return "redirect:/admin/data/classifier";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCl(Model model) {
        model.addAttribute("title", "Add classifier");
        return "admin/pages/bap/cl/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/cl/value/{id}/delete")
    public Map<String,Object> deleteClValue(@PathVariable("id") UUID id) {
        classifierValueRepository.delete(id);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("id",id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cl/value/edit", method = RequestMethod.POST)
    public Map<String,Object> editClValue(ClassifierValue classifierValue) {
        classifierValueRepository.save(classifierValue);
        Map<String,Object> result = new HashMap<>();
        result.put("classifierValue",classifierValue);
        result.put("success",true);
        return result;
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
