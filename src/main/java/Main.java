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
        validation.setValidationStrategy(new AnnotationValidation());
        Test test = new Test();
        Test2 test2 = new Test2();
        Test3 test3 = new Test3();
        ResponseException err = validation.validate(test);

        System.out.println("Test1 has error : " + err.hasError());

        System.out.println("Test1 all error :");
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

        // builder
        ObjectValidation objectValidation = new ValidationBuilder().maxLength(5)
                .minLength(1).notEmpty().build();
        String a = "";
        validation.setValidationStrategy(objectValidation);
        ResponseException res = validation.validate(a);

        List<String> errField = res.getErrorBuilderInString();

        errField.forEach(e -> {
            System.out.println("ErrorBuilder : " + e);
        });

        ObjectValidation testCustom = new ValidationBuilder().custom(new PrimeNumberValidation()).build();
        validation.setValidationStrategy(testCustom);
        ResponseException res2 = validation.validate(9);

        List<String> errField2 = res2.getErrorBuilderInString();

        errField2.forEach(er -> {
            System.out.println("ErrorBuilder : " + er);
        });

    }

}
