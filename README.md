<h1 align="center">CropImageView</h1>

<p align="center">
  <a href="http://developer.android.com/index.html"><img src="https://img.shields.io/badge/platform-android-green.svg?style=flat-square"></a>
  <a href="https://android-arsenal.com/api?level=7"><img src="https://img.shields.io/badge/API-7%2B-brightgreen.svg?style=flat-square"></a>
  <a href="http://android-arsenal.com/details/1/1546"><img src="https://img.shields.io/badge/Android%20Arsenal-CropImageView-blue.svg?style=flat-square"></a>
  <a href="https://circleci.com/gh/cesards/CropImageView"><img src="https://img.shields.io/circleci/project/BrightFlair/PHP.Gt/master.svg?style=flat-square"></a>
  <a href="https://github.com/cesards/CropImageView/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square"></a>
</p>

An ImageView that supports different kind of cropping rather than the only Android is currently supporting: `centerCrop`

Using this library, you can crop your desired image by sides described below:

![Crop options](https://github.com/cesards/CropImageView/blob/master/art/cropping.png)

Development idea borns at the point in [Kerad Games] we needed images cropped by somewhere no matter the image size.

The original images we had to deal with (we wanted the half size of the ball shown whatever screen size), below

![Menu Left Side](https://github.com/cesards/CropImageView/blob/master/art/slide_menu_left.jpg)
![Menu Right Side](https://github.com/cesards/CropImageView/blob/master/art/slide_menu_right.jpg)

Here are some screenshots what we got thanks to this widget :-)

![Menu Post Left Side](https://github.com/cesards/CropImageView/blob/master/art/crop_menu_1.png)
![Menu Post Right Side](https://github.com/cesards/CropImageView/blob/master/art/crop_menu_2.png)
![Menu Centered](https://github.com/cesards/CropImageView/blob/master/art/crop_menu_3.png)

Usage
-----
### Step 1
##### Gradle
```groovy
dependencies {
   compile 'com.cesards.android:cropimageview:1.0.2.1'
}
```
##### Maven
```xml
<dependency>
   <groupId>com.cesards.android</groupId>
   <artifactId>CropImageView</artifactId>
   <version>1.0.2</version>
   <type>aar</type>
</dependency>
```
### Step 2
Define in xml:
```xml
<com.codeforvictory.android.superimageview.SuperImageView
   xmlns:custom="http://schemas.android.com/apk/res-auto"
   android:id="@+id/imageView1"
   android:src="@drawable/photo1"
   custom:crop="value" />
```
where `value` can take values
```xml
topLeft|centerLeft|bottomLeft|topRight|centerRight|bottomRight|centerTop|centerBottom
```
Or in code:
```java
final CropImageView cropImageView = new CropImageView(CropActivity.this);
final Resources res = getResources();
cropImageView.setImageDrawable(res.getDrawable(images[position]));
final CropImageView.CropType cropType = imageCrops[position];
cropImageView.setCropType(cropType);
```
Performance tests (using [Hugo](https://github.com/jakeWharton/hugo))
---------

### Center Crop Sample (ImageView)
```
V/TestForegroundImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1557)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1557)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1557)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = false
V/TestForegroundImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1557)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = false
V/TestForegroundImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1701)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1701)
V/TestForegroundImageView﹕ ⇠ setFrame [0ms] = true`
```

### Custom Crop Sample (CropImageView)
```
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1557)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1557)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1557)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = false
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1557)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = false
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=0, t=0, r=1080, b=1701)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = true
V/TestForegroundCropImageView﹕ ⇢ setFrame(l=1080, t=0, r=2160, b=1701)
V/TestForegroundCropImageView﹕ ⇠ setFrame [0ms] = true
```
Results: code used does not cause overhead in the UI thread.

ChangeLog
---------
* __1.0.2.1 (2015-10-22)__
   * ImageView crashes in devices with API < 18 due to a `@TargetApi` annotation
* __1.0.2 (2015-10-22)__
  * Changed `minSdkVersion` to 7
  * Solved a bug related with ImageView and API < 18.
* __1.0.1 (2015-02-17)__
  * Changed Samples UI and removed unused resources.
  * Added performance tests to Samples.
  * Added code styles for contributions.
  * Improved CropImageView widget. Removed `onLayout()` overhead. `ImageView` `onLayout()` is empty, so we only need logic in `setFrame()` method.
* __1.0.0 (2015-02-15)__
  * Initial release. (`minSdkVersion="14"`).

Developed By
------------
* César Díez Sánchez - <cesaryomismo@gmail.com>

<a href="https://twitter.com/menorking">
  <img alt="Follow me on Twitter" src="http://i.imgur.com/FMPxu5z.png" />
</a>
<a href="https://plus.google.com/115273462230054581675">
  <img alt="Follow me on Google Plus" src="http://i.imgur.com/KUyF398.png" />
</a>
<a href="https://medium.com/@cesards">
  <img alt="Follow me on Medium" src="http://i.imgur.com/oStZly8.png" />
</a>
<a href="https://www.pinterest.com/menorking/">
  <img alt="Follow me on Pinterest" src="http://i.imgur.com/ByWvWfX.png" />
</a>
<a href="https://dribbble.com/cesards">
  <img alt="Follow me on Dribbble" src="http://i.imgur.com/zWa8Fx3.png" />
</a>
<a href="http://www.linkedin.com/in/cesardiezsanchez">
  <img alt="Add me to Linkedin" src="http://i.imgur.com/GR52IsJ.png" />
</a>

Who's using it
--------------
* [Golden Manager](https://play.google.com/store/apps/details?id=com.keradgames.goldenmanager)
* [Slipstream Music](https://play.google.com/store/apps/details?id=com.cypher.slipstream&hl=en)
* [Vueling](https://play.google.com/store/apps/details?id=com.mo2o.vueling)
* Does your app use CropImageView? Let me know if you want to be featured in this list :-)

Do you want to contribute?
--------------------------
I'm pretty sure you there are some awesome hidden features you need in your daily dev life. Just let me know or collaborate to improve this librar

I'd like to improve this library with your help, there are some new features to implement waiting for you ;)

If you want to contribute, you should use [my code styles](https://github.com/cesards/CropImageView/blob/master/codestyles_DogmaLabs.xml) available in the root of the project!

<br>

<h2 align="center">License</h1>

__The MIT License (MIT)__

Copyright (c) 2016 César Díez Sánchez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
