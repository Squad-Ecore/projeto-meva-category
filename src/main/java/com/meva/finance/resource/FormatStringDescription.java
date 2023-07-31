package com.meva.finance.resource;

import org.springframework.stereotype.Component;

@Component
public class FormatStringDescription {

    public static String removeStringPequenas(String string) {
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
