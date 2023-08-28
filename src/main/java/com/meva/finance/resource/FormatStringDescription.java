package com.meva.finance.resource;

import com.meva.finance.entity.Category;
import com.meva.finance.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FormatStringDescription {

    @Autowired
    private CategoryService categoryService;


    public String removeStringPequenas(String string) {


        String[] palavras = string.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.length() >= 3) {


                Category category = categoryService.buscaCategoryAtravesSubCategory(palavra);
                if (category != null) {
                    return category.getDescription();
                }

            }
        }
        return new Category(1, "Nao_Categorizado").getDescription();
    }


}
