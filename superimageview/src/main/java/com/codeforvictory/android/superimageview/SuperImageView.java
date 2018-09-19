package com.codeforvictory.android.superimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class SuperImageView extends AppCompatImageView {

  @Nullable Class<?> cropImpl;
  @Nullable private Crop crop;
  @Nullable private RoundedCorners roundedCorners;

  public SuperImageView(Context context) {
    super(context);
    setup(context, null);
  }

  public SuperImageView(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    setup(context, attrs);
  }

  public SuperImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    setup(context, attrs);
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public <T> T getFeature(final Class<T> implementation) {
    if (crop != null && crop.getClass() == implementation) {
      return ((T) crop);
    } else if (roundedCorners != null && roundedCorners.getClass() == implementation) {
      return ((T) roundedCorners);
    } else {
      throw new IllegalStateException("The feature requested has not been initialized yet. Make sure you do.");
    }
  }

  public <T> void setFeature(final Class<T> implementation) {
    // Create new instance of the feature.
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (changed && roundedCorners != null) {
      roundedCorners.onLayoutChanged();
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (roundedCorners != null) {
      roundedCorners.onDraw(canvas);
    }
  }

  @Override
  public void setScaleType(ScaleType scaleType) {
    if (crop != null) {
      crop.onScaleTypeChanged(scaleType);
    }
    super.setScaleType(scaleType);
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    final boolean changed = super.setFrame(l, t, r, b);
    if (changed && crop != null) {
      crop.onFrameChanged();
    }

    return changed;
  }

  private void setup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
    if (attributeSet == null) {
      return;
    }

    @SuppressLint("") final TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.siv_SuperImageView);
    setupCrop(a.getString(R.styleable.siv_SuperImageView_siv_cropImplementation), context, attributeSet);
    setupRoundedCorners(
            a.getString(R.styleable.siv_SuperImageView_siv_roundedCornersImplementation),
            context,
            attributeSet
    );
    a.recycle();
  }

  private void setupCrop(String cropImplementation, Context context, AttributeSet attributeSet) {
    if (TextUtils.isEmpty(cropImplementation)) {
      return;
    }
    cropImplementation = cropImplementation.trim();
    if (cropImplementation.isEmpty()) {
      return;
    }

    try {
      // Stupid layoutlib cannot handle simple class loaders.
//      ClassLoader classLoader = isInEditMode() ? getClass().getClassLoader() : context.getClassLoader();
//      Class<?> cropClass = classLoader.loadClass(cropImplementation);
//      Constructor<?> constructor = cropClass.getConstructor(CROP_CONSTRUCTOR_SIGNATURE);
//      constructor.setAccessible(true);
//      Object[] constructorArgs = new Object[]{this};
//      this.crop = (Crop) constructor.newInstance(constructorArgs);

//      Class<?> cl = Class.forName(cropImplementation);
      cropImpl = Class.forName(cropImplementation);
      Constructor<?> constructor = cropImpl.getConstructor(Crop.View.class);
      Object[] constructorArgs = new Object[]{ new Crop.View() {
        @Override
        public Drawable getDrawable() {
          return SuperImageView.super.getDrawable();
        }

        @Override
        public Matrix getImageMatrix() {
          return SuperImageView.super.getImageMatrix();
        }

        @Override
        public void setImageMatrix(Matrix matrix) {
          SuperImageView.super.setImageMatrix(matrix);
        }

        @Override
        public int getWidth() {
          return SuperImageView.super.getWidth();
        }

        @Override
        public int getHeight() {
          return SuperImageView.super.getHeight();
        }

        @Override
        public int getPaddingLeft() {
          return SuperImageView.super.getPaddingLeft();
        }

        @Override
        public int getPaddingRight() {
          return SuperImageView.super.getPaddingRight();
        }

        @Override
        public int getPaddingTop() {
          return SuperImageView.super.getPaddingTop();
        }

        @Override
        public int getPaddingBottom() {
          return SuperImageView.super.getPaddingBottom();
        }

        @NonNull
        @Override
        public Context getContext() {
          return SuperImageView.super.getContext();
        }

        @Override
        public boolean isInEditMode() {
          return SuperImageView.super.isInEditMode();
        }

        @Override
        public void setScaleType(ScaleType scaleType) {
          SuperImageView.super.setScaleType(scaleType);
        }

        @Override
        public void requestLayout() {
          SuperImageView.super.requestLayout();
        }

        @Override
        public void invalidate() {
          SuperImageView.super.invalidate();
        }
      } };
      crop = (Crop) constructor.newInstance(constructorArgs);
//      Object o = constructor.newInstance(constructorArgs);
      crop.setup(context, attributeSet);

//      Class<? extends Crop> cropClass = classLoader.loadClass(cropImplementation).asSubclass(Crop.class);
//      Constructor<? extends Crop> constructor = cropClass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
//      Object[] constructorArgs = new Object[]{context, attrs, defStyleAttr, defStyleRes};
//      Constructor<? extends Crop> constructor = cropClass.getConstructor();
    } catch (ClassNotFoundException e) {

    } catch (NoSuchMethodException e) {

    } catch (IllegalAccessException e) {

    } catch (InstantiationException e) {

    } catch (InvocationTargetException e) {

    }
//      this.crop = (Crop) Class.forName(cropImplementation).newInstance();
  }

  private void setupRoundedCorners(String roundedCornersImplementation, Context context, AttributeSet attributeSet) {
    if (TextUtils.isEmpty(roundedCornersImplementation)) {
      return;
    }
    roundedCornersImplementation = roundedCornersImplementation.trim();
    if (roundedCornersImplementation.isEmpty()) {
      return;
    }

    try {
      Class<?> cl = Class.forName(roundedCornersImplementation);
      Constructor<?> constructor = cl.getConstructor(RoundedCorners.View.class);
      Object[] constructorArgs = new Object[]{ new RoundedCorners.View() {
        @NonNull
        @Override
        public Context getContext() {
          return SuperImageView.super.getContext();
        }

        @Override
        public void setLayerType(int layerType, @Nullable Paint paint) {
          SuperImageView.super.setLayerType(layerType, paint);
        }

        @Override
        public void setWillNotDraw(boolean willNotDraw) {
          SuperImageView.super.setWillNotDraw(willNotDraw);
        }
      } };
      roundedCorners = (RoundedCorners) constructor.newInstance(constructorArgs);
      roundedCorners.setup(context, attributeSet);
    } catch (ClassNotFoundException e) {

    } catch (NoSuchMethodException e) {

    } catch (IllegalAccessException e) {

    } catch (InstantiationException e) {

    } catch (InvocationTargetException e) {

    }
  }








































  @Override
  public void setImageDrawable(Drawable drawable) {
    Log.d("", "");
    super.setImageDrawable(drawable);
//    roundedImage.requiresShapeUpdate();


//    if (drawable instanceof RoundedBitmapDrawable) {
//      super.setImageDrawable(drawable);
//      return;
//    }
//    RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) drawable).getBitmap());
//    mBitmapShader = new BitmapShader(dr.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

//    dr.setCornerRadius(10f);
//    super.setImageDrawable(dr);
//    this.requiersShapeUpdate = true;
//    super.setImageDrawable(drawable);
//    postInvalidate();
  }










  //  @Override
//  public void setImageBitmap(Bitmap bm) {
//    super.setImageDrawable(roundedImage.modifiedDrawable(bm));
//    // I think it would be better not to modify this method because we are breaking Liskov principle?
//    // so we could use Bitmap modifiedBitmap(Bitmap bitmap);
//    // as
//    // super.setImageBitmap(roundedImage.modifiedBitmap(bm));
    // bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//  }
//





}
