#-----------------------------------------------------------------------------
# Usage:
# time ruby fibonacci.rb [<flag>] <n>
# 
# <flag>:
#   -r: recursive_fibonacci
#   -c: cached_fibonacci (recursion + cache)
#   (blank: loop 3..n if n > 2 || 1)
#
# <n>:
#   fibonacci's parameter
#
# My Best Benchmark for fib(30):
#   $ time ruby fibonacci.rb 30
#       fib(30) = 832040   (0m0.030s)
#   $ time ruby fibonacci.rb -r 30
#       r_fib(30) = 832040 (0m0.170s)
#   $ time ruby fibonacci.rb -c 30
#       c_fib(30) = 832040 (0m0.033s)
#
# My Best Benchmark for fib(1000000):
#   $time ruby fibonacci.rb 1000000
#       STACK OVERFLOW AT RECURSION Nº 7695   (0m0.040s)
#   $time ruby fibonacci.rb -r 1000000
#       STACK OVERFLOW AT RECURSION Nº 9347   (0m0.053s)
#   $time ruby fibonacci.rb -c 1000000
#       c_fib(1000000) = <really big number> (0m15.433s)
#----------------------------------------------------------------------------

$cache = {}

def cached_fibonacci(n)
  return $cache[n] ||= n < 2 ? n : cached_fibonacci(n-1) + cached_fibonacci(n-2)
end

def recursive_fibonacci(n)
  return n < 2 ? n : recursive_fibonacci(n-1) + recursive_fibonacci(n-2)
end

def fibonacci(n)
  return n if n < 2
  fn, fn_0 = 1, 1
  (2..n-1).each do |i|
    fn, fn_0 = fn + fn_0, fn
  end
  return fn
end

mode = ARGV.include?("-r") ? :r : ARGV.include?("-c") ? :c : :none
n = ARGV[-1].to_i
puts "#{mode == :r ? "r_" : mode == :c ? "c_" : ""}fib(#{n}) = #{mode == :c ? cached_fibonacci(n) : mode == :r ? recursive_fibonacci(n) : fibonacci(n)}"

