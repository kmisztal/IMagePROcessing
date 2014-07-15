package pl.edu.misztal.imageprocessing.image.noise;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;


/**
 *
 * @author Krzysztof
 */
public class SaltAndPepper extends Plugin {

    private double density;
    
    int check(int v){
        if(v > 255)
            return 255;
        if(v < 0)
            return 0;
        return v;
    }

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask != null) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        if(getAttribute("density") != null){
            density = (Double) getAttribute("density");
        }else{
            density = 0.2;
            setAttribute("density", density);
        }
        
        int r;
        for (int y = 0; y < imgIn.getHeight(); y++) {
            for (int x = 0; x < imgIn.getWidth(); x++) {
                if(Math.random() < density){
                    if(Math.random() < 0.5){
                        r = 255;
                    }else{
                        r = 0;
                    }
                    imgOut.setRGB(x, y, r, r, r);
                }
            }
        }
    }
}