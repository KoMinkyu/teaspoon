TeaSpoon
========
[![jitpack](https://img.shields.io/badge/jitpack%20maven-0.2.1-blue.svg)](https://img.shields.io/badge/jitpack%20maven-0.2.1-blue.svg)
**This library is non-productive!**

Annotation-triggered method call by specified thread.
* Eliminate `runOnUiThread` calls by using `@OnUi` on method.
* Make background logic more clear by using `@OnBackground` on method.

It makes more clear and easy to execute your method on `ui thread` or `background thread`.

``` java
class BackgroundCallback extends Callback {
	@Override public void success(Object object, Response response) {
		showSuccessView();
	}

	@Override public void failure(RetrofitError error) {
		showFailureView();
		saveLogOnDevice();
	}
}

@OnUi public void showSuccessView() {
	successView.setVisibility(View.VISIBLE);
}

@OnUi public void showFailureView() {
	failureView.setVisibility(View.VISIBLE);
}

@OnBackground public void saveLogOnDevice() {
	// File IO or some background thread required work
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
    classpath 'com.github.KoMinkyu.TeaSpoon:teaspoon-plugin:0.2.1'
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
