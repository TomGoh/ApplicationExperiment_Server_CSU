package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateDate {

    public static String getYYYYMMDD(){

        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");

        return strNow1[0]+"-"+strNow1[1]+"-"+strNow1[2];
    }

}
