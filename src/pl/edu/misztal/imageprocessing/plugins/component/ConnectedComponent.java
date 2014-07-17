package pl.edu.misztal.imageprocessing.plugins.component;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.ImageFactory;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public class ConnectedComponent extends Plugin{
    
    private int[][] res;

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        res = compactLabeling(ImageFactory.toIntArray(imgIn), imgIn.getWidth(), imgIn.getHeight(), true);
        final int scale = getMaxLabel() + 1;
        ImageFactory.fromIntArray(res, scale, imgOut);
        setAttribute("result", res);
        setAttribute("nr", getMaxLabel());
    }

    final int MAX_LABELS = 80000;
    int next_label = 1;

    /**
     * label and re-arrange the labels to make the numbers of label continous
     *
     * @param zeroAsBg Leaving label 0 untouched
     */
    public int[][] compactLabeling(int[][] image, int w, int h, boolean zeroAsBg) {
        //label first
        int[][] label = labeling(image, w, h, zeroAsBg);
        int[] stat = new int[next_label + 1];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (label[i][j] > next_label) {
                    System.err.println("bigger label than next_label found!");
                }
                stat[label[i][j]]++;
            }
        }

        stat[0] = 0;              // label 0 will be mapped to 0
        // whether 0 is background or not
        int k = 1;
        for (int i = 1; i < stat.length; i++) {
            if (stat[i] != 0) {
                stat[i] = k++;
            }
        }

//        System.out.println("From " + next_label + " to " + (k - 1) + " regions");
        next_label = k - 1;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                label[i][j] = stat[label[i][j]];
            }
        }
        return label;
    }

    /**
     * return the max label in the labeling process. the range of labels is
     * [0..max_label]
     */
    public int getMaxLabel() {
        return next_label;
    }

    /**
     * Label the connect components If label 0 is background, then label 0 is
     * untouched; If not, label 0 may be reassigned [Requires] 0 is treated as
     * background
     *
     * @param image data
     * @param d dimension of the data
     * @param zeroAsBg label 0 is treated as background, so be ignored
     */
    public int[][] labeling(int[][] image, int w, int h, boolean zeroAsBg) {
        int[][] rst = new int[w][h];
        int[] parent = new int[MAX_LABELS];
        int[] labels = new int[MAX_LABELS];
        // region label starts from 1;
        // this is required as union-find data structure
        int next_region = 1;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (image[x][y] == 0 && zeroAsBg) {
                    continue;
                }
                int k = 0;
                boolean connected = false;
                // if connected to the left
                if (x > 0 && image[x - 1][y] == image[x][y]) {
                    k = rst[x - 1][y];
                    connected = true;
                }
                // if connected to the up
                if (y > 0 && image[x][y - 1] == image[x][y]
                        && (connected = false || image[x][y - 1] < k)) {
                    k = rst[x][y - 1];
                    connected = true;
                }
                if (!connected) {
                    k = next_region;
                    next_region++;
                }

                if (k >= MAX_LABELS) {
                    System.err.println("maximum number of labels reached. "
                            + "increase MAX_LABELS and recompile.");
                    System.exit(1);
                }
                rst[x][y] = k;
                // if connected, but with different label, then do union
                if (x > 0 && image[x - 1][y] == image[x][y] && rst[x - 1][y] != k) {
                    uf_union(k, rst[x - 1][y], parent);
                }
                if (y > 0 && image[x][y - 1] == image[x][y] && rst[x][y - 1] != k) {
                    uf_union(k, rst[x][y - 1], parent);
                }
            }
        }

        // Begin the second pass.  Assign the new labels
        // if 0 is reserved for background, then the first available label is 1
        next_label = 1;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (image[i][j] != 0 || !zeroAsBg) {
                    rst[i][j] = uf_find(rst[i][j], parent, labels);
                    // The labels are from 1, if label 0 should be considered, then
                    // all the label should minus 1
                    if (!zeroAsBg) {
                        rst[i][j]--;
                    }
                }
            }
        }
        next_label--;   // next_label records the max label
        if (!zeroAsBg) {
            next_label--;
        }

//        System.out.println(next_label + " regions");

        return rst;
    }

    void uf_union(int x, int y, int[] parent) {
        while (parent[x] > 0) {
            x = parent[x];
        }
        while (parent[y] > 0) {
            y = parent[y];
        }
        if (x != y) {
            if (x < y) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }

    /**
     * This function is called to return the root label Returned label starts
     * from 1 because label array is inited to 0 as first [Effects] label array
     * records the new label for every root
     */
    int uf_find(int x, int[] parent, int[] label) {
        while (parent[x] > 0) {
            x = parent[x];
        }
        if (label[x] == 0) {
            label[x] = next_label++;
        }
        return label[x];
    }
    
}
