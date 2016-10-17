package ru.dnsprice.com.utils.logic;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by shestakov.m on 17.10.2016.
 */

@Service("readXlsx")
public class ReadXlsx {

    public HashMap<String,String> Read(String filePath) {
        HashMap<String,String> array = new HashMap<String, String>();
        InputStream iS = null;
        try {
            iS = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными ставок не найден");
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(iS);
        } catch (IOException e) {
            System.out.println("Что-то не так с файлом для заливки, проверьте файл");;
        }
        XSSFSheet sh = wb.getSheet("Лист1");
        for (int row = 0;row < sh.getLastRowNum(); row++){
            Row row1 = sh.getRow(row);
            array.put(String.valueOf(row1.getCell(0)),String.valueOf(row1.getCell(1)));
        }
        System.out.println("Последняя ячейка с данными в ексель " + sh.getLastRowNum());
        return array;

    }
}
