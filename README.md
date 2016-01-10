TeaSpoon
========

[![jitpack](https://img.shields.io/badge/jitpack%20maven-v0.3.3-blue.svg)](https://img.shields.io/badge/jitpack%20maven-0.3.3-blue.svg)
[![license](https://img.shields.io/hexpm/l/plug.svg)](LICENSE)

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

before using this annotations, just add this single line of code in your Application class.

``` java
public class YourApplication exstends Application {
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
    classpath 'me.minkyu.TeaSpoon:teaspoon-plugin:0.3.3'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'teaspoon'
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
