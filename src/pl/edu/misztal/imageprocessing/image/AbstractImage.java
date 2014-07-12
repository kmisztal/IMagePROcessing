/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.misztal.imageprocessing.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public abstract class AbstractImage {

    // Dimension
    protected int width;
    protected int height;

    protected String descrition = "";

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getDescrition() {
        return descrition;
    }

    public abstract AbstractImage copy();

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    protected boolean checkX(final int x) {
        return (x >= 0 && x <= width);
    }

    protected boolean checkY(final int y) {
        return (y >= 0 && y <= height);
    }

    
}
