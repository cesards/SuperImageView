package com.codeforvictory.superimageview.roundedcorners;

import android.graphics.Path;

import androidx.annotation.Nullable;

final class ClipPathManager {

    private final Path path = new Path();
    private ClipPathCreator createClipPath = null;

    @Nullable
    private Path createClipPath(int width, int height) {
        if (createClipPath != null) {
            return createClipPath.createClipPath(width, height);
        }
        return null;
    }

    void setClipPathCreator(ClipPathCreator createClipPath) {
        this.createClipPath = createClipPath;
    }

    Path createMask(int width, int height) {
        return path;
    }

    void setupClipLayout(int width, int height) {
        path.reset();
        final Path clipPath = createClipPath(width, height);
        if (clipPath != null) {
            path.set(clipPath);
        }
    }

    public interface ClipPathCreator {
        Path createClipPath(int width, int height);
    }
}
