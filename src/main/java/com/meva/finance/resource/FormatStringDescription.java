package com.meva.finance.resource;

import com.meva.finance.entity.Category;
import com.meva.finance.repository.CategoryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FormatStringDescription {

    @Autowired
    private CategoryRepository categoryRepository;


    public String removeStringPequenas(String string) {
        StringBuilder newString = new StringBuilder();

        String[] palavras = string.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.length() >= 3) {

                categoryRepository.findByDescription(palavra);


                newString.append(palavra);
            }


        }


        return newString.toString();
    }
}
