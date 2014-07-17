package pl.edu.misztal.imageprocessing.image.executors.gui;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.progressbar.ProcessingProgress;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutorWithProgressBar extends Executor {

    public StepHandlerExecutorWithProgressBar(String filename) {
        super(filename);
    }

    @Override
    public void executeCase() {
        ProcessingProgress progress = new ProcessingProgress(null, getPlugins().size());

        getPlugins().stream().forEach((p) -> {
            p.process(currentImage);
            progress.increment();            
        });
    }

}
