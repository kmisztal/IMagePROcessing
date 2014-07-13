package pl.edu.misztal.imageprocessing.image.executors;

import java.util.ArrayList;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;

/**
 *
 * @author Krzysztof
 */
public abstract class Executor {

    protected final Image originalImage;
    protected Image currentImage;

    ArrayList<Plugin> plugins = new ArrayList<>();

    public Executor(String filename) {
        originalImage = new Image(filename);
        currentImage = originalImage.copy();
    }

    public void add(Plugin plugin) {
        plugins.add(plugin);
    }

    public void add(Plugin plugin, Object... params) {
        if (params.length % 2 != 0) {
            throw new RuntimeException("Wrong plugin initialization");
        }
        for (int i = 0; i < params.length;) {
            plugin.setAttribute((String) params[i++], params[i++]);
        }
        plugins.add(plugin);
    }

    public abstract void execute();

    public void save(String filename) {
        currentImage.save(filename);
    }

    public Image getOriginalImage() {
        return originalImage;
    }

    public Image getResultImage() {
        return currentImage;
    }

}
