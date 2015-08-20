# class Teste
# 	def initialize(x = 4)
# 		@x = x
# 		print "x = #{x}\n"
# 	end
#
# 	def self.x
# 		return @x
# 	end
#
# 	def self.x=(value)
# 		@x = value
# 	end
#
# 	def x
# 		return @x
# 	end
# end
#
# a, t = Teste.new, Teste.new(18)
# print t.x.to_s+"\n"
# print a.x.to_s+"\n"
# print "#{(t.x - a.x)}\n"

#print "\n\n\n"
#print 666**666**2
#

def recursive_fibonacci(n)
    return 1 if n <= 2
    return recursive_fibonacci(n-1) + recursive_fibonacci(n-2)
end

def fibonacci(n)
  return 1 if n <= 2 # poderia ser n == 1 or n == 2, mas assim é mais performático
                     # e lida melhor com "n"s fora do contexto - mas não altera a
                     # complexidade de crescimento da função, visto que fazer uma comparação
                     # tem ordem de crescimento O(1) (ou seja, constante a medida que o dado cresce)
  fn, fn_0 = 1, 1
  for i in 3..n
    fn, fn_0 = fn + fn_0, fn
  end
  return fn
end

#print "modo (1 = recursivo/qualquer coisa = iterativo): "
r = ARGV.include?("-r")
#print "calcular fib de...: "
x = ARGV[-1].to_i
puts "#{r ? "r_" : ""}fib(#{x}) = #{r ? recursive_fibonacci(x) : fibonacci(x)}"

