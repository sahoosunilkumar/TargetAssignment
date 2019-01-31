# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\NarendraKumar\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5
-dontpreverify

-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver


-keep class com.google.android.gms.** { *; }
-keep class android.support.** { *; }
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#Gson
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-dontwarn com.google.android.gms.**
-dontwarn rx.**
-dontwarn android.support.**


# If in your rest service interface you use methods with Callback argument.
-keepattributes Exceptions
# If your rest service methods throw custom exceptions, because you've defined an ErrorHandler.
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod


#Jackson
-keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
-keep class org.codehaus.** { *; }
-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
  public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *;
}


## Retrofit 2
-keep class retrofit2.** { *; }
-dontwarn rx.**
-dontwarn retrofit2.**
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn retrofit.**

# GSON
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-dontwarn com.google.gson.**
-dontwarn retrofit2.Platform$Java8

#RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

 -ignorewarnings