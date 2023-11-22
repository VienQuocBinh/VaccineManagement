
package MyService;

import DTO.Injection;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validation {

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isSecondDateValid(String firstDate, String secondDate) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            Date date1 = df.parse(firstDate);
            Date date2 = df.parse(secondDate);
            // Get time se tra ve milisecond tu 1/1/1970 nen doi don vi sang ngay
            long getDiff = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
            if (getDiff >= 28 && getDiff <= 84) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean confirmMessage() {
        boolean flag = false;
        int confirm = -1;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Do you want to remove this injection? (1: yes / 0: no): ");
                confirm = sc.nextInt();
                if (confirm < 0 || confirm > 1) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Enter again");
                flag = true;
            }
        } while (flag);
        return confirm == 1;
    }
    public static boolean isStudentIdValid(String studentId){
        try {
            String pattern = "SE[0-9]{5}";
            if(!studentId.matches(pattern) || studentId.isBlank()){
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isVaccineIdValid(String vaccineId){
        try {
            String pattern = "Covid-V[0-9]+";
            if(!vaccineId.matches(pattern) || vaccineId.isBlank()){
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isCharOnlyString(String s) {
        try {
            String pattern = "[a-zA-Z ]+";
            if (!s.matches(pattern) || s.isBlank()) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String encryptInjection(Injection i) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String srcText = i.toString();
        String enrText;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] srcByte = srcText.getBytes("UTF-8");
        byte[] enrByte = md.digest(srcByte);
        BigInteger bigInt = new BigInteger(1, enrByte);
        enrText = bigInt.toString(16);
        return enrText;
    }
    
    public static boolean verifyInjEnrcrypt(Injection i, String enrTexts) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String srcText = i.toString();
        String checkSum;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] srcBytes = srcText.getBytes("UTF-8");
        byte[] enrBytes = md.digest(srcBytes);
        BigInteger bigInt = new BigInteger(1, enrBytes);
        checkSum = bigInt.toString(16);
        return checkSum.equals(enrTexts);
    }
}
