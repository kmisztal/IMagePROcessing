package pl.edu.misztal.imageprocessing.image.plugins;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public abstract class Plugin {
    
    protected Attributes attributes = new Attributes();

    /**
     * Executes the algorithm.
     *
     * @param imgIn	input image.
     * @param imgOut	output image.
     * @param attrOut	output attributes.
     * @param mask	mask containing what pixels should be considered.
     */
    public abstract void process(
            Image imgIn,
            Image imgOut,
            Attributes attrOut,
            Mask mask
    );

    /**
     * Executes the algorithm.
     *
     * @param imgIn	input image.
     * @param imgOut	output image.
     * @param mask
     */
    public final void process(
            Image imgIn,
            Image imgOut,
            Mask mask
    ) {
        process(imgIn, imgOut, null, mask);
    }

    /**
     * Executes the algorithm.
     *
     * @param imgIn	input image.
     * @param imgOut	output image.
     * @param attrOut
     */
    public final void process(
            Image imgIn,
            Image imgOut,
            Attributes attrOut
    ) {
        process(imgIn, imgOut, attrOut, null);
    }

    /**
     * Executes the algorithm.
     *
     * @param imgIn	input image.
     * @param imgOut	output image.
     */
    public final void process(
            Image imgIn,
            Image imgOut
    ) {
        process(imgIn, imgOut, null, null);
    }

    /**
     * Executes the algorithm.
     *
     * @param imgInAndOut	input and output image.
     */
    public final void process(
            Image imgInAndOut
    ) {
        process(imgInAndOut, imgInAndOut, null, null);
    }

    
    public Object getAttribute(String key){
        return attributes.get(key);
    }
    
    public void setAttribute(String key, Object value){
        attributes.set(key, value);
    } 
}
