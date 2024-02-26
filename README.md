# java-method-invoke-benchmark-test

A simple benchmark of java method invocation in various ways.

# Method invocation ways

We have 4 ways to invoke a method in java:

1. Directly invoke the method.
2. Use reflection to invoke the method.
3. Use MethodHandle to invoke the method.
4. Use bound MethodHandle to invoke the method.(By MethodHandle::bindTo)

# Environment
- OS: Windows 11
- CPU: 13th Gen Intel(R) Core(TM) i9-13900H   2.60 GHz
- RAM: 32GB
- JDK: java -version
  ````
  java version "1.8.0_391"
  Java(TM) SE Runtime Environment (build 1.8.0_391-b13)
  Java HotSpot(TM) 64-Bit Server VM (build 25.391-b13, mixed mode)
  ````

# Benchmark result

```
Warming up...
==================================================
Test results of 100*100=10000 rounds:
Direct:                                 453100ns
Bound MethodHandle:                     1253400ns
MethodHandle:                           1089900ns
Reflect:                                4138200ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         800300
Handle - Direct:                        636800
Reflect - Direct:                       3685100

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.20429838810446083
Reflect - Bound / (Bound - Direct):     3.604648256903661

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   4.786903266331659
==================================================
Warm up finished.

==================================================
Test results of 10*10000=100000 rounds:
Direct:                                 2866500ns
Bound MethodHandle:                     8271100ns
MethodHandle:                           7342400ns
Reflect:                                7124000ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         5404600
Handle - Direct:                        4475900
Reflect - Direct:                       4257500

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.17183510343041114
Reflect - Bound / (Bound - Direct):     -0.212245124523554

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   -0.04879465582340982
==================================================

==================================================
Test results of 10*100000=1000000 rounds:
Direct:                                 23403300ns
Bound MethodHandle:                     28199700ns
MethodHandle:                           27949000ns
Reflect:                                33373200ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         4796400
Handle - Direct:                        4545700
Reflect - Direct:                       9969900

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.052268367942623635
Reflect - Bound / (Bound - Direct):     1.0786214660995748

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   1.1932595639835448
==================================================

==================================================
Test results of 10*1000000=10000000 rounds:
Direct:                                 218572400ns
Bound MethodHandle:                     231361400ns
MethodHandle:                           226761800ns
Reflect:                                245221100ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         12789000
Handle - Direct:                        8189400
Reflect - Direct:                       26648700

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.35965282664790055
Reflect - Bound / (Bound - Direct):     1.0837203847056063

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   2.2540479155982123
==================================================

==================================================
Test results of 10*10000000=100000000 rounds:
Direct:                                 2025875200ns
Bound MethodHandle:                     2114698200ns
MethodHandle:                           2111815500ns
Reflect:                                2201683500ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         88823000
Handle - Direct:                        85940300
Reflect - Direct:                       175808300

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.03245443184760704
Reflect - Bound / (Bound - Direct):     0.979310538936987

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   1.045702656378905
==================================================

==================================================
Test results of 10*100000000=1000000000 rounds:
Direct:                                 19686971700ns
Bound MethodHandle:                     20425297900ns
MethodHandle:                           20391888000ns
Reflect:                                21605664900ns

Bound/Handle/Reflect - Direct: 
Bound - Direct:                         738326200
Handle - Direct:                        704916300
Reflect - Direct:                       1918693200

Handle/Reflect VS Bound MethodHandle: 
Handle - Bound / (Bound - Direct):      -0.04525086608060232
Reflect - Bound / (Bound - Direct):     1.5987066421318923

Reflect VS MethodHandle: 
Reflect - Handle / (Handle - Direct):   1.7218737884199868
==================================================
```
