## Java内存分配
- 静态储存区：编译时就分配好，在程序整个运行期间都存在。它主要存放静态数据和常量；
- 栈区：局部变量，基本类型数据，对象的引用；
- 堆区：存放，new出来的对象，由 Java 垃圾回收器回收；
## 四种引用类型
- 强引用(StrongReference)：JVM 宁可抛出 OOM ，也不会让 GC 回收具有强引用的对象；
- 软引用(SoftReference)：只有在内存空间不足时，才会被回的对象；
- 弱引用(WeakReference)：在 GC 时，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存；
- 虚引用(PhantomReference)：任何时候都可以被GC回收，当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就
会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否存在该对象的虚引用，
来了解这个对象是否将要被回收。可以用来作为GC回收Object的标志。
## Android内存管理
- 为了整个Android系统的内存控制需要，Android系统为每一个应用程序都设置了一个硬性的Dalvik Heap Size最大限制阈值，
这个阈值在不同的设备上会因为RAM大小不同而各有差异。如果你的应用占用内存空间已经接近这个阈值，此时再尝试分配内存的话，
很容易引起OutOfMemoryError的错误；
- `ActivityManager.getMemoryClass()`可以用来查询当前应用的Heap Size阈值，这个方法会返回一个整数，表明你的应用
的Heap Size阈值是多少Mb(megabates)；
## Android内存泄露
  *我们常说的内存泄漏是指new出来的Object无法被GC回收，即为强引用。通俗来讲，就是对象已不再使用，但是依然持有对它的
  强引用*
  ### 检测工具[leakcanary](https://github.com/square/leakcanary)
  ```
  dependencies {
   debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5.1'
   releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.1'
   testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.1'
 }
 ```
 ### 常见内存泄露的情景
 1. Static Activities or Views；
 <p>
 在类中定义了静态Activity变量，把当前运行的Activity实例赋值于这个静态变量。如果这个静态变量在Activity生命周期结束
 后没有清空，就导致内存泄漏。因为static变量是贯穿这个应用的生命周期的，所以被泄漏的Activity就会一直存在于应用的进程
 中，不会被垃圾回收器回收。
 <p/>
 2. Inner Class；
 <p>
 因为非静态内部类持有外部类的隐式引用，容易导致意料之外的泄漏。然而内部类可以访问外部类的私有变量，只要我们注意引用的
 生命周期，就可以避免意外的发生。
 <p/>
 3. Anonymous Classes；
 <p>
 相似地，匿名类也维护了外部类的引用。所以内存泄漏很容易发生，当你在Activity中定义了匿名的AsyncTsk，当异步任务在后台
 执行耗时任务期间，Activity不幸被销毁了（译者注：用户退出，系统回收），这个被AsyncTask持有的Activity实例就不会被垃
 圾回收器回收，直到异步任务结束。
 <p/>
 4. 单例模式；
 <p>
 单例的静态特性导致其生命周期同应用一样长。
 <p/>
 5. Handler；
 <p>
 当Handler中有延迟的的任务或是等待执行的任务队列过长，由于消息持有对Handler的引用，而Handler又持有对其外部类的潜在
 引用，这条引用关系会一直保持到消息得到处理，而导致了Activity无法被垃圾回收器回收，而导致了内存泄露。
 <p/>
 6. 集合中对象没清理造成的内存泄漏；
 <p>
 我们通常把一些对象的引用加入到了集合容器（比如ArrayList）中，当我们不需要该对象时，并没有把它的引用从集合中清理掉，
 这样这个集合就会越来越大。如果这个集合是static的话，那情况就更严重了。所以要在退出程序之前，将集合里的东西clear，
 然后置为null，再退出程序。
 <p/>
 7. WebView；
 <p>
 当我们不要使用WebView对象时，应该调用它的destory()函数来销毁它，并释放其占用的内存，否则其占用的内存长期也不能被
 回收，从而造成内存泄露。
 <p/>
 
 ### 解决方案
 **Talking is cheap！Reading the code!**
 
 
 
 
 
