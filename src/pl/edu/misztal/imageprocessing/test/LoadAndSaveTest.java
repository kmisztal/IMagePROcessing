package pl.edu.misztal.imageprocessing.test;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.plugins.color.Invert;
import pl.edu.misztal.imageprocessing.plugins.color.Posterize;
import pl.edu.misztal.imageprocessing.plugins.color.Sepia;
import pl.edu.misztal.imageprocessing.plugins.convolve.Blur;
import pl.edu.misztal.imageprocessing.plugins.convolve.Emboss;
import pl.edu.misztal.imageprocessing.plugins.threshold.Threshold;

/**
 *
 * @author Krzysztof
 */
public class LoadAndSaveTest {

    public static void main(String[] args) {
        // load file
        String filename = "./res/tucano.jpg";
        Image image = new Image(filename);

        //load plugin
        Plugin plugin = new Sepia();
        plugin.process(image);

        //save results
        image.save("./res/out.png");
    }
}
