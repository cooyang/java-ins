package com.coo.ins.image;

import com.coo.ins.image.service.ImageService;
import com.coo.ins.image.service.RandomService;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Author: G.Yang
 * Date Time: 2019/5/25 23:30
 * Function:
 */
public class ImageMainTest {
    public static void main(String[] args) {
        saveImageToFile();
    }

    private static void saveImageToFile() {
        String code = new RandomService().randomCharCode(8);
        BufferedImage image = new ImageService().generateImage(code, 100, 40);
        File file = Paths.get("C:\\Users\\Administrator\\Desktop\\validateCode.jpg").toFile();
        try {
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            System.out.println("image save failed");
        }
    }
}
