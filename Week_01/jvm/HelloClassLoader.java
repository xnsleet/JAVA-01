package jvm;

import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;
public class HelloClassLoader extends ClassLoader {
    public static String filePath = "C:/java-learn/java-advance/src/jvm/Hello.xlass";

    public static void main(String[] args) {
        try {
            Class<?> aClass = new HelloClassLoader().findClass("Hello");
            aClass.getMethod("hello").invoke(aClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String base64 = encodeBase64File(filePath);
        byte[] bytes = decode(base64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private String encodeBase64File(String path) {
        String encode = null;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();
            encode = new BASE64Encoder().encode(bytes).replace("\r\n", "");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encode;
    }

    private byte[] decode(String base64) {
        byte[] decode = Base64.getDecoder().decode(base64);
        byte num = (byte) 255;
        if (decode != null) {
            for (int i = 0; i < decode.length; i++) {
                byte old = decode[i];
                decode[i] = (byte) (num - old);
            }
        }
        return decode;
    }
}
