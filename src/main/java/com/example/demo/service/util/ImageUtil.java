package com.example.demo.service.util;

public class ImageUtil {

    public static String getFileType(String filePath) {
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        return switch (fileType) {
            case "jpeg" -> "jpg";
            case "tiff" -> "tif";
            case "dib" -> "bmp";
            default -> fileType;
        };
    }
}
