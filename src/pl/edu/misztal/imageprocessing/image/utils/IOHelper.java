/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.misztal.imageprocessing.image.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class IOHelper {

    public static BufferedImage load(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException ex) {
            throw new RuntimeException("Wrong local file name");
        }
    }

    public static void save(String filePath, BufferedImage image) {
        File l_file = new File(filePath);
        try {
            if (filePath.toUpperCase().endsWith(".JPEG") || filePath.toUpperCase().endsWith(".JPG")) {
                ImageIO.write(image, "JPEG", l_file);
            } else if (filePath.toUpperCase().endsWith(".PNG")) {
                ImageIO.write(image, "PNG", l_file);
            } else {
                ImageIO.write(image, filePath.substring(filePath.lastIndexOf('.') + 1), l_file);
            }
        } catch (Exception e) {
        }
    }
}
