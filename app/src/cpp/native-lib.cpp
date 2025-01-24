#include <jni.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_weatherweather_core_utils_Keys_OpenWeatherApiKey(JNIEnv *env, jobject thiz) {
    return env ->NewStringUTF("OPEN_WEATHER_API_KEY");
}