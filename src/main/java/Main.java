import com.validation.AnnotationValidation;
import com.validation.Test;
import com.validation.Test2;
import com.validation.Validation;
import com.validation.annotations.NotEmpty;
import com.validation.exceptions.ResponseException;

public class Main {
    public static void main(String[] args) {
        Validation validation = Validation.getValidationInstance();
        validation.setValidationStrategy(new AnnotationValidation());
        Test test = new Test();
        Test2 test2 = new Test2();
        ResponseException err = validation.validate(test);

    }
}
