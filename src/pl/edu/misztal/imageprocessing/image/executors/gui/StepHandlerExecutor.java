package pl.edu.misztal.imageprocessing.image.executors.gui;

import pl.edu.misztal.imageprocessing.image.executors.gui.helpers.StepHandlerExecutorGUI;
import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.progressbar.ProcessingProgress;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutor extends Executor {

    public StepHandlerExecutor(String filename) {
        super(filename);
    }

    @Override
    public void execute() {
        StepHandlerExecutorGUI imageList = new StepHandlerExecutorGUI();
        ProcessingProgress progress = new ProcessingProgress(imageList, plugins.size());
        imageList.setVisible(true);

        plugins.stream().forEach((p) -> {
            p.process(currentImage);
            imageList.addImage(currentImage.copy(), p);
            progress.increment();            
        });

    }

}
