import java.util.*;
import java.util.function.BiFunction;

/**
 * CHALLENGE 2: Ledger Reconciliation  (Medium, 30 min)
 *
 * Interviewer: "At the end of the day we compare our internal ledger with the bank statement.
 * I need every ledger transaction that does NOT appear in the bank statement, so the
 * operations team can investigate it."
 *
 * INPUT : List<Transaction> ledger  (n items)   - our internal records
 *         List<Transaction> bank    (m items)   - the bank statement
 *         0 <= n, m <= 200,000. Ids are unique inside each list. Lists are NOT sorted.
 * OUTPUT: the ledger transactions whose id is not present in the bank list,
 *         in the same order they have in the ledger.
 *
 * EXAMPLE
 *   ledger ids: [T-1001, T-1002, T-1003, T-1004, T-1005]
 *   bank   ids: [T-1001, T-1003, T-1005, T-1009]
 *   result    : [T-1002, T-1004]      (T-1009 is only in the bank: ignore it here)
 *
 * RULES:
 *   1. Clarify first: ask at least 2 questions (what defines "same" transaction? amount too?).
 *   2. Implement missingInBankBruteForce FIRST and write its complexity in terms of n and m.
 *   3. Then implement missingInBankOptimized, with time and space complexity.
 *   4. Run:  java Challenge02LedgerReconciliation.java
 *
 * Follow-up the interviewer will ask: "Now I also want the bank transactions missing in the
 * ledger. How do you reuse your solution?"
 *
 * Glosario: ledger = libro mayor interno, bank statement = estado de cuenta del banco.
 */
public class Challenge02LedgerReconciliation {

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

    /** Time: ?    Space: ? */
    static List<Transaction> missingInBankBruteForce(List<Transaction> ledger, List<Transaction> bank) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Time: ?    Space: ? */
    static List<Transaction> missingInBankOptimized(List<Transaction> ledger, List<Transaction> bank) {
        throw new UnsupportedOperationException("TODO");
    }

    // ------------------------------------------------------------ TESTS (do not edit)

    static List<Transaction> sample(String... ids) {
        List<Transaction> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(new Transaction(ids[i], "A-0" + (i % 3 + 1), 1000L + i));
        }
        return list;
    }

    static List<String> idsOf(List<Transaction> list) {
        List<String> ids = new ArrayList<>();
        for (Transaction t : list) ids.add(t.id);
        return ids;
    }

    static int failures = 0;

    static void check(String label, BiFunction<List<Transaction>, List<Transaction>, List<Transaction>> method,
                      List<Transaction> ledger, List<Transaction> bank, String... expectedIds) {
        try {
            List<String> actual = idsOf(method.apply(ledger, bank));
            List<String> expected = Arrays.asList(expectedIds);
            if (actual.equals(expected)) {
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

    static void runAll(String name, BiFunction<List<Transaction>, List<Transaction>, List<Transaction>> method) {
        System.out.println(name);
        check("example from the statement", method,
                sample("T-1001", "T-1002", "T-1003", "T-1004", "T-1005"),
                sample("T-1001", "T-1003", "T-1005", "T-1009"),
                "T-1002", "T-1004");
        check("everything matches", method,
                sample("T-1", "T-2", "T-3"), sample("T-3", "T-2", "T-1"));
        check("bank is empty", method,
                sample("T-1", "T-2"), new ArrayList<>(), "T-1", "T-2");
        check("ledger is empty", method,
                new ArrayList<>(), sample("T-1", "T-2"));
        check("nothing matches", method,
                sample("T-1", "T-2"), sample("T-8", "T-9"), "T-1", "T-2");
        check("keeps ledger order", method,
                sample("T-5", "T-3", "T-4", "T-1"), sample("T-4"), "T-5", "T-3", "T-1");
    }

    static void timing(String name, BiFunction<List<Transaction>, List<Transaction>, List<Transaction>> method, int n) {
        String[] a = new String[n];
        String[] b = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = "T-" + i;
            b[i] = "T-" + (i + 5);   // 5 ledger items are missing in the bank
        }
        try {
            long start = System.nanoTime();
            List<Transaction> result = method.apply(sample(a), sample(b));
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("  " + name + " with n=m=" + n + ": " + ms + " ms (missing found: " + result.size() + ", expected 5)");
        } catch (RuntimeException e) {
            System.out.println("  " + name + " timing skipped (not implemented yet)");
        }
    }

    public static void main(String[] args) {
        runAll("== Brute force ==", Challenge02LedgerReconciliation::missingInBankBruteForce);
        runAll("== Optimized ==", Challenge02LedgerReconciliation::missingInBankOptimized);
        System.out.println("== Timing (compare the two!) ==");
        timing("brute force", Challenge02LedgerReconciliation::missingInBankBruteForce, 10_000);
        timing("optimized  ", Challenge02LedgerReconciliation::missingInBankOptimized, 200_000);
        System.out.println(failures == 0 ? "ALL GOOD" : failures + " check(s) not passing yet");
    }
}
