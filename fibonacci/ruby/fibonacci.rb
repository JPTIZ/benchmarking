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
#----------------------------------------------------------------------------

def recursive_fibonacci(n)
    return 1 if n <= 2
    return recursive_fibonacci(n-1) + recursive_fibonacci(n-2)
end

def fibonacci(n)
  return 1 if n <= 2
  fn, fn_0 = 1, 1
  for i in 3..n
    fn, fn_0 = fn + fn_0, fn
  end
  return fn
end

r = ARGV.include?("-r")
n = ARGV[-1].to_i
puts "#{r ? "r_" : ""}fib(#{n}) = #{r ? recursive_fibonacci(n) : fibonacci(n)}"

