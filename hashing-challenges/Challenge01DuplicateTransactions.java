import java.util.*;
import java.util.function.Function;

/**
 * CHALLENGE 1: Duplicate Transactions (Easy, 25 min)
 *
 * Interviewer: "A payments service receives a batch of transactions. Because
 * the message
 * queue is at-least-once, the same transaction id can arrive twice. Write a
 * method that tells
 * me whether a batch contains any duplicated transaction id."
 *
 * INPUT : List<Transaction> (n transactions, 0 <= n <= 200,000)
 * OUTPUT: true if two or more transactions share the same id, false otherwise.
 *
 * EXAMPLE 1: ids [T-1001, T-1002, T-1003, T-1004, T-1005] -> false
 * EXAMPLE 2: ids [T-1001, T-1002, T-1003, T-1002, T-1005] -> true (T-1002
 * repeats)
 *
 * RULES (say them out loud, like in the interview):
 * 1. Clarify first: ask at least 2 questions before coding (order? null ids?
 * case sensitive?).
 * 2. Implement hasDuplicateBruteForce FIRST. State its time complexity in a
 * comment.
 * 3. Then implement hasDuplicateOptimized. State time and space complexity in a
 * comment.
 * 4. Run: java Challenge01DuplicateTransactions.java
 *
 * Follow-up the interviewer will ask: "What if the ids do not fit in memory?"
 *
 * Glosario: batch = lote, duplicated = repetido, brute force = fuerza bruta.
 */
public class Challenge01DuplicateTransactions {

    static final class Transaction {
        final String id;
        final String accountId;
        final long amountCents;

        Transaction(String id, String accountId, long amountCents) {
            this.id = id;
            this.accountId = accountId;
            this.amountCents = amountCents;
        }

        @Override
        public String toString() {
            return "Transaction[" + id + "]";
        }
    }

    // ---------------------------------------------------------------- YOUR CODE

    /** Time: ? Space: ? */
    static boolean hasDuplicateBruteForce(List<Transaction> transactions) {
        if (transactions.size() == 1) {
            return false;
        }
        for (int i = 0; i < transactions.size(); i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if( transactions.get(i).id.equals(transactions.get(j).id) ){
                    return true;
                }
            }
        }
        return false;
    }

    /** Time: ? Space: ? */
    static boolean hasDuplicateOptimized(List<Transaction> transactions) {
        HashSet<String> transactionsSet = new HashSet<>();
        
        for (Transaction trx : transactions) {
            if (transactionsSet.contains(trx.id)) {
                return true;
            } else {
                transactionsSet.add(trx.id);
            }
        }
        return false;
    }

    // ------------------------------------------------------------ TESTS (do not
    // edit)

    static List<Transaction> sample(String... ids) {
        List<Transaction> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(new Transaction(ids[i], "A-0" + (i % 3 + 1), 1000L + i));
        }
        return list;
    }

    static int failures = 0;

    static void check(String label, Function<List<Transaction>, Boolean> method,
            List<Transaction> input, boolean expected) {
        try {
            boolean actual = method.apply(input);
            if (actual == expected) {
                System.out.println("  PASS  " + label);
            } else {
                failures++;
                System.out.println("  FAIL  " + label + "  expected=" + expected + " actual=" + actual);
            }
        } catch (UnsupportedOperationException e) {
            failures++;
            System.out.println("  TODO  " + label);
        } catch (RuntimeException e) {
            failures++;
            System.out.println("  ERROR " + label + "  " + e);
        }
    }

    static void runAll(String name, Function<List<Transaction>, Boolean> method) {
        System.out.println(name);
        check("no duplicates (5 records)", method, sample("T-1001", "T-1002", "T-1003", "T-1004", "T-1005"), false);
        check("one duplicate (5 records)", method, sample("T-1001", "T-1002", "T-1003", "T-1002", "T-1005"), true);
        check("duplicate at the end", method, sample("T-1", "T-2", "T-3", "T-1"), true);
        check("all the same", method, sample("T-9", "T-9", "T-9"), true);
        check("single element", method, sample("T-1"), false);
        check("empty list", method, new ArrayList<>(), false);
        check("ids are case sensitive", method, sample("t-1", "T-1"), false);
    }

    static void timing(String name, Function<List<Transaction>, Boolean> method, int n) {
        String[] ids = new String[n];
        for (int i = 0; i < n; i++)
            ids[i] = "T-" + i;
        List<Transaction> input = sample(ids);
        try {
            long start = System.nanoTime();
            method.apply(input);
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("  " + name + " with n=" + n + " (no duplicates): " + ms + " ms");
        } catch (RuntimeException e) {
            System.out.println("  " + name + " timing skipped (not implemented yet)");
        }
    }

    public static void main(String[] args) {
        runAll("== Brute force ==", Challenge01DuplicateTransactions::hasDuplicateBruteForce);
        runAll("== Optimized ==", Challenge01DuplicateTransactions::hasDuplicateOptimized);
        System.out.println("== Timing (compare the two!) ==");
        timing("brute force", Challenge01DuplicateTransactions::hasDuplicateBruteForce, 10_000);
        timing("optimized  ", Challenge01DuplicateTransactions::hasDuplicateOptimized, 200_000);
        System.out.println(failures == 0 ? "ALL GOOD" : failures + " check(s) not passing yet");
    }
}
