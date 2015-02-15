CropImageView
=============

An ImageView that supports different kind of cropping rather than the only Android is currently supporting: `centerCrop`

Using this library, you can crop your desired image by sides described below:

![Crop options](https://raw.github.com/makeramen/RoundedImageView/master/art/cropping.png)

Development idea borns at the point in [Kerad Games] we needed images cropped by somewhere no matter the image size.

Here are some screenshots what we got thanks to this widget :-)

![Menu Left Side](https://raw.github.com/makeramen/RoundedImageView/master/art/crop_menu_1.png)
![Menu Right Side](https://raw.github.com/makeramen/RoundedImageView/master/art/crop_menu_2.png)
![Menu Centered](https://raw.github.com/makeramen/RoundedImageView/master/art/crop_menu_3.png)

Usage
-----
## Step 1
#### Gradle
```groovy
dependencies {
   compile ''
}
```
#### Maven
```xml
<dependency>
    <groupId></groupId>
    <artifactId></artifactId>
    <version></version>
    <type></type>
</dependency>
```
## Step 2
Define in xml:
```xml
<com.cesards.cropimageview
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:id="@+id/imageView1"
        android:src="@drawable/photo1"
        custom:crop="value" />
```
where `value` can be `topLeft|centerLeft|bottomLeft|topRight|centerRight|bottomRight|centerTop|centerBottom`
Or in code:
```java
CropImageView cropImageView = new CropImageView(CropActivity.this);
final Resources res = getResources();
cropImageView.setImageDrawable(res.getDrawable(images[position]));
final CropImageView.CropType cropType = imageCrops[position];
cropImageView.setCropType(cropType);
```

ChangeLog
---------

* **1.4.0 (2015-02-15) **
  * Initial release. (```minSdkVersion="14"```)


License
---------

   Copyright 2015 César Díez Sánchez

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.