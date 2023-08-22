package com.meva.finance.resource;

import com.meva.finance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormatStringDescription {

    @Autowired
    private CategoryRepository categoryRepository;


    public static String removeStringPequenas(String string) {
        StringBuilder newString = new StringBuilder();

        String[] palavras = string.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.length() >= 3) {

                newString.append(palavra);
            }

        }


        return newString.toString();
    }
}
