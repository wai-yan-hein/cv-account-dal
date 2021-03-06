/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.util;

import com.cv.account.api.util.ZipFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.slf4j.LoggerFactory;

/**
 *
 * @author WSwe
 */
public class Util1 {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Util1.class);

    public static String getEngChar(int i) {
        String[] engChar = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

        if ((i + 1) < 0 || (i + 1) > engChar.length) {
            return null;
        } else {
            return engChar[i + 1];
        }
    }

    public static boolean isNumber(String number) {
        boolean status;
        try {
            if (number != null && !number.isEmpty()) {
                Double.parseDouble(number);
                status = true;
            } else {
                status = true;
            }
        } catch (NumberFormatException ex) {
            logger.error("isNumber :" + ex.getMessage());
            status = false;
        }
        return status;
    }

    /*public static String getPropValue(String key) {
        String strFilter = "v.propKey = '" + key + "'";
        List<SystemProperty> listSP = HibernateUtil.findAllHSQL("SystemProperty", strFilter);

        if (!listSP.isEmpty()) {
            return listSP.get(0).getPropValue();
        } else {
            return null;
        }
    }*/
    public static Date toDate(Object objDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            if (objDate != null) {
                date = formatter.parse(objDate.toString());
            }
        } catch (ParseException ex) {
            logger.info("toDateStr Error : " + ex.getMessage());
        }

        return date;
    }

    public static Date toJavaDate(Object objDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        Date date = null;

        try {
            if (objDate != null) {
                date = formatter.parse(objDate.toString());
            }
        } catch (ParseException ex) {
            logger.info("toDateStr Error : " + ex.getMessage());
        }

        return date;
    }

    public static boolean isDate(String str) {

        return str.length() == 10;
    }

    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d1).equals(sdf.format(d2));
    }

    public static String toDateStrMYSQL(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;

        try {
            date = formatter.format(toDate(strDate));
        } catch (Exception ex) {
            logger.info("toDateTimeStrMYSQL : " + ex.getMessage());
        }

        return date;
    }

    public static boolean isMySqLDate(String strDate) {
        boolean status = true;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;
        try {
            date = formatter.format(toDate(strDate));
        } catch (Exception ex) {
            status = false;
            logger.info("toDateTimeStrMYSQL : " + ex.getMessage());
        }

        return status;
    }

    public static String toDateTimeStrMYSQL(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;

        try {
            date = formatter.format(toDate(strDate, "dd/MM/yyyy"));
        } catch (Exception ex) {
            logger.info("toDateTimeStrMYSQL : " + ex.getMessage());
        }

        return date;
    }

    public static String getTodayDateTimeStrMySql() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;

        try {
            date = formatter.format(new Date());
        } catch (Exception ex) {

        }

        return date;
    }

    public static String toDateStr(String strDate, String inFormat, String outFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(outFormat);
        String date = null;

        try {
            /*if (strDate.contains("-")) {
                date = strDate;
            } else {*/
            date = formatter.format(toDate(strDate, inFormat));
            //}
        } catch (Exception ex) {
            try {
                date = formatter.format(toDate(strDate, outFormat));
            } catch (Exception ex1) {
                logger.info("toDateStr : " + ex1.getMessage());
            }
        }

        return date;
    }

    public static String toDateStrMYSQLEnd(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;

        try {
            date = formatter.format(toDate(strDate, "dd/MM/yyyy")) + " 23:59:59";
        } catch (Exception ex) {
            logger.info("toDateStrMYSQL Error : " + ex.getMessage());
        }

        return date;
    }

    public static Date toDate(Object objDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = formatter.parse(objDate.toString());
        } catch (ParseException ex) {
            try {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                date = formatter.parse(objDate.toString());
            } catch (ParseException ex1) {
                logger.info("toDateStr Error : " + ex1.getMessage());
            }
        }

        return date;
    }

    public static boolean saveFile(String path, String fileName, byte[] content) {
        boolean status = true;

        try {
            File file = new File(path + "/" + fileName);
            //FileUtils.writeByteArrayToFile(file, content);
        } catch (Exception ex) {
            logger.error("saveFile : " + ex.toString());
            status = false;
        }

        return status;
    }

    public static String getFileExtension(String content) {
        String extension = "";

        if (content.contains("jpeg")) {
            extension = "jpg";
        } else if (content.contains("gif")) {
            extension = "gif";
        } else if (content.contains("tif")) {
            extension = "tif";
        } else if (content.contains("png")) {
            extension = "png";
        } else if (content.contains("bmp")) {
            extension = "bmp";
        }

        return extension;
    }

    public static String toDateStr(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String strDate = null;

        try {
            strDate = formatter.format(date);
        } catch (Exception ex) {
            System.out.println("toDateStr Error : " + ex.getMessage());
        }

        return strDate;
    }

    public static Date getTodayDate() {
        Date todayDate = Calendar.getInstance().getTime();
        return todayDate;
    }

    public static String getJsonElementValue(JsonElement je) {
        String tmpString = null;

        if (je != null) {
            try {
                tmpString = je.getAsString();
            } catch (Exception ex) {

            }
        }

        return tmpString;
    }

    public static String toDateStrMYSQL(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;

        try {
            date = formatter.format(toDate(strDate, format));
        } catch (Exception ex) {
            logger.info("toDateTimeStrMYSQL : " + ex.getMessage());
        }

        return date;
    }

    public static String addDateTo(String date, int ttlDay) {
        String output = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(toDate(date, "dd/MM/yyyy")); // Now use today date.
            c.add(Calendar.DATE, ttlDay);
            output = formatter.format(c.getTime());
        } catch (Exception ex) {
            logger.info("addDateTo : " + ex.getMessage());
        }

        return output;
    }

    public static Date addDateTo(Date date, int ttlDay) {
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date tmp = null;

        try {
            //c.setTime(toDate(date, "yyyy-MM-dd")); // Now use today date.
            c.setTime(date);
            c.add(Calendar.DATE, ttlDay);
            tmp = c.getTime();
        } catch (Exception ex) {
            logger.info("addDateTo : " + ex.getMessage());
        }

        return tmp;
    }

    public static String isNull(String strValue, String value) {
        if (strValue == null) {
            return value;
        } else if (strValue.isEmpty() || strValue.equals("")) {
            return value;
        } else {
            return strValue;
        }
    }

    public static String isNullObj(Object obj, String value) {
        if (obj == null) {
            return value;
        } else {
            return value;
        }
    }

    public static Date getLastDayOfMonth(String strDate, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(strDate, format));

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();
        return lastDayOfMonth;
    }

    public static int getDatePart(Date d, String format) {
        int intValue = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String value = sdf.format(d);

            if (!value.isEmpty()) {
                intValue = Integer.parseInt(value);
            }
        } catch (Exception ex) {

        }

        return intValue;
    }

    public static Double NZeroDouble(Object number) {
        Double value = 0.0;

        try {
            if (number == null) {
                return new Double(0);
            } else {
                return value = Double.parseDouble(number.toString());
            }
        } catch (NumberFormatException ex) {
            return new Double(0);
        }
    }

    public static int isNullZero(Integer value) {
        if (value == null) {
            return 0;
        } else {
            return value;
        }
    }

    public static double nullZero(String value) {
        if (value == null) {
            return 0;
        }

        if (value.isEmpty()) {
            return 0;
        }

        return Double.parseDouble(value);
    }

    public static boolean getNullTo(Boolean value) {
        if (value == null) {
            return false;
        } else {
            return value;
        }
    }

    public static String getPeriod(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMyyyy");
        String strPeriod = null;
        Date date = toDate(strDate, format);

        if (date != null) {
            strPeriod = formatter.format(date);
        }

        return strPeriod;
    }

    public static String getPeriod(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMyyyy");
        String strPeriod = null;

        if (date != null) {
            strPeriod = formatter.format(date);
        }

        return strPeriod;
    }

    public static String getZawgyiText(HashMap<Integer, Integer> hmIngZgy, String text) {
        String tmpStr = "";

        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                String tmpS = Character.toString(text.charAt(i));
                int tmpChar = (int) text.charAt(i);

                if (hmIngZgy.containsKey(tmpChar)) {
                    char tmpc = (char) hmIngZgy.get(tmpChar).intValue();
                    if (tmpStr.isEmpty()) {
                        tmpStr = Character.toString(tmpc);
                    } else {
                        tmpStr = tmpStr + Character.toString(tmpc);
                    }
                } else if (tmpS.equals("ƒ")) {
                    if (tmpStr.isEmpty()) {
                        tmpStr = "ႏ";
                    } else {
                        tmpStr = tmpStr + "ႏ";
                    }
                } else if (tmpStr.isEmpty()) {
                    tmpStr = tmpS;
                } else {
                    tmpStr = tmpStr + tmpS;
                }
            }
        }

        return tmpStr;
    }

    public static Double getDouble(Object number) {
        double value = 0.0;
        if (number != null) {
            if (!number.toString().isEmpty()) {
                value = Double.parseDouble(number.toString());
            }
        }
        return value;
    }

    public static Float getFloat(Object number) {
        float value = 0.0f;
        if (number != null) {
            if (!number.toString().isEmpty()) {
                value = Float.parseFloat(number.toString());
            }
        }
        return value;
    }

    public static Long getLong(Object number) {
        long value = 0;
        if (number != null) {
            value = Long.parseLong(number.toString());
        }
        return value;
    }

    public static Integer getInteger(String number) {
        int value = 0;
        if (!number.isEmpty()) {
            value = Integer.parseInt(number);
        }
        return value;
    }

    public static String getString(String str) {
        String value = "";
        if (!str.isEmpty()) {
            value = str;
        }
        return value;
    }

    public static String getString(Object obj) {
        String value = "";
        if (obj != null) {
            value = obj.toString();
        }
        return value;
    }

    public static String getStringValue(Object obj) {
        String value = "";
        if (obj != null) {
            value = obj.toString();
        }
        return value;
    }

    public static boolean getBoolean(Boolean obj) {
        if (obj == null) {
            obj = false;
        }
        return obj;

    }

    public static int getCurrentMonth() {
        LocalDate currentdate = LocalDate.now();
        return currentdate.getMonth().getValue();

    }

    public static boolean isValidDateFormat(Object dateStr, String dateFromat) {
        boolean status = true;
        DateFormat formatter = new SimpleDateFormat(dateFromat);

        Date date = null;
        if (isDate(dateStr.toString())) {
            try {
                date = formatter.parse(dateStr.toString());
            } catch (ParseException ex) {
                logger.info("isValidDateFormat Error : " + ex.getMessage());
                status = false;

            }

        } else {
            status = false;
        }
        return status;
    }

    public static String toFormatDate(String obj) {
        String[] arr = null;
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        //String strYear = String.valueOf(year).substring(0, 2);
        //logger.info("String year .." + strYear);
        arr = obj.split("(?<=\\G.{2})");

        String format = arr[0] + "/" + arr[1] + "/" + arr[2] + arr[3];
        return format;

    }

    public static JDialog getLoading(JDialog owner, ImageIcon icon) {
        JDialog dialog = new JDialog(owner, false);
        dialog.getContentPane().setBackground(Color.white);
        dialog.setSize(70, 70);
        dialog.getContentPane().setLayout(new BorderLayout());
        JLabel lblImg = new JLabel(icon);
        lblImg.setLocation(70, 0);
        dialog.add(lblImg);
        dialog.getContentPane().add(lblImg, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(null);
        dialog.setUndecorated(true);
        dialog.validate();
        return dialog;
    }

    public static Dimension getScreenSize() {
        //Calculate dialog position to centre.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        return screen;
    }

    public static String getComputerName() {
        String computerName = "";

        try {
            computerName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.info("getComputerName : " + e.toString());
        }

        return computerName;
    }

    public static String getIPAddress() {
        String iPAddress = "";

        try {
            iPAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.info("getIPAddress : " + e.toString());
        }

        return iPAddress;
    }

    public static String toFormatPattern(Double value) {
        final String pattern = "#,##0.00;(#,##0.00)";
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern(pattern);
        return df.format(value);

    }

    public static String getAppWorkFolder() {
        return System.getProperty("user.dir");
    }

    public static byte[] readfileAsBytes(File file) throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        byte[] bytes = new byte[(int) accessFile.length()];
        accessFile.readFully(bytes);
        return bytes;

    }

    public static void deletedFile(String zipfilepath) {

        File directory = new File(zipfilepath);
        File[] fList = directory.listFiles();

        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {

                    try {
                        file.delete();
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }

            }
        }

    }

    public static String genGSON(Object data, String filePath) throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString().concat(".json");
        try ( FileOutputStream fos = new FileOutputStream(filePath + fileName);  OutputStreamWriter isr = new OutputStreamWriter(fos, "UTF-8")) {

            Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
            gs.toJson(data, isr);
        }

        ZipFile zf = new ZipFile();
        String file = zf.zipFiles(filePath, fileName);
        return file;
    }

    public static String genGSON(List list, String filePath) throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString().concat(".json");
        try ( FileOutputStream fos = new FileOutputStream(filePath + fileName);  OutputStreamWriter isr = new OutputStreamWriter(fos, "UTF-8")) {

            Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
            gs.toJson(list, isr);
        }

        ZipFile zf = new ZipFile();
        String file = zf.zipFiles(filePath, fileName);
        return file;
    }

    public static String unzip(String zipFilePath, String destDirectory, String fileName) throws Exception {
        final int BUFFER_SIZE = 4096;
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath + File.separator + fileName));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + fileName.replaceAll(".zip", ".json");
            if (!entry.isDirectory()) {
                try ( BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                    byte[] bytesIn = new byte[BUFFER_SIZE];
                    int read = 0;
                    while ((read = zipIn.read(bytesIn)) != -1) {
                        bos.write(bytesIn, 0, read);
                    }
                }

            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
            zipIn.close();
        }
        return zipFilePath;
    }

    public static boolean isNull(Object value) {
        boolean status = false;
        if (value == null) {
            status = true;
        } else if (value.toString().isBlank()) {
            status = true;
        }
        return status;
    }
}
