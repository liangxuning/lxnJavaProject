package fileio;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileSystemResourceTest {
    public static void main(String[] args) {
        FileSystemResource fileSystemResource = new FileSystemResource("/Users/liangxuning/梁旭宁.log");
        try {

            String filename = URLEncoder.encode(fileSystemResource.getFilename(), StandardCharsets.UTF_8.toString());
            System.out.println(filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fileSystemResource.getFilename());
        try {
            System.out.println(fileSystemResource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void as() {
        try {
            this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
