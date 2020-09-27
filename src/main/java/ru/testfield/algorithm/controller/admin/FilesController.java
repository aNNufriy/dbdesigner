package ru.testfield.algorithm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.testfield.algorithm.model.bfap.Node;
import ru.testfield.algorithm.model.bfap.PersistentFile;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.model.bfap.params.semantic.SemValueType;
import ru.testfield.algorithm.repository.PersistentFileRepository;
import ru.testfield.algorithm.repository.ValueRepository;
import ru.testfield.web.service.StorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private PersistentFileRepository persistentFileRepository;

    @Autowired
    private StorageService storageService;

    @ResponseBody
    @RequestMapping(value = "/{fileId}", method= RequestMethod.GET)
    public FileSystemResource getFileById(@PathVariable("fileId") UUID fileId){
        return storageService.getFileResourceByFileId(fileId);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method= RequestMethod.POST)
    public PersistentFile uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
              @RequestParam(value = "semValueTypeId", required = true) SemValueType semValueType,
              @RequestParam(value = "nodeId", required = false) Node node) {
        PersistentFile persistentFile = storageService.store(file);
        if(node!=null){
            Value value = new Value();
            value.setUuidValue(persistentFile.getId());
            value.setNode(node);
            value.setSemValueType(semValueType);
            valueRepository.save(value);
        }
        return persistentFile;
    }

    @ResponseBody
    @RequestMapping(value = "/{fileId}/deleteNodeFile", method= RequestMethod.GET)
    public Map deleteFile(@PathVariable("fileId") PersistentFile persistentFile){
        Map result = new HashMap();
        result.put("result",storageService.removeFile(persistentFile));
        return result;
    }

}
