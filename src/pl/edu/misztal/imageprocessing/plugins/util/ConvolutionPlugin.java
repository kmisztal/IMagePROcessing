package pl.edu.misztal.imageprocessing.plugins.util;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public abstract class ConvolutionPlugin extends Plugin {

    protected int width;
    protected int height;
    protected float[] kernel;

    protected abstract void createKernel();

    protected abstract void setKernelSize();

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask != null) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        setKernelSize();
        
        createKernel();

        final BufferedImageOp bio
                = new ConvolveOp(new Kernel(width, height, kernel));
        try{
            bio.filter(imgIn.getBufferedImage(), imgOut.getBufferedImage());
        }catch(java.lang.IllegalArgumentException ex){
            BufferedImage im = imgIn.getCopyOfBufferedImage();
            bio.filter(imgIn.getBufferedImage(), im);
            imgIn.setBufferedImage(im);
        }
    }

}
