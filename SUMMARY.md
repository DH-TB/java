**DAY 1**

+ int 溢出
```
int * int 可能会超过int的最大值，所以应该转为更长整型 long
判断溢出的方法：Math.addExact(left, right)
源码：
int r = x + y;
if (((x ^ r) & (y ^ r)) < 0) {
  throw new ArithmeticException("integer overflow");
}
只有当 x y 是同符号的时候才可能会溢出（x 和 y 的符号都跟 r 不一样）
```

+ 算术运算的类型
```
long * int = long 只要其中一个是 long，结果就为 long
double * float = double
short * short = int 
```

+ Math取整
```
final int number = Math.round();
floor  向下取整
ceil   向上取整
round  四舍五入取整（只根据小数点后一位来判断）
```

+ INFINITY的定义
```
public static final double POSITIVE_INFINITY = 1.0 / 0.0;
public static final double NEGATIVE_INFINITY = -1.0 / 0.0;
INFINITY：表示无限，乘以 0 等于NAN，做其他运算结果还是无限
```

+ NAN的定义：
```
public static final double NaN = 0.0d / 0.0;
NAN：表示非数字，和谁都不相等，也不等于自己
举例：assertFalse(Double.NaN == Double.NaN);
```

+ 几个基本类型的默认值：
```
boolean：false
int / short / byte / long：0
char：'\u0000'
double：0.0d
float：0.0f
long：0L
String：null
Object：null
```

+ 基本类型——字节byte —— bit位  
```
byte —— 1字节 —— 1*8   
short —— 2字节 —— 2*8    
int —— 4字节 —— 4*8 
long —— 8字节 —— 8*8
float —— 4字节 —— 4*8 
double —— 8字节 —— 8*8 
boolean —— 1字节 —— 1*8 
char —— 2字节 —— 2*8 ——无符号              
```

+ 运算符优先级
```
括号 > 单目 > 算术 > 关系 > 位 > 逻辑 > 条件 > 赋值
```

+ 位运算符
```
^ 异或运算符，相同为0，不同为1
~  非运算符，按位取反
优先级：~ > & > |
```

+ 区别：`&&`（逻辑与） `&`（按位与） `|`（按位或） `||`（逻辑或）
```
|| ：只要满足第一个条件，后面条件就不再判断
 | ：要对所有的条件都进行判断
```

+ 移位运算符
```
<< ：左移运算符，num << 1,相当于num乘以2
>> ：右移运算符，num >> 1,相当于num除以2
>>>：无符号右移，忽略符号位，空位都以0补齐
举例： 1000 …….. 1000
不带符号位（整体右移1） >>>：0100 ……0100
带符号位（符号位不动）>>：1000 …….. 0100
```

**DAY 2**

+ 类的访问修饰符

![](https://upload-images.jianshu.io/upload_images/3087126-9df1865c3184f1a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

+ final 修饰符
```
final 类   不能被继承
final 方法  不能被重写
final 变量（引用类型）  不能修改引用
final 变量（基本类型）  不能修改值
```

+ Unicode实现方式：包含了UTF-8、UTF-16、UTF-32
```
UTF-8：可以用1 / 2 / 3 字节来表示
UTF-16：可以用 2 / 4 字节来表示
UTF-32：可以用 4 字节来表示
```

+ codePoint 和 char
```
一个char 不能完全代表一个code point，一个code point 可能会超过两个字节，一个char 表示不了
所以 code point 用 int 存储
```

+ 字符串拼接
```
因为String不可变，每次拼接都会产生新的字符串，效率低，可以使用String.format()或者 new StringBuilder()
new StringBuffer() 是线程安全的，所以平常使用new StringBuilder()
```

+ Java Equals() 特性
```
1、自反性：对于非空引用值 x，x.equals(x) 都应返回 true。
2、对称性：对于非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。
3、传递性：对于非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
4、一致性：对于非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是equals比较的信息没有被修改。
5、对于任何非空引用值 x，x.equals(null) 都应返回 false。
```

+ Equals() 和 == 的区别 
```
equals() 比较值相等
== 比较值和引用都相等
但 Object.equals() 比较的是引用，所有继承了 Object 的对象，如果没有重写equals() 方法，也是比较引用
一般重写equals() 是为了只比较值是否相等
如果 Identify ，则一定Equality
如果Equality，则hashcode一定相等
```

+ 重写Equals方法
```
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
   
    OtherClass klass = (OtherClass) obj; 
    return name.equals(klass.name);
}
```

+ 重写HashCode方法
```
@Override
public int hashCode() {
   return Objects.hash(name, yearOfBirth);
}
```

+ 栈溢出
```
stackoverflow，可能情况：递归没终止条件
```

+ 参数
```
arguments 实际参数（传入的参数）
parameters 形式参数(接受的参数)
```

+ 初始化顺序 
```
Init block 和 filed  都和声明顺序有关，但是都在constructor之前执行
```

+ 变量初始化 
```
局部变量声明后，Java虚拟机不会自动给它初始化为默认值，必须经过显示的初始化，才能使用。
如果使用一个没有被初始化的局部变量，编译器会报错。
类的成员变量，Java虚拟机都会先自动给它初始化为默认值。
Java虚拟机会为数组的所有元素初始化为相应类型的默认值。引用类型被初始化为 null ，基本类型初始化为相应的值
```

**DAY 3**

+ 基本类型：从左到右转换规则表

|   | char | byte   | short | int  | long  | float  |  double |
|---|---|---|---|---|---|---|---|
| char    |  | 显|  显|隐 |隐   | 隐  | 隐  |
| byte    | 显  | |  隐 | 隐  |隐   | 隐  | 隐  |
| short     | 显 |  显 | | 隐  | 隐  | 隐  | 隐  |
| int       | 显|   显 | 显  |   | 隐  | 隐  | 隐  |
| long      | 显 |  显 | 显  | 显  | | 隐  |  隐 |
| float     | 显 |  显 | 显  | 显  | 显  |  | 隐  |
| double  | 显  | 显 | 显  | 显  | 显  | 显  | |

+ 基本类型
```
长整型转换为短整形，是直接截取低位的值，不保留符号位
短整形可以直接转换为长整型，高位补0
举例：int是32位的，short是int的低16位，char是int的最低8位
```

+ 引用类型
```
子类可以隐示转换成父类 
父类可以强制转换成子类，语法没有错误，但是运行时有错(通过转换成Object，再进行转换就不会报错)
只有在同一继承链上才可以转换，否则不能转换
```

+ 重写 与 重载的区别
```
重写是：子类继承父类，重写父类的方法，要求方法名和参数列表都相同
重载是：相同的方法名称，不同的方法签名（参数列表不同：包括个数/类型/顺序）返回类型不影响
方法重载，在编译时确定调用哪个方法
方法重写，在运行时确定调用的是子类还是父类的方法
```

+ 可变参数
```
使用...表示可变长参数，比如：Object… 
可变长参数必须是该方法的最后一个参数
编译器在传递的时候，会将参数列表包裹成对象数组，如果本身已经是对象数组编译器就不会进行包裹
```

+ 重载方法的形参匹配规则优先级：
```
基本类型：默认会自动从“短类型”扩展成“长类型”，如果没有精确匹配，优先匹配 存储长度 大于且最接近的
引用类型：如果没有精确匹配，优先匹配 在继承树结构上，离实参类型最近的
总结：当前类型（基本类型或引用类型 > 自动装箱拆箱 > 可变参数列表
```

+ 自动装箱拆箱 
```
自动装箱：Java自动将原始类型转换成对应的对象，比如：将int类型值转换成Integer对象
自动拆箱：将对象转换为原始类型，比如：Integer对象转换成int类型值
自动装箱时：编译器调用 valueOf 将原始类型值转换成对象
自动拆箱时：编译器调用类似 intValue(), doubleValue() 这类方法将对象转换成原始类型值
```

+ 反射
```
类中的方法 ：存放在Class（method table）中，它的头部是元数据，只存一份，不会因为多个实例化创建多个
类中的字段：存放在堆里面，多个实例化会创建多个
Class类 是反射的入口，可以在运行时拿到类里面的很多信息（filed / method / type / annotations等）
```

+ 获取类Class
```
Employee.getClass() 
Employee.class
```

+  getMethods 和 getDeclaredMethods 区别
```
getMethods: 本类 / 继承类 / 实现类的public method，如果重写了方法，则只会拿到本类的方法
getDeclaredMethods: 本类声明的所有方法（public / protected /default /  private），不包含继承的方法。
```


**DAY 4**

+ 接口的特性
```
什么是接口：是为了规范行为，提供约束，从而规定的一系列行为
接口：默认是抽象的
接口：不能实例化
接口：没有private
接口：可以有多个抽象方法（抽象方法没有body），也可以有多个default / static 方法
接口： default 方法可以不被子类重写，其他方法（public /abstract）必须在实现类中重写
接口只能继承接口，不能实现接口 （interface extends interface）
abstract 声明的类不能实例化
instance of 在继承 / 实现中都可以用来判断是否是实例
```

+ 函数式接口
```
@FunctionalInterface注解来显式指定一个接口是函数式接口
函数式接口：只能有一个抽象方法
```

+ 抽象类和接口区别
```
单继承，一个类只能继承一个父类，有 public / private 
多接口，一个类可以实现多个接口
当实现了多个接口时，可以显示通过 父类.super.method()来指定调用哪个父类的方法，而且必须重写方法，哪怕是default方法
接口默认是public， 类里面默认是package-private
```

+ Lambda表达式
```
::  Method Reference
Lambda：是一个函数式接口
Lambda：允许把函数作为一个方法的参数
Lambda：只能引用标记了 final 的外层局部变量，不能在 lambda 内部修改定义在域外的局部变量，会有编译错误。
Lambda：在编译时，表达式里面的变量直接定义在闭包（closure）里面，函数执行完不会销毁，闭包保存了变量定义时的环境
```

+ 注解
```
@interface Annotation{ } 定义一个注解 @Annotation，一个注解是一个类
```

+ 元注解
```
@Target：定义了修饰的类型
@Retention：定义了注解的声明周期
@Documented：是一个标记注解，没有成员，被修饰的注解会生成到javadoc
@Inherited：子类Class对象可以使用getAnnotations()获取父类被@Inherited修饰的注解
```

+ 继承（构造函数调用）
```
子类实例化时，会先调用父类的无参构造函数，再调用自己的构造函数
每个构造方法里面只能有一个（this / super）
如果构造方法里显示指定了（this / super），则会继续调用一次
可以用 this(args) / super(args) 显示指定调用有参构造还是无参构造
如果子类 / 父类 没有显示定义构造函数，会默认有一个无参构造函数
递归调用，编译错误
```