package com.codeforvictory.superimageview.roundedcorners;

import android.graphics.Path;

final class ClipPathManager {

    private final Path path = new Path();
    private ClipPathCreator createClipPath = null;

    void setClipPathCreator(ClipPathCreator createClipPath) {
        this.createClipPath = createClipPath;
    }

    Path createMask() {
        return path;
    }

    void setupClipLayout(int width, int height) {
        path.reset();

        if (createClipPath != null) {
            path.set(createClipPath.createClipPath(width, height));
        }
    }

    public interface ClipPathCreator {
        Path createClipPath(int width, int height);
    }
}
