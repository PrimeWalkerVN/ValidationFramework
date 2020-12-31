import com.validation.Test;
import com.validation.Validation;
import com.validation.annotations.NotEmpty;

public class Main {
    public static void main(String[] args) {
        Validation validation = Validation.getInstance();
        Test test = new Test();
        validation.validate(test);

    }

}
