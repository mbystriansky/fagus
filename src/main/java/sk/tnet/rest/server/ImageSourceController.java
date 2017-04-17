package sk.tnet.rest.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/dir/{dir}")
public class ImageSourceController {
    Logger LOG = LoggerFactory.getLogger(ImageSourceController.class);

    @Value("${fagus.app.images.root-dir}")
    private String rootDir;

    @RequestMapping("/images")
    public Map<String, String[]> images(@PathVariable String dir) {
        File directory = new File(rootDir, dir);
        String[] fileList = directory.list();
        return Collections.singletonMap("images", fileList);
    }

    @RequestMapping("/image/{image}")
    public Map<String, String> image(@PathVariable String dir, @PathVariable String image) {
        File file = firstMatchedFile(dir, image);
        return Collections.singletonMap("image", file.getName());
    }

    @RequestMapping(value = "/image/{image}/data")
    public ResponseEntity<InputStreamResource> imageData(@PathVariable String dir, @PathVariable String image)
            throws FileNotFoundException {
        File file = firstMatchedFile(dir, image);
        InputStreamResource streamResource = new InputStreamResource(new FileInputStream(file));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(file.length());
        headers.setContentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(file.getName())));
        return new ResponseEntity<InputStreamResource>(streamResource, headers, HttpStatus.OK);
    }

    private File firstMatchedFile(String dir, String image) {
        File directory = new File(rootDir, dir);
        File[] file = directory.listFiles(new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return FilenameUtils.removeExtension(name).equals(image);
            }
        });
        if (file.length == 0) {
            throw new RuntimeException("Nebol najdeny subor vyhovujuci nazvu: " + image);
        }
        return file[0];
    }

}
