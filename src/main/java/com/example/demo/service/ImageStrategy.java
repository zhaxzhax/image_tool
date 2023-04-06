package com.example.demo.service;

import com.example.demo.service.model.Response;
import com.example.demo.service.util.ImageUtil;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImageStrategy {

    boolean typeMatch(String type);

    default Response validSourceFile(String sourcePath, String targetType) {
        String sourceType = ImageUtil.getFileType(sourcePath);
        if (StringUtils.isBlank(sourceType)) {
            return Response.getError("File type not support");
        }
        if (sourceType.equals(targetType)) {
            return Response.getError("Source file has the same type with target");
        }
        return Response.getOK();
    }

    default Response convert(String sourcePath, String targetPath) {
        String targetType = ImageUtil.getFileType(targetPath);
        try {
            BufferedImage read = ImageIO.read(new File(sourcePath));
            boolean convertResult = ImageIO.write(read, targetType, new File(targetPath));
            if (!convertResult) {
                return Response.getError("Convert Image to " + targetType + " error");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.getError("Convert Image to " + targetType + " error");
        }
        return Response.getOK();
    }


}
