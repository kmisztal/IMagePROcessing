package pl.edu.misztal.imageprocessing.image.executors.gui;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.gui.helpers.StepHandlerExecutorGUI;
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
    public void execute() {
        ProcessingProgress progress = new ProcessingProgress(null, plugins.size());

        plugins.stream().forEach((p) -> {
            p.process(currentImage);
            progress.increment();            
        });

    }

}
