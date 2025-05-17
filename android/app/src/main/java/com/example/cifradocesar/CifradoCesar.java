package com.example.cifradocesar;

public class CifradoCesar {

    public static String encrypt(String text, int shift) {
        Alfabeto[] letras = Alfabeto.values();
        int totalLetras = letras.length;
        StringBuilder result = new StringBuilder();

        shift = ((shift % totalLetras) + totalLetras) % totalLetras;

        for (char ch : text.toCharArray()) {
            char mayuscula = Character.toUpperCase(ch);

            int index = buscarIndice(letras, mayuscula);

            if (index != -1) {
                int nuevoIndice = index + shift;

                if (nuevoIndice >= totalLetras) {
                    nuevoIndice -= totalLetras;
                }

                char letraCifrada = letras[nuevoIndice].name().charAt(0);

                if (Character.isLowerCase(ch)) {
                    letraCifrada = Character.toLowerCase(letraCifrada);
                }

                result.append(letraCifrada);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    private static int buscarIndice(Alfabeto[] letras, char ch) {
        for (int i = 0; i < letras.length; i++) {
            if (letras[i].name().charAt(0) == ch) {
                return i;
            }
        }
        return -1;
    }
}
