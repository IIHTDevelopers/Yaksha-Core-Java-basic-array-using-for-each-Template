package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.DoStmt;

public class AutoGrader {

    // Test if the code uses proper array manipulation techniques and correct logic
    public boolean testArrayOperationsOnly(String filePath) throws IOException {
        System.out.println("Starting testArrayOperationsOnly with file: " + filePath);

        File participantFile = new File(filePath); // Path to participant's file
        if (!participantFile.exists()) {
            System.out.println("File does not exist at path: " + filePath);
            return false;
        }

        FileInputStream fileInputStream = new FileInputStream(participantFile);
        JavaParser javaParser = new JavaParser();
        CompilationUnit cu;
        try {
            cu = javaParser.parse(fileInputStream).getResult()
                    .orElseThrow(() -> new IOException("Failed to parse the Java file"));
        } catch (IOException e) {
            System.out.println("Error parsing the file: " + e.getMessage());
            throw e;
        }

        System.out.println("Parsed the Java file successfully.");

        boolean hasArrayOperations = false;

        // Check for array length (not as a method call, but as a field)
        System.out.println("------ Array Length ------");
        for (FieldAccessExpr fieldAccess : cu.findAll(FieldAccessExpr.class)) {
            if (fieldAccess.getNameAsString().equals("length")) {
                System.out.println("Array length accessed: " + fieldAccess);
                hasArrayOperations = true;
            }
        }

        // Check for traditional array iteration using for-each loop
        System.out.println("------ Array Iteration ------");
        for (ForStmt forStmt : cu.findAll(ForStmt.class)) {
            // Check if the loop is a for-each loop
            if (forStmt.getInitialization().toString().contains("int[]") && forStmt.getUpdate().toString().isEmpty()) {
                System.out.println("Array iteration found using for-each loop: " + forStmt);
                hasArrayOperations = true;
            }
        }

        // Check for while and do-while loops (though not typically used for-each)
        for (WhileStmt whileStmt : cu.findAll(WhileStmt.class)) {
            if (whileStmt.getCondition().toString().contains("length")) {
                System.out.println("Array iteration found using while loop: " + whileStmt);
                hasArrayOperations = true;
            }
        }

        for (DoStmt doStmt : cu.findAll(DoStmt.class)) {
            if (doStmt.getCondition().toString().contains("length")) {
                System.out.println("Array iteration found using do-while loop: " + doStmt);
                hasArrayOperations = true;
            }
        }

        // Check if continue is used for array iteration (in a loop with arrays)
        System.out.println("------ Array Continue Statements ------");
        for (ExpressionStmt stmt : cu.findAll(ExpressionStmt.class)) {
            if (stmt.getExpression() instanceof MethodCallExpr) {
                MethodCallExpr methodCall = (MethodCallExpr) stmt.getExpression();
                if (methodCall.getNameAsString().equals("continue")) {
                    System.out.println("Array-related 'continue' statement found: " + methodCall);
                    hasArrayOperations = true;
                }
            }
        }

        // Return whether array-related operations are found
        System.out.println("Has correct array operations: " + hasArrayOperations);

        boolean result = hasArrayOperations;
        System.out.println("Test result: " + result);

        return result;
    }
}
