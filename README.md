# Lab Report 6: OpenGL ES, Android NDK, and JNI
Use native code to compute square value of a number and display the result on view

[Repository](https://github.com/hiradevina/learn-tktpl-1706979221/tree/lab-jni) 

## Configuration
1. On `build.gradle` app, add the `ndkVersion` used
2. Declare the CMake configuration file path on `externalNativeBuild`

## Create Native Code
1. Create native code in `cpp/hello-jni.c` that compute the square value of  `num` 
2. Create `cpp/CMakeLists.txt` configuration file, CMake will manage the compilation of the native code based on the config file. Declare the .c file that needs to be compiled and libraries needed.
3. Create external function in `MainActivity.kt`
```
external fun intFromJNI(num: Int): Int
```
4. Load the native library on `MainActivity` class initiation 
5. Call the native function `intFromJni` and place the result on TextView