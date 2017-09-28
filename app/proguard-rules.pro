
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-dontwarn uk.**
-keep class retrofit.** { *; }
-keep class com.exampleapp.model.** { *; }
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}