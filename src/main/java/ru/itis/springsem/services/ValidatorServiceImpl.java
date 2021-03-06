package ru.itis.springsem.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public boolean isEmailValid(final char[] input) { // Строка, которую нужно проверить
        if (input == null) {
            return false;
        }

        int state = 0; // Состояние проверки (-1 для ошибки)
        char ch; // Текущий символ (input[index])
        int index = 0; // Итератор для input
        int mark = 0;
        String local = null;
        ArrayList<String> domain = new ArrayList<>();

        while (index <= input.length && state != -1) {

            if (index == input.length) {
                ch = '\0'; // Обозначаем конец нашей работы
            }
            else {
                ch = input[index];
                if (ch == '\0') {
                    // символ, которым мы кодируем конец работы, не может быть частью ввода
                    return false;
                }
            }

            switch (state) {

                case 0: {
                    // Первый символ {atext} -- текстовой части локального имени
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 1: {
                    // Остальные символы {atext} -- текстовой части локального имени
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        break;
                    }
                    if (ch == '.') {
                        state = 2;
                        break;
                    }
                    if (ch == '@') { // Конец локальной части
                        local = new String(input, 0, index - mark);
                        mark = index + 1;
                        state = 3;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 2: {
                    // Переход к {atext} (текстовой части) после точки
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 3: {
                    // Переходим {alnum} (домену), проверяем первый символ
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 4: {
                    // Собираем {alnum} --- домен
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        break;
                    }
                    if (ch == '-') {
                        state = 5;
                        break;
                    }
                    if (ch == '.') {
                        domain.add(new String(input, mark, index - mark));
                        mark = index + 1;
                        state = 5;
                        break;
                    }
                    // Проверка на конец строки
                    if (ch == '\0') {
                        domain.add(new String(input, mark, index - mark));
                        state = 6;
                        break; // Дошли до конца строки -> заканчиваем работу
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 5: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    if (ch == '-') {
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 6: {
                    // Успех! (На самом деле, мы сюда никогда не попадём)
                    break;
                }
            }
            index++;
        }

        // Остальные проверки

        // Не прошли проверку выше? Возвращаем false!
        if (state != 6)
            return false;

        // Нам нужен домен как минимум второго уровня
        if (domain.size() < 2)
            return false;

        // Домен верхнего уровня должен состоять только из букв и быть не короче двух символов
        index = input.length - 1;
        while (index > 0) {
            ch = input[index];
            if (ch == '.' && input.length - index > 2) {
                return true;
            }
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                return false;
            }
            index--;
        }
        return true;
    }
}
