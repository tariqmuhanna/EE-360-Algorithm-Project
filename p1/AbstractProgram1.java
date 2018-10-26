package p1;

public abstract class AbstractProgram1 {
    public abstract boolean isStableMatching(Matching marriage);

    /**
     * Brute force solution to the Stable Marriage problem. Relies on the function
     * isStableMatching(p1.Matching) to determine whether a candidate p1.Matching is stable.
     *
     * @return A stable p1.Matching.
     */
    public Matching stableMarriageBruteForce(Matching marriage) {
        int n = marriage.getResidentCount();
        int slots = marriage.totalHospitalSlots();

        Permutation p = new Permutation(n, slots);
        Matching matching;
        while ((matching = p.getNextMatching(marriage)) != null) {
            if (isStableMatching(matching)) {
                return matching;
            }
        }

        return new Matching(marriage);
    }

    public abstract Matching stableMarriageBruteForce_residentoptimal(Matching marriage);

    public abstract Matching stableMarriageGaleShapley_hospitaloptimal(Matching marriage);

    public abstract Matching stableMarriageGaleShapley_residentoptimal(Matching marriage);
}
