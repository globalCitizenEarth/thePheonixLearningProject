#include <jni.h>
#include <iostream>
#include <stdio.h>
#include "App.h"

// Implementation of the C-style function (wrapped in extern "C" for the linker)
extern "C" {
    JNIEXPORT void JNICALL Java_App_callCFunction(JNIEnv* env, jobject obj) {
        printf("[Native C] Hello from the C-side of the bridge!\n");
    }
}

// Implementation of the C++-style function
JNIEXPORT void JNICALL Java_App_callCppFunction(JNIEnv* env, jobject obj) {
    std::cout << "[Native C++] Hello from the C++-side! Using std::cout." << std::endl;
}
