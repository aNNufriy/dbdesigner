package ru.testfield.algorithm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.testfield.algorithm.model.bfap.params.Classifier;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.repository.ClassifierRepository;
import ru.testfield.algorithm.repository.ClassifierValueRepository;
import ru.testfield.algorithm.repository.ValueRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeGroupRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;
import ru.testfield.web.exception.EntityNotFoundException;
import ru.testfield.web.model.cms.Notification;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@Controller
@RequestMapping("admin/data/classifier")
public class ClassifiersController extends AbstractController {

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Autowired
    private ClassifierRepository classifierRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private SemValueTypeGroupRepository semValueTypeGroupRepository;

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("title", "Classifiers");
        model.addAttribute("description", "classifiers list");
        model.addAttribute("classifiers", classifierRepository.findAll());
        return "admin/pages/data/classifier/list";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") UUID id) {
        Classifier classifier = classifierRepository.findOne(id);
        for (SemValueType semValueType : classifier.getSemValueTypes()) {
            valueRepository.deleteBySemValueType_Id(semValueType.getId());
        }
        classifierValueRepository.deleteByClassifier_Id(id);
        classifierRepository.delete(id);
        return "redirect:/admin/data/classifier";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Classifier classifier, Model model) throws EntityNotFoundException {
        if(classifier==null) {
            throw new EntityNotFoundException("object not found");
        }
        model.addAttribute("title", "Edit classifier");
        model.addAttribute("classifierValues", classifierValueRepository.findByClassifierOrderByCode(classifier));
        model.addAttribute("classifier", classifierRepository.findOne(classifier.getId()));
        model.addAttribute("semValueTypeGroups", semValueTypeGroupRepository.findAll());

        return "admin/pages/data/classifier/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Classifier postedClassifier, HttpServletRequest request, RedirectAttributes attr) {
        Classifier classifier = classifierRepository.save(postedClassifier);
        attr.addFlashAttribute("notifications",
                Arrays.asList(new Notification(Notification.NotificationType.SUCCESS, "Changes saved"))
        );
        return "redirect:/admin/data/classifier/" + classifier.getId() + "/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit() {
        return "redirect:/admin/data/classifier";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Create classifier");
        model.addAttribute("semValueTypeGroups", semValueTypeGroupRepository.findAll());
        return "admin/pages/data/classifier/edit";
    }

}
