package com.dtstack.spi;

import com.dtstack.core.IStudent;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.ServiceLoader;

/**
 * @company: www.dtstack.com
 * @Author ：WangChuan
 * @Date ：Created in 下午3:02 2020/5/24
 * @Description：
 */
public class SPITest {

    private static String pluginName = String.format("%s/%s",System.getProperty("user.dir"),"core/plugins/");
    public static void main(String[] args) throws Exception{
        File file = new File(pluginName);
        ArrayList<URL> urls = new ArrayList<>();
        if (file.exists()){
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File f:files){
                    urls.add(f.toURI().toURL());
                }
            }
        }
        URLClassLoader loader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
        Thread.currentThread().setContextClassLoader(loader);
        ServiceLoader<Service> load = ServiceLoader.load(Service.class);
        for (Service service:load){
            IStudent stu = service.get2();
            System.out.println(IStudent.class.getClassLoader());
            System.out.println(stu.getClass().getClassLoader());
            System.out.println(stu.getAddr());
        }
    }
}
