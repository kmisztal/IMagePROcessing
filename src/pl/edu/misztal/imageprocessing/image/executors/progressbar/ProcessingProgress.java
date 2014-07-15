package pl.edu.misztal.imageprocessing.image.executors.progressbar;

import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.ProgressMonitor;
import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.gui.StepHandlerExecutor;

/**
 *
 * @author Krzysztof
 */
public class ProcessingProgress {
    private final ProgressMonitor monitor;
    private int progress;
    private final int stepNumber;

    public ProcessingProgress(Component parent, int stepsNumber) throws HeadlessException {
        this.monitor = new ProgressMonitor(parent, "Image Processing", "Getting Started...", 0, stepsNumber);
        this.monitor.setMillisToDecideToPopup(0);
        this.monitor.setMillisToPopup(0);
        this.progress = 0;
        this.stepNumber = stepsNumber;
    }

    public void increment() {
        this.progress += 1;
        this.monitor.setProgress(progress);
        this.monitor.setNote("Step " + progress + " / " + stepNumber + " completed");
    }
}
