package ru.testfield.web.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.testfield.algorithm.model.bfap.PersistentFile;
import ru.testfield.algorithm.model.bfap.params.InnerType;
import ru.testfield.algorithm.model.bfap.params.Value;
import ru.testfield.algorithm.repository.PersistentFileRepository;
import ru.testfield.algorithm.repository.ValueRepository;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by J.Bgood on 1/6/18.
 */
@Service
public class StorageService {

    @org.springframework.beans.factory.annotation.Value("${testfield.cms.fileStoragePath}")
    private String fileStoragePath;

    @Autowired
    private PersistentFileRepository persistentFileRepository;

    @Autowired
    private ValueRepository valueRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    public PersistentFile store(MultipartFile file) {

        String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (file != null && !file.isEmpty()) {
            try {
                File dir = new File(fileStoragePath+"/"+dateDir);
                if (!dir.exists()) dir.mkdirs();

                File serverFile = new File(buildAbsolutPath(file, dir));
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                logger.debug("Saved file location = " + serverFile.getAbsolutePath());

                PersistentFile persistentFile = new PersistentFile();
                persistentFile.setName(file.getOriginalFilename());
                persistentFile.setPath(dateDir+"/"+serverFile.toPath().getFileName().toString());
                persistentFile.setSize(serverFile.length());

                String hash = DatatypeConverter.printHexBinary(MessageDigest
                    .getInstance("MD5")
                    .digest(file.getBytes())
                ).toLowerCase();

                persistentFile.setMd5Hash(hash);

                persistentFileRepository.save(persistentFile);

                return persistentFile;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    private String buildAbsolutPath(MultipartFile file, File dir) {
        String pathName = dir.getAbsolutePath()
                + File.separator
                + UUID.randomUUID();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(extension!=null&&!extension.isEmpty()){
            pathName = pathName + "." + extension;
        }
        return pathName;
    }

    public PersistentFile getById(UUID fileId){
        return persistentFileRepository.findOne(fileId);
    }

    public boolean removeFile(PersistentFile persistentFile) {
        File file = new File(fileStoragePath+"/"+persistentFile.getPath());
        valueRepository.deleteByUuidValue(persistentFile.getId());
        if(file==null){
            throw new RuntimeException("unable to find file in filesystem");
        }
        persistentFileRepository.delete(persistentFile);
        return file.delete();
    }

    public PersistentFile getPersistentFileById(UUID persistentFileId){
        return persistentFileRepository.findOne(persistentFileId);
    }

    public FileSystemResource getFileResourceByFileId(UUID fileId) {
        String pathName = fileStoragePath+"/"+persistentFileRepository.findOne(fileId).getPath();
        return new FileSystemResource(new File(pathName));
    }
}
