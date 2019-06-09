package com.shem.testing.parsers;

import com.shem.testing.Question;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class XlsxParser {

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(ResourceUtils.getFile("classpath:questions.xlsx"));

            // Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Get iterator to all the rows in current sheet


            for (Row row : sheet) {
                // Get iterator to all cells of current row
                Iterator<Cell> cellIterator = row.cellIterator();
                Question question = new Question();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int column = cell.getAddress().getColumn();
                    String stringCellValue = cell.getStringCellValue();
                    if (column == 0) {
                        question.setTheme(stringCellValue);
                    } else if (column == 1) {
                        question.setQuestion(stringCellValue);
                    } else {
                        question.getOptions().add(stringCellValue);
                        if (cell.getCellStyle().getFillForegroundColorColor() != null) {
                            question.getAnswers().add(cell.getAddress().getColumn() - 2);
                        }
                    }
                }
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}
