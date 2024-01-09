package com.vmware.tap.accelerators.aichat;

import org.slf4j.Logger;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UploadController {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UploadController.class);

    @Value("${app.vectorstore.path}")
    private String vectorStorePath;

    private final VectorStore vectorStore;

    public UploadController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostMapping("/upload")
    public UploadResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        Path destinationFile = Paths.get("/tmp").resolve(
                Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        TikaDocumentReader documentReader = new TikaDocumentReader(destinationFile.toUri().toString());
        List<Document> documents = documentReader.get();
        List<Document> splitDocuments = new TokenTextSplitter().apply(documents);

        vectorStore.add(splitDocuments);

        if (vectorStore instanceof SimpleVectorStore) {
            ((SimpleVectorStore) vectorStore).save(new File(vectorStorePath));
        }

        return new UploadResponse(file.getOriginalFilename(), file.getContentType(), file.getSize());
    }

    private static record UploadResponse(String fileName, String fileType, long fileSize) {
    }

}
