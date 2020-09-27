package ru.testfield.algorithm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.semantic.SemNodeTypeRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeGroupRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by J.Bgood on 12/15/17.
 */
@Service
public class SemValueTypeGroupService {

    @Autowired
    private SemValueTypeGroupRepository semValueTypeGroupRepository;

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @Autowired
    private SemNodeTypeRepository semNodeTypeRepository;

    @Transactional
    public void save(SemValueTypeGroup semValueTypeGroup){
        if(semValueTypeGroup.getId()!=null) {
            SemValueTypeGroup storedSemValueTypeGroup = semValueTypeGroupRepository.findOne(semValueTypeGroup.getId());
            for (SemValueType storedSemValueType : storedSemValueTypeGroup.getSemValueTypes()) {
                boolean found = false;
                for (SemValueType semValueType : semValueTypeGroup.getSemValueTypes()) {
                    if (semValueType.equals(storedSemValueType)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    storedSemValueType.setSemValueTypeGroup(null);
                }
            }
        }
        for (SemValueType semValueType : semValueTypeGroup.getSemValueTypes()) {
            semValueType.setSemValueTypeGroup(semValueTypeGroup);
        }
        semValueTypeGroupRepository.save(semValueTypeGroup);
    }

    public void populateModel(Model modelForView, SemValueTypeGroup semValueTypeGroup) {
        Iterable<SemValueType> semValueTypes = semValueTypeRepository.findAll();
        Map<SemValueType,Boolean> semValueTypesMap = new HashMap<>();
        for (SemValueType semValueType : semValueTypes) {
            Boolean selected = false;
            if(semValueTypeGroup!=null) {
                selected = semValueTypeGroup.getSemValueTypes().contains(semValueType);
            }
            semValueTypesMap.put(semValueType,selected);
        }
        modelForView.addAttribute("semValueTypesMap",semValueTypesMap);

        Iterable<SemNodeType> semNodeTypes = semNodeTypeRepository.findAll();
        Map<SemNodeType,Boolean> semNodeTypesMap = new HashMap<>();
        for (SemNodeType semNodeType : semNodeTypes) {
            Boolean selected = false;
            if(semValueTypeGroup!=null) {
                selected = semValueTypeGroup.getSemNodeTypes().contains(semNodeType);
            }
            semNodeTypesMap.put(semNodeType,selected);
        }
        modelForView.addAttribute("semNodeTypesMap",semNodeTypesMap);

        modelForView.addAttribute("semValueTypeGroup", semValueTypeGroup);
    }

    public Iterable<SemValueTypeGroup> find(Pageable pageable) {
        return semValueTypeGroupRepository.findAll(pageable);
    }

    public SemValueTypeGroup find(UUID id) {
        return semValueTypeGroupRepository.findOne(id);
    }

    public void delete(UUID id) {
        semValueTypeGroupRepository.delete(id);
    }
}
