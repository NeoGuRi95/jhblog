package net.wogus.jhblog.app.attachment;

import net.wogus.jhblog.app.attachment.dto.AttachmentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${custom.file.dir}/")
    private String fileDirPath;

    public String createPath(String storeFilename) {
        return fileDirPath + "images/" + storeFilename;
    }

    public List<AttachmentDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                attachmentDtos.add(storeFile(multipartFile));
            }
        }

        return attachmentDtos;
    }

    public AttachmentDto storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        multipartFile.transferTo(new File(createPath(storeFilename)));

        return AttachmentDto.builder()
                .orgFileName(originalFilename)
                .storePath(storeFilename)
                .build();

    }

    private String createStoreFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        String storeFilename = uuid + ext;

        return storeFilename;
    }

    private String extractExt(String originalFilename) {
        int idx = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(idx);
        return ext;
    }
}
