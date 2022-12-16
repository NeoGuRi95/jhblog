package net.wogus.jhblog.app.util;

import net.wogus.jhblog.app.dto.AttachmentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${custom.file.dir}/")
    private String fileDirRootPath;
    /** 오늘 날짜 */
    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    public String todayDir() {
        return fileDirRootPath + "images/" + today;
    }
    public String createPath(String storeFilename) {
        return fileDirRootPath + "images/" + today + "/" + storeFilename;
    }

    public List<AttachmentDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.getSize() > 0) {
                attachmentDtos.add(storeFile(multipartFile));
            }
        }

        return attachmentDtos;
    }

    public AttachmentDto storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        // 금일 이미지 폴더 생성
        String dirPath = todayDir();
        File folder = new File(dirPath);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        multipartFile.transferTo(new File(todayDir() + "/" + storeFilename));

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
