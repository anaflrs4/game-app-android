# Add project specific ProGuard rules here.
# You can find more details about ProGuard in the official documentation:
# https://www.guardsquare.com/en/products/proguard/manual/usage

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# If you use retrofit, you may need this to avoid crashes with R8
-dontwarn retrofit2.Platform$Java8
