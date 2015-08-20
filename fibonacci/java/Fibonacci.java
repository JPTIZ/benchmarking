import java.util.HashMap;

public class Fibonacci {

    enum Mode {
        LOOP,
        RECURSIVE,
        CACHED
    }
    
    public static long fibonacci(int n) {
        if (n <= 2) return 1;
        long fn = 1, fn_0 = 1, old = 1;
        for (int i = 3; i <= n; i++) {
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
        long before = System.nanoTime();
        long result = mode == Mode.CACHED ? cached_fibonacci(n) : mode == Mode.RECURSIVE ? recursive_fibonacci(n) : fibonacci(n);
        long after = System.nanoTime();
        System.out.println((mode == Mode.CACHED ? "cached_" :
                            mode == Mode.RECURSIVE ? "recursive_" :
                            "")+"fibonacci("+n+") = "+result+" ("+((double)(after-before)/1000000000.0)+"s)");
    }
}

