//----------------------------------------------------------------------------
// Usage:
//  Compile: javac Fibonacci.java
//  Run: java Fibonacci [<flag>] <n>
//      <flag>: Method.
//          -r: Recursion Fibonacci;
//          -c: Cached Recursion Fibonacci;
//          (else: n < 2 ? n : for_iteration)
//
//      <n>: Fibonacci Parameter (from fib(n)).
//
//  My Best Benchmark for fib(30):
//      $ time java Fibonacci 30
//          fibonacci(30) = 832040           (0m0.120s)
//      $ time java Fibonacci -r 30
//          recursive_fibonacci(30) = 832040 (0m0,123s)
//      $ time java Fibonacci -c 30
//          cached_fibonacci(30) = 832040    (0m0,117s)
//
//  My Best Benchmark for fib(1000000):
//      $ time java Fibonacci 1000000
//          fibonacci(1000000) = -4249520595888827205 (0m0.133s)
//      $ time java Fibonacci -r 1000000
//          STACK OVERFLOW AT RECURSION Nº 12490      (0m0.450s)
//      $ time java Fibonacci -c 1000000
//          STACK OVERFLOW AT RECURSION Nº 7065       (0m0.203s)
//----------------------------------------------------------------------------

import java.util.HashMap;

public class Fibonacci {

    enum Mode {
        LOOP,
        RECURSIVE,
        CACHED
    }
    
    public static long fibonacci(int n) {
        if (n < 2) return n;
        long fn = 1, fn_0 = 1, old = 1;
        for (int i = 2; i < n; i++) {
            old = fn;
            fn = fn + fn_0;
            fn_0 = old;
        }
        return fn;
    }

    public static  HashMap<Integer, Long> cache = new HashMap<>();

    public static long cached_fibonacci(int n) {
        if (n <= 2) return 1;
        if (cache.get(n) != null) {
            return cache.get(n);
        } else {
            long result = cached_fibonacci(n-1) + cached_fibonacci(n-2);
            cache.put(n, result);
            return result;
        }
    }

    public static long recursive_fibonacci(int n) {
        if (n <= 2) return 1;
        return recursive_fibonacci(n-1) + recursive_fibonacci(n-2);
    }

    public static void main(String[] args) {
        Mode mode = Mode.LOOP;
        for (String arg : args) {
            if (arg.equals("-r")) {
                mode = Mode.RECURSIVE;
                break;
            } else if (arg.equals("-c")) {
                mode = Mode.CACHED;
                break;
            }
        }
        int n = Integer.parseInt(args[args.length-1]);
        //long before = System.nanoTime();
        long result = mode == Mode.CACHED ? cached_fibonacci(n) : mode == Mode.RECURSIVE ? recursive_fibonacci(n) : fibonacci(n);
        //long after = System.nanoTime();
        //double elapsed_sec = (double)(after-before)/1000000000.0;
        //int elapsed_min = (int)elapsed_sec / 60;
        //elapsed_sec %= 60.0;
        System.out.printf("%sfibonacci(%d) = %d", (mode == mode.CACHED ? "cached_" : mode == Mode.RECURSIVE ? "recursive_" : ""), n, result);
        //System.out.printf("%sfibonacci(%d) = %d (%dm%.3fs)\n", (mode == Mode.CACHED ? "cached_" :
        //                    mode == Mode.RECURSIVE ? "recursive_" :
        //                    ""), n, result, elapsed_min, elapsed_sec);
    }
}

