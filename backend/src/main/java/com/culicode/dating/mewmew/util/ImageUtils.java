package com.culicode.dating.mewmew.util;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageUtils {
    public static boolean writeImage(String path, String imgBase64) throws IOException {
        mkdirs(path);
        System.out.println("write image");
        System.out.println(imgBase64);
        // create a buffered image
        BufferedImage image = null;
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();

        imageByte = decoder.decodeBuffer(imgBase64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();


// write the image to a file
        File outputfile = new File(path);
        ImageIO.write(image, "png", outputfile);
        return true;
    }

    private static void mkdirs(String path) {
        String[] parts = path.split("/");
        parts = Arrays.copyOfRange(parts, 0, parts.length - 1);
        path = String.join("/", parts);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
    }
}
