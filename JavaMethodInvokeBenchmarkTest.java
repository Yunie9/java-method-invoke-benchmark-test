import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class JavaMethodInvokeBenchmarkTest {
    public static class Adder {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    long directlyInvoke(int numberOfRounds) {
        Adder adder = new Adder();
        int result = 0;

        long cost = 0;
        for (int i = 0; i < numberOfRounds; i++) {
            long start = System.nanoTime();
            result = adder.sum(result, i);
            cost += System.nanoTime() - start;
        }
        return cost;
    }

    long boundMethodHandleInvoke(int numberOfRounds) throws Throwable {
        Adder adder = new Adder();
        MethodType methodType = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Adder.class, "sum", methodType);
        // bind the method handle to the instance
        methodHandle = methodHandle.bindTo(adder);
        int result = 0;

        long cost = 0;
        for (int i = 0; i < numberOfRounds; i++) {
            long start = System.nanoTime();
            result = (int) methodHandle.invoke(result, i);
            cost += System.nanoTime() - start;
        }
        return cost;
    }

    long methodHandleInvoke(int numberOfRounds) throws Throwable {
        Adder adder = new Adder();
        MethodType methodType = MethodType.methodType(int.class, int.class, int.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Adder.class, "sum", methodType);
        int result = 0;

        long cost = 0;
        for (int i = 0; i < numberOfRounds; i++) {
            long start = System.nanoTime();
            result = (int) methodHandle.invoke(adder, result, i);
            cost += System.nanoTime() - start;
        }
        return cost;
    }

    long reflectInvoke(int numberOfRounds) throws Throwable {
        Adder adder = new Adder();
        Method method = Adder.class.getMethod("sum", int.class, int.class);
        int result = 0;

        long cost = 0;
        for (int i = 0; i < numberOfRounds; i++) {
            long start = System.nanoTime();
            result = (int) method.invoke(adder, result, i);
            cost += System.nanoTime() - start;
        }
        return cost;
    }


    public void test(int numberOfRounds1, int numberOfRounds2) throws Throwable {
        System.out.println("==================================================");

        final int numberOfRounds = numberOfRounds1 * numberOfRounds2;
        System.out.println("Test results of " +
                numberOfRounds1 + "*" + numberOfRounds2 + "=" + +numberOfRounds + " rounds:");

        long directlyInvokeCost = 0;
        long boundHandleInvokeCost = 0;
        long handleInvokeCost = 0;
        long reflectInvokeCost = 0;

        for (int i = 0; i < numberOfRounds1; i++) {
            directlyInvokeCost += directlyInvoke(numberOfRounds2);
            boundHandleInvokeCost += boundMethodHandleInvoke(numberOfRounds2);
            handleInvokeCost += methodHandleInvoke(numberOfRounds2);
            reflectInvokeCost += reflectInvoke(numberOfRounds2);
        }

        System.out.println("Direct:                                 " + directlyInvokeCost + "ns");
        System.out.println("Bound MethodHandle:                     " + boundHandleInvokeCost + "ns");
        System.out.println("MethodHandle:                           " + handleInvokeCost + "ns");
        System.out.println("Reflect:                                " + reflectInvokeCost + "ns");

        System.out.println("\nBound/Handle/Reflect - Direct: ");
        System.out.println("Bound - Direct:                         " + (boundHandleInvokeCost - directlyInvokeCost));
        System.out.println("Handle - Direct:                        " + (handleInvokeCost - directlyInvokeCost));
        System.out.println("Reflect - Direct:                       " + (reflectInvokeCost - directlyInvokeCost));

        System.out.println("\nHandle/Reflect VS Bound MethodHandle: ");
        System.out.println("Handle - Bound / (Bound - Direct):      " +
                (handleInvokeCost - boundHandleInvokeCost) / (double) (boundHandleInvokeCost - directlyInvokeCost));
        System.out.println("Reflect - Bound / (Bound - Direct):     " +
                (reflectInvokeCost - boundHandleInvokeCost) / (double) (boundHandleInvokeCost - directlyInvokeCost));

        System.out.println("\nReflect VS MethodHandle: ");
        System.out.println("Reflect - Handle / (Handle - Direct):   " +
                (reflectInvokeCost - handleInvokeCost) / (double) (handleInvokeCost - directlyInvokeCost));

        System.out.println("==================================================");

    }

    public static void main(String[] args) throws Throwable {
        JavaMethodInvokeBenchmarkTest test = new JavaMethodInvokeBenchmarkTest();

        // Warm up by 100*100 rounds
        System.out.println("Warming up...");
        test.test(100, 100);
        System.out.println("Warm up finished.\n");

        test.test(10, 10_000);
        System.out.println();

        test.test(10, 100_000);
        System.out.println();

        test.test(10, 1_000_000);
        System.out.println();

        test.test(10, 10_000_000);
        System.out.println();

        test.test(10, 100_000_000);
        System.out.println();
    }
}
