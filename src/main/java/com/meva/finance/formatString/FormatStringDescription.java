package com.meva.finance.formatString;

import org.springframework.stereotype.Component;

@Component
public class FormatStringDescription {

    public String removeStringPequenas(String string) {
        StringBuilder newString = new StringBuilder();

        String[] palavras = string.split("\\s+");


        for (String palavra : palavras) {
            if (palavra.length() >= 3) {
                newString.append(palavra).append(" ");
            }
        }
        return newString.toString().trim();

    }

}
