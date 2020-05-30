### 利用SPI机制和URLCLassloader实现插件化开发

#### 一、模块说明：

1. ##### common：

   放一些公用的util、方法类、枚举类等

2. ##### core：

   需要被主程序加载的模块，也就是使用APPClassloader类加载器来进行加载的

3. ##### service：

   具体的插件模块，这里只用了一个模块

#### 二、实现步骤：

1. 在core模块中定义一个spi接口Service，和一个IStudent接口
2. 插件模块依赖于core模块
3. 在service模块中新建一个类ServiceImpl实现core模块的Service接口，新建一个类Student实现IStudent接口
4. 在ServiceImpl重写Service方法，new一个Student对象，方法返回值类型写IStudent
5. 在service模块的resources文件夹下新建一个META-INF文件夹，再新建一个services文件夹，里面新建一个文件，文件名就是Service接口的全限定累名，文件内容写实现类的全限定类名
6. 将service模块打成jar包放到core模块的plugins文件夹下
7. 在core包下新建一个测试类SPITest，通过URLClassloader将jar包加载进来，再调用ServiceLoader利用spi机制发现Service的实现类，这个时候因为Service的实现类使用的类加载器是UrlClasslaoder，而ServiceLoader默认是用的APPClasslaoder，并不能发现Service的实现类，这个时候可以有两种方法解决，一是将当前线程上线文类加载器设置成URLClassloader，二是ServiceLoader.load的第二个参数设置成当前使用的URLClassloader，这样就可以发现实现类了，然后调用方法就可以了

