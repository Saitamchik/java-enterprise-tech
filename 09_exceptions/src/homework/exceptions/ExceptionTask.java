package homework.exceptions;

import java.util.Optional;

public class ExceptionTask {

    public static Optional<String> mergeStrings(String first, String second) {
        if (first == null) {
            first = "";
        }

        if (second == null) {
            second = "";
        }

        if (first.isEmpty() && second.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(first.length() > second.length() ? first + second : second + first);
    }

    public static long getPower(int n, int p) throws Exception {
        if (n < 0 || p < 0 || n == 0 || p == 0) {
            return -1L;
        } else {
            return (long) (Math.pow(n, p));
        }
    }

    public static void customException(int a) {
        if (a == 0) {
            throw new InvalidZeroParameterException();
        }
        try {
            throwCheckedException();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void exceptionProcessing() throws Exception {
        try {
            throwCheckedException();
            throwUncheckedException();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void throwCheckedException() throws Exception {
        throw new Exception("Checked exception");
    }

    private static void throwUncheckedException() {
        throw new RuntimeException("Unchecked exception");
    }
}
