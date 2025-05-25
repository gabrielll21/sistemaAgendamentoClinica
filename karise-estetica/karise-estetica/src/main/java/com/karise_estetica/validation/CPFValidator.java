// src/main/java/com/karise_estetica/validation/CPFValidator.java
package com.karise_estetica.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) return true;             // deixe @NotBlank cuidar disso
        String digits = cpf.replaceAll("\\D+", "");
        if (digits.length() != 11) return false;

        // não pode ser sequência de mesmo dígito
        if (digits.matches("(\\d)\\1{10}")) return false;

        try {
            int[] nums = digits.chars().map(c -> c - '0').toArray();
            // calcular 1º dígito verificador
            if (calc(nums, 9)  != nums[9])  return false;
            // calcular 2º dígito verificador
            if (calc(nums, 10) != nums[10]) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private int calc(int[] num, int length) {
        int sum = 0;
        for (int i = 0, weight = length + 1; i < length; i++, weight--) {
            sum += num[i] * weight;
        }
        int mod = (sum * 10) % 11;
        return (mod == 10) ? 0 : mod;
    }
}
