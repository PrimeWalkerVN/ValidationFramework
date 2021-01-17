import com.validation.*;
import com.validation.annotations.NotEmpty;
import com.validation.builder.ValidationBuilder;
import com.validation.customs.PrimeNumberValidation;
import com.validation.exceptions.ResponseException;
import com.validation.exceptions.ValidatorException;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Validation validation = Validation.getValidationInstance();
        //=========================Test annotation====================
        System.out.println("=========================Test annotation====================");
        validation.setValidationStrategy(new AnnotationValidation());
        Test test = new Test();
        ResponseException err = validation.validate(test);
        System.out.println("Test has error : " + err.hasError());
        System.out.println("Test all error :");
        Map<String, List<ValidatorException>> errors = err.getAllErrors();
        for (String key : errors.keySet())
        {
            for (ValidatorException ve : errors.get(key)) {
                System.out.println("\tError : " + ve.getMessage());
            }
        }
        List<String> errorOfRegex = err.getErrorInString("regex");

        System.out.println("Error of regex");
        for (String str : errorOfRegex) {
            System.out.println("\tError : " + str);
        }

        //=========================Test builder====================
        System.out.println("=========================Test builder====================");
        ObjectValidation objectValidation = new ValidationBuilder().dateFormat("DD//MM/YY").build();
        String a = "122/12/2020";
        validation.setValidationStrategy(objectValidation);
        ResponseException res = validation.validate(a);
        List<String> errs = res.getErrorBuilderInString();

        errs.forEach(e -> {
            System.out.println("Error Builder : " + e);
        });

        //=========================Test custom builder====================
        System.out.println("=========================Test builder====================");
        ObjectValidation testCustom = new ValidationBuilder().custom(new PrimeNumberValidation()).build();
        validation.setValidationStrategy(testCustom);
        ResponseException res2 = validation.validate(9);

        List<String> errField2 = res2.getErrorBuilderInString();

        errField2.forEach(err2 -> {
            System.out.println("Error Builder custom : " + err2);
        });
    }

}
