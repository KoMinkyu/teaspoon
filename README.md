TeaSpoon
========

[![](https://jitpack.io/v/me.minkyu/teaspoon.svg)](https://jitpack.io/#com.github.KoMinkyu/teaspoon)
[![license](https://img.shields.io/hexpm/l/plug.svg)](LICENSE)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TeaSpoon-blue.svg?style=flat)](http://android-arsenal.com/details/1/2997)
[![Stories in Ready](https://badge.waffle.io/KoMinkyu/teaspoon.png?label=ready&title=Ready)](https://waffle.io/KoMinkyu/teaspoon)

[![travis](https://api.travis-ci.org/KoMinkyu/teaspoon.svg)](https://travis-ci.org/KoMinkyu/teaspoon)

Annotation-triggered method call by specified thread.
* Eliminate `runOnUiThread` calls by using `@OnUi` on method.
* Make background logic more clear by using `@OnBackground` on method.

It makes more clear and easy to execute your method on `ui thread` or `background thread`.

Usage
=====
You've seen this code at least once.

``` java
void someUiLogicInActivity() {
  this.runOnUiThread(new Runnable() {
    @Override public void run() {
      // UI Thread required works here.
    }
  });
}
```

Using TeaSpoon, you can implement `UI Thread` required functions like this.

```java
@OnUi void someUiLogic() {
  // UI Thread required works here.
}
```

or you can add delay option.

``` java
@OnUi(delay = 1000) void someUiLogicDelayed() {
  // UI Thread required works here.
}
```

even you can implement `Background Thread` required functions.

``` java
@OnBackground void someBackgroundLogic() {
  // Background Thread required works here.
}
```

and just call these methods wherever you want!

``` java
public class MainActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    someBackgroundLogic(); // it will be executed on Background Thread
    someUiLogic(); // it will be executed on Ui Thread
    someUiLogicDelayed(); // it will be executed on Ui Thread after specified delay
  }
}
```

before using this annotations, just add this single line of code in your Application class.

``` java
public class YourApplication extends Application {
  @Override public void onCreate() {
    TeaSpoon.initialize();
  }
}
```

Download
--------

on Gradle:
```groovy
buildscript {
  repositories {
    maven { url "https://jitpack.io" }
  }

  dependencies {
    classpath 'com.github.KoMinkyu.teaspoon:teaspoon-plugin:0.3.5'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'me.minkyu.teaspoon'
```

Local Development
-----------------

Working on this project? Here's some helpful Gradle tasks:

 * `install` - Install plugin, runtime, and annotations into local repo.
 * `cleanExample` - Clean the example project build.
 * `assembleExample` - Build the example project. Must run `install` first.
 * `installExample` - Build and install the example project debug APK onto a device.

License
-------

    Copyright 2015 Minkyu Ko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
