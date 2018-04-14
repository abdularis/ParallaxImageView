# Android Parallax Image View
[![](https://jitpack.io/v/abdularis/ParallaxImageView.svg)](https://jitpack.io/#abdularis/ParallaxImageView)

Creates effect such as vertical parallax, horizontal parallax etc. on android ImageView when it's being vertically or horizontally scrolled (moving) on the screen.

## Screenshot
![](screenshots/screenshot_1.gif)

## Setup
- **Step 1** Add repository into root build.gradle

~~~xml
allprojects {
    repositories {
    ...
    maven {
        url 'https://jitpack.io' }
    }
}
~~~

- **Step 2** Add library dependency into app build.gradle

~~~xml
dependencies {
    compile 'com.github.abdularis:ParallaxImageView:1.0'
}
~~~


## Usage
- Create vertical parallax image view
~~~xml
<com.github.abdularis.piv.VerticalScrollParallaxImageView
    android:id="@+id/image_view"
    android:layout_width="200dp"
    android:layout_height="170dp"
    android:src="@drawable/img1"/>
~~~

- Create Horizontal parallax image view
~~~xml
<com.github.abdularis.piv.HorizontalScrollParallaxImageView
    android:id="@+id/image_view"
    android:layout_width="200dp"
    android:layout_height="170dp"
    android:src="@drawable/img1"/>
~~~

- Create and customize effect on your own
~~~xml
<com.github.abdularis.piv.ScrollTransformImageView
    android:id="@+id/image_view"
    android:layout_width="200dp"
    android:layout_height="170dp"
    android:src="@drawable/img1"/>
~~~

In the java/kotlin code you can set the effect (transformer) manually. There are three built-in effect classes, VerticalParallaxTransformer, HorizontalParallaxTransformer, HorizontalScaleTransformer.

Java code
~~~java
ScrollTransformImageView img = findViewById(R.id.image_view);

// create horizontal scale effect
img.setViewTransformer(new HorizontalScaleTransformer())

// create vertical or horizontal parallax effect manually
// img.setViewTransformer(new VerticalParallaxTransformer())
// img.setViewTransformer(new HorizontalParallaxTransformer())
//
// the VerticalParallaxImageView or HorizontalParallaxImageView are nothing but the ScrollTransformImageView with coresponding parallax effect
~~~

You can create your own custom effect by extending ViewTransformer.
~~~java
public class CustomTransformer extends ViewTransformer {
    @Override
    public void onAttached(@NotNull ScrollTransformImageView view) {
        // do something when this transformer is set into image view
    }

    @Override
    public void onDetached(@NotNull ScrollTransformImageView view) {
        // do something when this transformer is remove from image view
    }

    @Override
    public void apply(@NotNull ScrollTransformImageView view, @NotNull Canvas canvas, int viewX, int viewY) {
        // do transformation effect or so, this would be called everytime image view move/scrolled
    }
}
~~~


