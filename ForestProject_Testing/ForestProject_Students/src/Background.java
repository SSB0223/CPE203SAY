import processing.core.PImage;

import java.util.List;

/**
 * Represents a background for the 2D world.
 */
final class Background
{
    private String id;
    private  List<PImage> images;

    private int imageIndex;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }

    public PImage getCurrentImage() {
        return images.get((this).imageIndex);
    }
}
