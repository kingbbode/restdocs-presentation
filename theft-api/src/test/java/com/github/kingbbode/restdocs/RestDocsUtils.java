package com.github.kingbbode.restdocs;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.snippet.Attributes.Attribute;
import static org.springframework.restdocs.snippet.Attributes.key;

public class RestDocsUtils {
    public static OperationRequestPreprocessor preprocessOrderRequest() {
        return preprocessRequest(
                modifyUris().scheme("https").host("kingbbode.com").removePort(),
                prettyPrint()
        );
    }

    public static OperationResponsePreprocessor preprocessOrderResponse() {
        return preprocessResponse(prettyPrint());
    }

    /*
        REQUIRED : 필수 값 여부
     */

    public static Attribute required() {
        return key("required").value("true");
    }

    public static Attribute optional() {
        return key("required").value("false");
    }

    /*
        DEFAULT VALUE : 기본 값
     */
    public static Attribute defaultValue(String defaultValue) {
        return key("defaultValue").value(defaultValue);
    }

    public static Attribute emptyDefaultValue() {
        return key("defaultValue").value("");
    }

    /*
        FORMAT : 형태
     */

    public static Attribute customFormat(String custom) {
        return key("format").value(custom);
    }

    public static Attribute emptyFormat() {
        return key("format").value("");
    }

    public static Attribute dateTimeFormat() {
        return key("format").value("YYYY-MM-DD HH:MM:SS");
    }
    public static Attribute dateFormat() {
        return key("format").value("YYYY-MM-DD");
    }

    public static <T extends Enum<T>> Attribute enumFormat(Class<T> enums){
        return key("format").value(Arrays.stream(enums.getEnumConstants()).map(Enum::name).collect(Collectors.joining("\n")));
    }

    public static <T extends Enum<T>> Attribute enumFormat(Collection<T> enums){
        return key("format").value(enums.stream().map(Enum::name).collect(Collectors.joining("\n")));
    }

    public static <T extends Enum<T>> Attribute enumFormat(T[] enums, Function<T, String> detailFcuntion){
        return key("format").value(Arrays.stream(enums).collect(Collectors.toList()).stream().map(en -> en.name() + "(" + detailFcuntion.apply(en) + ")").collect(Collectors.joining("\n")));
    }

    public static <T extends Enum<T>> Attribute enumFormat(Collection<T> enums, Function<T, String> detailFcuntion){
        return key("format").value(enums.stream().map(en -> en.name() + "(" + detailFcuntion.apply(en) + ")").collect(Collectors.joining("\n")));
    }
}