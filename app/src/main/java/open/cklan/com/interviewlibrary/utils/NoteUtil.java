package open.cklan.com.interviewlibrary.utils;

import android.content.Context;

import com.example.Note;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dalvik.system.DexFile;

/**
 * AUTHOR：lanchuanke on 17/9/6 17:03
 * 获取包名下类上面的注释，用来作为文档
 */
public class NoteUtil {

    public static final List<String> getNoteByPkg(String packageCodePath, String packageName){
        List<String> notes=new ArrayList<>();
        List<String> classNameList=getClassName(packageCodePath,packageName);
        try {
            if(classNameList!=null){
                for(String className:classNameList){
                    Class clz=Class.forName(className);
                    boolean annotationPresent = clz.isAnnotationPresent(Note.class);
                    if(annotationPresent){
                        StringBuilder sb=new StringBuilder();
                        Note note= (Note) clz.getAnnotation(Note.class);
                        if(note!=null){
                            sb.append(clz.getSimpleName()+":\n");
                            sb.append(note.value()+"\n");
                            notes.add(sb.toString());
                        }
                    }

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return notes;
    }

    private static  List<String > getClassName(String packageCodePath,String packageName){
        List<String >classNameList=new ArrayList<String >();
        try {

            DexFile df = new DexFile(packageCodePath);//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();

                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }
}
