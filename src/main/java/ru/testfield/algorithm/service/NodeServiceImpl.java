package ru.testfield.algorithm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.model.bfap.params.semantic.SemNodeType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueTypeGroup;
import ru.testfield.algorithm.repository.NodeRepository;
import ru.testfield.algorithm.repository.ValueRepository;
import ru.testfield.algorithm.repository.semantic.SemValueTypeRepository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by J.Bgood on 6/24/18.
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private EntityManager em;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private SemValueTypeRepository semValueTypeRepository;

    @Transactional
    public void mapValues(Node node, HttpServletRequest request) {
        String name = node.getName();

        if(node!=null && node.getId()!=null) {
            node = nodeRepository.findOne(node.getId());
            node.setName(name);
            removeAllButFileValues(node);
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, SemValueType> allValueTypes = takeSemValueTypesFromNode(node);

        Pattern pattern = Pattern.compile("^nodeValue-([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12})-(stored|new)$");
        putValuesIntoNode(node, parameterMap, allValueTypes, pattern);
        pattern = Pattern.compile("^file-([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12})$");
        putValuesIntoNode(node, parameterMap, allValueTypes, pattern);
        pattern = Pattern.compile("^classifier-([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12})$");
        putValuesIntoNode(node, parameterMap, allValueTypes, pattern);

        nodeRepository.save(node);
    }

    private void removeAllButFileValues(Node node) {
        Iterator<Value> valuesIterator = node.getValues().iterator();
        while (valuesIterator.hasNext()) {
            Value next = valuesIterator.next();
            if (next.getSemValueType().getInnerType() != InnerType.FILE) {
                next.setNode(null);
                valuesIterator.remove();
            }
        }
    }

    private void putValuesIntoNode(Node node, Map<String, String[]> parameterMap, Map<String, SemValueType> allValueTypes, Pattern pattern) {
        for (String parameter : parameterMap.keySet()) {
            Matcher matcher = pattern.matcher(parameter);
            if (matcher.find()) {
                String valueTypeUUIDString = matcher.group(1);
                if (allValueTypes.containsKey(valueTypeUUIDString)) {
                    String[] parameters = parameterMap.get(parameter);
                    SemValueType semValueType = semValueTypeRepository.findOne(UUID.fromString(valueTypeUUIDString));
                    if (parameters != null && parameters.length != 0) {
                        if (semValueType != null) {
                            for (String receivedValue : parameters) {
                                if (!"".equals(receivedValue)) {
                                    Value value = new Value();
                                    value.setNode(node);
                                    node.getValues().add(value);
                                    value.setSemValueType(semValueType);
                                    switch (semValueType.getInnerType()) {
                                        case STRING:
                                            value.setStringValue(receivedValue);
                                            break;
                                        case FILE:
                                            value.setUuidValue(UUID.fromString(receivedValue));
                                            break;
                                        case DOUBLE:
                                            value.setDoubleValue(Double.valueOf(receivedValue));
                                            break;
                                        case LONG:
                                            value.setLongValue(Long.valueOf(receivedValue));
                                            break;
                                        case CLASSIFIER:
                                            value.setUuidValue(UUID.fromString(receivedValue));
                                            break;
                                        case DATETIME:
                                            DateFormat format = DateFormat.getDateInstance();
                                            try {
                                                value.setDateValue(format.parse(receivedValue));
                                            } catch (ParseException e) {
                                                throw new RuntimeException("unable to parse date: " + receivedValue);
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("invalid value - SemValueType \"" + valueTypeUUIDString);
                }
            }
        }
    }

    private Map<String, SemValueType> takeSemValueTypesFromNode(Node node) {
        SemNodeType semNodeType = node.getSemNodeType();
        Set<SemValueTypeGroup> semValueTypeGroups = semNodeType.getSemValueTypeGroups();

        Map<String, SemValueType> allValueTypes = new HashMap<>();
        for (SemValueTypeGroup semValueTypeGroup : semValueTypeGroups) {
            for (SemValueType semValueType : semValueTypeGroup.getSemValueTypes()) {
                allValueTypes.put(semValueType.getId().toString(), semValueType);
            }
        }
        return allValueTypes;
    }

}
