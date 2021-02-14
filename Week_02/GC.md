**GCLogAnalysis.java演练案例总结如下：**

**使用串行GC：（SerialGC）**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m基准，内存越大，生成对象次数越小，gc频率越低。

​		不设置Xms（初始化内存大小），以-Xmx512m基准，内存越大，生成对象次数越小，gc频率基本都是一致的。

**使用并行GC：（ParallelGC）**

​		设置Xms（初始化内存大小），以Xms512m -Xmx1g基准，内存越大，生成对象次数越小，gc频率越低。

​		不设置Xms（初始化内存大小），以-Xmx1g基准，内存越大，生成对象次数越小，gc频率也是逐步变小。

**使用CMSGC：（ConcMarkSweepGC）**

​		设置Xms（初始化内存大小），以Xms512m -Xmx1g基准，内存越大，生成对象次数越小，gc频率基本都是一致的。

​		不设置Xms（初始化内存大小），以-Xmx1g基准，内存越大，生成对象次数越小，gc频率基本都是一致的。

**使用G1GC：（G1GC）**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m->4g基准，内存越大，生成对象次数越大，gc频率基本都是一致的；设置Xmx8g，生成对象变少，gc频率变小。

​		不设置Xms（初始化内存大小），以-Xmx512m->4g基准，内存越大，生成对象次数越大，gc频率基本都是一致的;设置Xmx8g,生生对象变少，gc频率也变得很小。



**演练 gateway-server-0.0.1-SNAPSHOT.jar 示例**

演示代码：wrk -c40 -t4 -d10s http://localhost:8088/api/hello

**使用SerialGC:**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m基准，内存越大，qps越小。

​		不设置Xms（初始化内存大小），以-Xmx512m基准，内存越大，qps越小。

**使用ParallelGC:**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m基准，内存越大，qps越小。

​		不设置Xms（初始化内存大小），以-Xmx512m基准，内存越大，qps越小。

**使用CMSGC:**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m基准，内存越大，qps越小。

​		不设置Xms（初始化内存大小），以-Xmx512m基准，内存越大，qps越小。

**使用G1GC:**

​		设置Xms（初始化内存大小），以Xms512m -Xmx512m基准，内存越大，qps越大。

​		不设置Xms（初始化内存大小），以-Xmx512m基准，内存越大，qps越大。



总体来说：SerialGC -> parallelGC -> CMSGC ->G1GC