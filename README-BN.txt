SS APK BUILDER v2.0 — THREE-LANGUAGE BUILD STARTER
==================================================

এই সংস্করণে একটি Android APK-র মধ্যে তিন ধরনের code support যুক্ত করা হয়েছে:

1) HTML + JavaScript
   Path: app/src/main/assets/index.html
   Android WebView-তে HTML/JS চালায়। JS preview builder-এর মধ্যেও কাজ করে।
   Browser/Node.js-এর সব API পাওয়া যাবে না; এটি WebView JavaScript।

2) Python 3.13
   Path: app/src/main/python/runner.py
   Chaquopy 17.2.0 দিয়ে Android অ্যাপের ভেতর Python runtime যুক্ত।
   Builder APK-এর Python tab-এ code লিখে Run চাপলে Android bridge দিয়ে Python চলে।
   Python-এর কিছু module Android-এ সমর্থিত নয়; tkinter/turtle/curses ইত্যাদি নেই।

3) Native Kotlin + Java
   Kotlin: app/src/main/java/com/ss/apkbuilder/NativeDemo.kt
   Java:   app/src/main/java/com/ss/apkbuilder/JavaDemo.java
   এগুলো Gradle দিয়ে Android APK-র মধ্যে native source হিসেবে compile হয়।
   Builder app-এর Native tab source file download করে। GitHub repo-তে একই path-এ
   ফাইলটি replace করে workflow চালালে পরিবর্তন APK-তে compile হবে। Package, class/object,
   method signature না বদলালে sample bridge থেকে output দেখা যাবে।

BUILD STEPS (PHONE + GITHUB)
----------------------------
1. ZIP extract করো।
2. GitHub-এ নতুন repository তৈরি করো, যেমন SS-APK-BUILDER।
3. ZIP-এর ভেতরের ফাইল/ফোল্ডারগুলো repository root-এ upload করো। ZIP-টিকে এক ফাইল হিসেবে
   upload করবে না; extract করা ফাইলগুলো upload করবে।
4. Commit করো। Actions tab-এ Build SS APK workflow চলবে। প্রয়োজনে workflow_dispatch দিয়ে
   Run workflow চাপো।
5. সফল হলে workflow run খুলে Artifacts থেকে SS-APK-Debug ডাউনলোড করো।
6. Builder APK চালিয়ে code edit করো। Download source দিয়ে ফাইল নামাও।
7. সেই ফাইল GitHub-এ README-তে দেখানো exact path-এ replace ও commit করো। তারপর workflow চালাও।

TECHNICAL NOTES
---------------
- AGP 8.6.1 + Gradle 8.7 + JDK 17.
- Chaquopy 17.2.0 + Python 3.13; minSdk 24; ABI arm64-v8a and x86_64.
- Chaquopy docs: https://chaquo.com/chaquopy/doc/current/android.html
- Chaquopy compatibility: https://chaquo.com/chaquopy/doc/current/versions.html
- This is a starter project; it has not been built on the user's phone in this session.
- Python packages with native compiled extensions may need an Android-compatible wheel.
- Native Android projects outside this starter must be Gradle-compatible and contain their
  own required dependencies/resources/manifest. Pasting an arbitrary Kotlin or Java snippet
  alone cannot automatically create a complete app.
- The workflow builds a Debug APK. Release signing is not configured.
