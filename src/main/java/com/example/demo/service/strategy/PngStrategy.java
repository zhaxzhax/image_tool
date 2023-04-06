package com.example.demo.service.strategy;

import com.example.demo.service.ImageStrategy;
import com.example.demo.service.model.Response;
import com.example.demo.service.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngStrategy implements ImageStrategy {

    // region Singleton Pattern
    private static final PngStrategy INSTANCE = new PngStrategy();

    private PngStrategy() {
    }

    public static PngStrategy getInstance() {
        return INSTANCE;
    }
    // endregion

    public static final String TYPE = "png";

    @Override
    public boolean typeMatch(String type) {
        return TYPE.equals(type.toLowerCase());
    }


    @Override
    public Response validSourceFile(String sourcePath, String targetPath) {
        Response response = ImageStrategy.super.validSourceFile(sourcePath, targetPath);
        if (response.getCode() == Response.OK) {
            return Response.getWarning("PNG images may lose their transparency");
        }
        return response;
    }

    /**
     * PNG Type is special
     */
    @Override
    public Response convert(String sourcePath, String targetPath) {
        String targetType = ImageUtil.getFileType(targetPath);
        try {
            BufferedImage read = ImageIO.read(new File(sourcePath));
            BufferedImage result = new BufferedImage(
                    read.getWidth(),
                    read.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            result.createGraphics().drawImage(read, 0, 0, Color.WHITE, null);
            boolean convertResult = ImageIO.write(result, TYPE, new File(targetPath));
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
