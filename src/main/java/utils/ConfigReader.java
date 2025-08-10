package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static String getProperty(String key)
    {
        if (prop==null)
        {
            try {
                FileInputStream fis=new FileInputStream("config.properties");
                prop=new Properties();
                prop.load(fis);
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return prop.getProperty(key);

    }
}
