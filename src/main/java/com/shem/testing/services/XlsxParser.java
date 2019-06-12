package com.shem.testing.services;

import com.shem.testing.Question;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class XlsxParser {
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    private Set<String> themes = new HashSet<>();

    public Set<String> getThemes() {
        return themes;
    }

    @PostConstruct
    void init(){
        questions = getQuestionsFromExcel();
    }


    public List<Question> getQuestionsFromExcel() {
        List<Question> questions = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook("questions.xlsx");

            XSSFSheet sheet = workbook.getSheetAt(0);

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
                        themes.add(stringCellValue);
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
