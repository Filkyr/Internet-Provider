package by.bsuir.service.util;

import by.bsuir.service.exception.ServiceDataException;

public class Validator {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$";
    public static final String PASSWORD_PATTERN = "^[!@#$%^&*()+/*+{}\\[\\]|;:?\\w]{4,50}$";
    public static final String INITIALS_RU_PATTERN = "^[а-яА-Яё-]{1,30}$";
    public static final String MIDDLE_NAME_JSP_PATTERN = "^$|^[а-яА-Яё]{1,30}$";
    public static final String MOBILE_PHONE_PATTERN = "^\\+375\\d{9}$";
    public static final String SUM_PATTERN = "^[1-9]\\d{0,2}\\.\\d{0,2}$|^[1-9]\\d{0,2}$";
    public static final String TARIFF_NAME_PATTERN = "^[^a-zA-Z]{1,100}$";
    public static final String DESCRIPTION_PATTERN = "^[^a-zA-Z]{1,500}$";
    public static final String FEATURE_PATTERN = "^[^a-zA-Z]{1,150}$";

    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 50;
    private static final int MAX_EMAIL_LENGTH = 100;

    public static void validateEmail(String email) throws ServiceDataException {
        if(email == null || !email.matches(EMAIL_PATTERN) || email.length() > MAX_EMAIL_LENGTH){
            throw new ServiceDataException("Invalid email value - " + email);
        }
    }

    public static void validatePassword(byte[] password) throws ServiceDataException {
        if(password == null || password.length < MIN_PASSWORD_LENGTH || password.length > MAX_PASSWORD_LENGTH){
            PasswordManager.dispose(password);
            throw new ServiceDataException("Invalid password. It have not enough length.");
        }
    }

    public static void validateInitials(String initials) throws ServiceDataException {
        if(initials == null || !initials.matches(INITIALS_RU_PATTERN)){
            throw new ServiceDataException("Invalid surname value - " + initials);
        }
    }

    public static void validateLastName(String lastName) throws ServiceDataException {
        if(lastName != null && !lastName.matches(INITIALS_RU_PATTERN)){
            throw new ServiceDataException("Invalid middle name value - " + lastName);
        }
    }

    public static void validateTariffName(String name) throws ServiceDataException {
        if(name == null || !name.matches(TARIFF_NAME_PATTERN)){
            throw new ServiceDataException("Invalid tariff name value - " + name);
        }
    }
    public static void validateFeature(String feature) throws ServiceDataException {
        if(feature == null || !feature.matches(FEATURE_PATTERN)){
            throw new ServiceDataException("Invalid feature value - " + feature);
        }
    }

    public static void validateDescription(String description) throws ServiceDataException {
        if(description == null || !description.matches(DESCRIPTION_PATTERN)){
            throw new ServiceDataException("Invalid description value - " + description);
        }
    }

    public static void validateMobilePhone(String phoneNumber) throws ServiceDataException {
        if(phoneNumber == null || !phoneNumber.matches(MOBILE_PHONE_PATTERN)){
            throw new ServiceDataException("Invalid mobile phone - " + phoneNumber);
        }
    }

    public static void validateSum(String sum) throws ServiceDataException {
        if(sum == null || !sum.matches(SUM_PATTERN)){
            throw new ServiceDataException("Invalid sum value - " + sum);
        }
    }

    public static int validateAndGetId(String id) throws ServiceDataException {
        if(id == null){
            throw new ServiceDataException("Input id - null. Invalid id value.");
        }
        try {
            return Integer.parseInt(id);
        } catch(NumberFormatException e){
            throw new ServiceDataException("Invalid id value " + id);
        }
    }

    public static int checkAndGetNumber(String number, int defaultValue){
        if(number == null){
            return defaultValue;
        }
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e){
            return defaultValue;
        }
    }
}