package pl.edu.misztal.imageprocessing.test;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.gui.StepHandlerExecutor;
import pl.edu.misztal.imageprocessing.image.executors.gui.StepHandlerExecutorWithProgressBar;
import pl.edu.misztal.imageprocessing.plugins.color.Invert;
import pl.edu.misztal.imageprocessing.image.noise.SaltAndPepper;
import pl.edu.misztal.imageprocessing.plugins.convolve.Blur;
import pl.edu.misztal.imageprocessing.plugins.statistical.Median;
import pl.edu.misztal.imageprocessing.plugins.statistical.MedianOnThreads;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutorWithProgressBarTest {
     public static void main(String[] args) {
        String filename = "./res/senna.jpg";
        
        Executor exec = new StepHandlerExecutor(filename);
        
        exec.add(new Invert());
        exec.add(new Invert());
        
        exec.add(new Blur(),
                "size", 6);
        
        exec.add(new SaltAndPepper(), 
                "density", 0.2);
//        exec.add(new Median(),
//                "size", 21);
        exec.add(new MedianOnThreads(),
                "size", 21);
        
        exec.execute();
        
        exec.save("./res/out.png");
    }
}

