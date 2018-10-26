package p1;
/*
 * Name: Tariq Muhanna
 * EID: tim278
 */

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Your solution goes in this class.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 *
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */

class Spots{
    public int[] length;
    public int[] curr_length;
    public int[] position;
    public int[][] free;
    public int[] worst_res;

    Spots(Matching marriage){
        length = new int[marriage.getHospitalCount()];
        position = new int[marriage.getHospitalCount()];
        curr_length = new int[marriage.getHospitalCount()];
        worst_res = new int[marriage.getHospitalCount()];
        free = new int[marriage.getHospitalCount()][];
        for (int i=0; i< marriage.getHospitalCount(); i++){
            free[i] = new int[marriage.getHospitalSlots().get(i)];
            for (int j=0; j < marriage.getHospitalSlots().get(i); j++){
                free[i][j] = -1;
            }
            length[i] = marriage.getHospitalSlots().get(i);
            position[i] = 0;
            curr_length[i] = 0;
            worst_res[i] = -1;
        }
    }

}



public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate p1.Matching represents a solution to the Stable Marriage problem.
     * Study the description of a p1.Matching in the project documentation to help you with this.
     */
    @Override
    public boolean isStableMatching(Matching marriage) {
        /* TODO implement this function */
        int free_slots = marriage.totalHospitalSlots();

        int sum = 0;
        for (int i = 0; i < marriage.getResidentCount(); i++) {
            if (marriage.getResidentMatching().get(i) != -1)
                sum++;
        }
        if (sum != free_slots)
            return false;
        if (marriage.getResidentCount() < free_slots)
            return false;

        int preferred_res = -1;
        while (free_slots>0) {
            for (int resident = 0; resident < marriage.getResidentCount(); resident++) {//s
                if (marriage.getResidentMatching().get(resident) == -1) // checks if resident is unmatched
                    continue;
                free_slots--;
                int hospital = marriage.getResidentMatching().get(resident);//h
                for (int i = 0; i < marriage.getResidentCount(); i++) {
                    if (marriage.getHospitalPreference().get(hospital).indexOf(i) < marriage.getHospitalPreference().get(hospital).indexOf(resident)) { // goes through all of h s' > s list
                        preferred_res = i;//s'
                        if (marriage.getResidentMatching().get(preferred_res) == hospital) { //s' is pair to h (slots), so good
                            continue;
                        } else {
                            int preferred_hos = marriage.getResidentMatching().get(preferred_res);//h'
                            if (preferred_hos == -1)
                                return false;
                            if (preferred_hos == hospital)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }


//        int slots = marriage.totalHospitalSlots();
//        //ArrayList<Integer> hospital_slots = marriage.getHospitalSlots();
//        Spots match = new Spots(marriage);
//        for (int resident=0; resident < marriage.getResidentCount(); resident++) {
//
//            if (marriage.getResidentMatching().get(resident) == -1) // checks if resident is unmatched
//                continue;
//            int hospital = marriage.getResidentMatching().get(resident); // Current matched hospital
//            ArrayList<Integer> hospitalPrefs = marriage.getHospitalPreference().get(hospital); //hositpal's prefs
//            if (marriage.totalHospitalSlots() == marriage.getHospitalCount()) {
//                if (hospitalPrefs.get(0) == resident & (match.length[hospital] > 0)) { // checks for perfect match
////                match.free[hospital][match.position[hospital]] = resident;
//                    match.position[hospital]++;
//                    match.curr_length[hospital]++;
//                    match.length[hospital]--;
//                    slots -= 1;
//                    if (slots == 0)
//                        slots += 0;
//                    continue;
//                }
//            }
//            else {
//                if (hospitalPrefs.get(0) == resident | (match.length[hospital] > 0)) { // checks for perfect match
////                match.free[hospital][match.position[hospital]] = resident;
//                    match.position[hospital]++;
//                    match.curr_length[hospital]++;
//                    match.length[hospital]--;
//                    slots -= 1;
//                    if (slots == 0)
//                        slots += 0;
//                    continue;
//                }
//            }
////            else if (match.length[hospital] > 0) { // checks for perfect match
//////                match.free[hospital][match.position[hospital]] = resident;
////                match.position[hospital]++;
////                match.curr_length[hospital]++;
////                match.length[hospital]--;
////                slots -= 1;
////                if (slots > 0)
//////                    slots += 0;
////                    continue;
////            }
//            for (int resident_num : hospitalPrefs) {
//                if (resident_num == resident)
//                    continue;
//                if (marriage.getResidentMatching().get(resident_num) == -1) // case 1 instability
//                    return false;
//                if (marriage.getResidentMatching().get(resident_num) == hospital)
//                    continue;
//                if (hospitalPrefs.indexOf(resident_num) < hospitalPrefs.indexOf(resident)) { //Hospital prefers this resident more
//                    ArrayList<Integer> residentPrefs = marriage.getResidentPreference().get(resident_num); //resident's prefs
//
//                    int hos_slots = marriage.getHospitalSlots().get(hospital);
//                    for (int hospital_num : residentPrefs) {
//                        if (residentPrefs.indexOf(hospital_num) == residentPrefs.indexOf(hospital)) // case 2 instability
//                            continue;
//                        if (residentPrefs.indexOf(hospital) < residentPrefs.indexOf(hospital_num))
//                            return false;
////                                hos_slots--;
////                        if (residentPrefs.indexOf(hospital_num) > residentPrefs.indexOf(hospital)) // case 2 instability
////                            continue;
////                            if (hos_slots <= 0)
////                                return false;
////                        else if (residentPrefs.indexOf(hospital_num) == residentPrefs.indexOf(hospital)) { // True
////                            return false;
////                        }
//                    }
//                }
//            }
////            if (match.length[hospital] > 0) { // checks for perfect match
//////                match.free[hospital][match.position[hospital]] = resident;
////                match.position[hospital]++;
////                match.curr_length[hospital]++;
////                match.length[hospital]--;
////                slots -= 1;
////                if (slots == 0)
////                    slots += 0;
//////                continue;
////            }
//        }
//        if (slots == 0)
//            return true;
//        return false;
//    }


    /**
     * Determines a resident optimal solution to the Stable Marriage problem from the given input set.
     * Study the project description to understand the variables which represent the input to your solution.
     *
     * @return A stable p1.Matching.
     */
    @Override
    public Matching stableMarriageBruteForce_residentoptimal(Matching marriage) {
        /* TODO implement this function */

        ArrayList<Integer> valMatches = new ArrayList<>();
        int sum, hosp, indOfHosp;

        ArrayList<Matching> stableMatchings = new ArrayList<>();
        int n = marriage.getResidentCount();
        int slots = marriage.totalHospitalSlots();

        Permutation p = new Permutation(n, slots);
        Matching matching;
        while ((matching = p.getNextMatching(marriage)) != null) {
            if (isStableMatching(matching)) {
                stableMatchings.add(matching);
            }
        }

        for (Matching match : stableMatchings) {
            sum = 0;
            for (int i = 0; i < n; i++) {
                hosp = match.getResidentMatching().get(i);
                if (hosp != -1) {
                    indOfHosp = match.getResidentPreference().get(i).indexOf(hosp);
                    sum += indOfHosp;
                }
            }
            valMatches.add(sum);
        }

        int most_stable = valMatches.indexOf(Collections.min(valMatches));
        return stableMatchings.get(most_stable);
    }


    /**
     * Determines a resident optimal solution to the Stable Marriage problem from the given input set.
     * Study the description to understand the variables which represent the input to your solution.
     *
     * @return A stable p1.Matching.
     */

    @Override
    public Matching stableMarriageGaleShapley_residentoptimal(Matching marriage) {
        /* TODO implement this function */
        boolean result = false;
        int slots = marriage.totalHospitalSlots();
        int HosCount = marriage.getHospitalCount();
        int freeCount = marriage.totalHospitalSlots();
        int resCount = marriage.getResidentCount();
        boolean resFree[] = new boolean[resCount];
        int resHosPair[] = new int[freeCount];

        Spots match = new Spots(marriage);
        ArrayList<Integer> res_list = new ArrayList<Integer>();
        for (int i=0; i< resCount;i++){
            res_list.add(i);
        }
        ArrayList<Integer> empty = new ArrayList<Integer>();
        for (int i=0; i<resCount; i++){
            empty.add(-1);
        }
        marriage.setResidentMatching(empty);

        for (int i=0; i < marriage.totalHospitalSlots(); i++){
            resHosPair[i] = -1;
        }

        ArrayList<HashMap<Integer, Integer>> hos_res_pref_Map = new ArrayList<>();
        for (int i=0; i< HosCount;i++) {
            hos_res_pref_Map.add(new HashMap<Integer, Integer>());
            int j = 0;
            for (Integer x : marriage.getHospitalPreference().get(i)) {
                hos_res_pref_Map.get(i).put(x, j++);
            }
        }

        ArrayList<HashMap<Integer, Integer>> res_hos_pref_Map = new ArrayList<>();
        for (int i=0; i< resCount;i++) {
            res_hos_pref_Map.add(new HashMap<Integer, Integer>());
            int j = 0;
            for (Integer x : marriage.getResidentPreference().get(i)) {
                res_hos_pref_Map.get(i).put(x, j++);
            }
        }

        int resident;
        for (resident = 0; resident < res_list.size(); resident++) {

            for (int i = 0; i < marriage.getHospitalCount(); i++) {
                int hospital = marriage.getResidentPreference().get(res_list.get(resident)).get(i);


                if (match.length[hospital] > 1) {
                    match.free[hospital][match.position[hospital]] = res_list.get(resident);
                    match.position[hospital]++;
                    match.curr_length[hospital]++;
                    match.length[hospital]--;
                    freeCount--;
                    break;
                } else if (match.length[hospital] == 1) {
                    match.free[hospital][match.position[hospital]] = res_list.get(resident);
                    match.position[hospital]++;
                    match.curr_length[hospital]++;
                    match.length[hospital]--;
                    freeCount--;
                    break;
                }
                else {
                    int lowest_pref = -1;
                    int patient_num = -1;
                    int currRes1 = match.free[hospital][match.curr_length[hospital]-1];
                    int currRes;
                    for (int patients = 0; patients < match.curr_length[hospital]; patients++) {
                        currRes = match.free[hospital][patients];
                        if (currRes == -1)
                            continue;

                        if (hos_res_pref_Map.get(hospital).get(res_list.get(resident)) < hos_res_pref_Map.get(hospital).get(currRes)) {
                            if (lowest_pref == -1){
                                lowest_pref = currRes;
                                patient_num = patients;
                            }
                            else {
                                lowest_pref = currRes;
                                patient_num = patients;
                            }
                        }
                    }
                    if (lowest_pref != -1) {
                        match.free[hospital][patient_num] = res_list.get(resident);
                        res_list.add(lowest_pref);
                        break;
                    }
                }
            }
        }


        int found = 0;
        for (int i=0; i<resCount; i++){
            for (int j=0; j<marriage.getHospitalCount(); j++){
                List<Integer> list = Arrays.stream(match.free[j]).boxed().collect(Collectors.toList());
                if (list.contains(i)) {
                    found = 1;
                    marriage.getResidentMatching().set(i, j);
                }
            }
            if (found == 0){
                marriage.getResidentMatching().set(i, -1);
            }
            found = 0;
        }

        return marriage;
    }

    /**
     * Determines a hospital optimal solution to the Stable Marriage problem from the given input set.
     * Study the description to understand the variables which represent the input to your solution.
     *
     * @return A stable p1.Matching.
     */

    @Override
    public Matching stableMarriageGaleShapley_hospitaloptimal(Matching marriage) {

        /* TODO implement this function */
        boolean result = false;
        int slots = marriage.totalHospitalSlots();
        int freeCount = marriage.totalHospitalSlots();
        int HosCount = marriage.getHospitalCount();
        int resCount = marriage.getResidentCount();
        boolean res_taken[] = new boolean[resCount];
        int resHosPair[] = new int[marriage.getResidentCount()];

        Spots match = new Spots(marriage);
        ArrayList<Integer> res_list = new ArrayList<Integer>();
        ArrayList<Integer> hos_list = new ArrayList<Integer>();
        ArrayList<Integer> ind_res_list = new ArrayList<Integer>();

        for (int i=0; i< resCount;i++){
            res_list.add(i);
            ind_res_list.add(-1);
        }
        for (int i=0; i< HosCount;i++){
            hos_list.add(i);
        }
        ArrayList<Integer> empty = new ArrayList<Integer>();
        for (int i=0; i<resCount; i++){
            empty.add(-1);
        }
        marriage.setResidentMatching(empty);

        for (int i=0; i < marriage.totalHospitalSlots(); i++){
            resHosPair[i] = -1;
        }

        ArrayList<HashMap<Integer, Integer>> hos_res_pref_Map = new ArrayList<>();
        for (int i=0; i< HosCount;i++) {
            hos_res_pref_Map.add(new HashMap<Integer, Integer>());
            int j = 0;
            for (Integer x : marriage.getHospitalPreference().get(i)) {
                hos_res_pref_Map.get(i).put(x, j++);
            }
        }

        ArrayList<HashMap<Integer, Integer>> res_hos_pref_Map = new ArrayList<>();
        for (int i=0; i< resCount;i++) {
            res_hos_pref_Map.add(new HashMap<Integer, Integer>());
            int j = 0;
            for (Integer x : marriage.getResidentPreference().get(i)) {
                res_hos_pref_Map.get(i).put(x, j++);
            }
        }



        int newHos;
        for (newHos = 0; newHos < hos_list.size(); newHos++) {

            for (int i = 0; i < res_list.size(); i++) {
                int resident = marriage.getHospitalPreference().get(hos_list.get(newHos)).get(res_list.get(i));
                if (res_taken[resident] == true) {
                    int curr_lowest_Res;
                    int hospital = resHosPair[res_list.get(resident)];
//                    if (marriage.getResidentPreference().get(resident).indexOf(hos_list.get(newHos)) < (marriage.getResidentPreference().get(resident).indexOf(hospital))){
                    if (res_hos_pref_Map.get(res_list.get(resident)).get(newHos) < res_hos_pref_Map.get(res_list.get(resident)).get(newHos)){
                        if (match.length[hos_list.get(newHos)]>0) {

                            match.free[hospital][ind_res_list.get(res_list.get(resident))] = match.free[hospital][match.curr_length[hospital]-1];
                            ind_res_list.set(match.free[hospital][match.curr_length[hospital]-1],ind_res_list.get(res_list.get(resident)));
                            match.free[hospital][match.curr_length[hospital]-1] = -1;
                            match.position[hospital]--;
                            match.curr_length[hospital]--;
                            match.length[hospital]++;
                            hos_list.add(hospital);

                            match.free[hos_list.get(newHos)][match.position[hos_list.get(newHos)]] = res_list.get(resident);
                            ind_res_list.set(res_list.get(resident), match.position[hos_list.get(newHos)]);
                            match.position[hos_list.get(newHos)]++;
                            match.curr_length[hos_list.get(newHos)]++;
                            match.length[hos_list.get(newHos)]--;
                            resHosPair[res_list.get(resident)] = hos_list.get(newHos);
                        }
                        else {
                            curr_lowest_Res = match.free[hos_list.get(newHos)][match.curr_length[hos_list.get(newHos)] - 1];
//                            if (marriage.getHospitalPreference().get(hos_list.get(newHos)).indexOf(resident) < (marriage.getHospitalPreference().get(hos_list.get(newHos)).indexOf(curr_lowest_Res))){
                            if (hos_res_pref_Map.get(hos_list.get(hospital)).get(resCount) < hos_res_pref_Map.get(hos_list.get(newHos)).get(curr_lowest_Res)){
                                match.free[hospital][ind_res_list.get(res_list.get(resident))] = match.free[hospital][match.curr_length[hospital]-1];
                                match.free[hospital][match.curr_length[hospital]-1] = -1;
                                match.position[hospital]--;
                                match.curr_length[hospital]--;
                                match.length[hospital]++;
                                hos_list.add(hospital);

                                ind_res_list.set(res_list.get(resident), match.position[hos_list.get(newHos)]);
                                resHosPair[res_list.get(resident)] = hos_list.get(newHos);
                                res_taken[res_list.get(resident)] = true;
                                res_taken[curr_lowest_Res] = false;
                                res_list.add(curr_lowest_Res);
                            }
                        }
                    }
                }
                else if (match.length[hos_list.get(newHos)] > 1) {

                    match.free[hos_list.get(newHos)][match.position[hos_list.get(newHos)]] = res_list.get(resident);
                    ind_res_list.set(res_list.get(resident), match.position[hos_list.get(newHos)]);
                    match.position[hos_list.get(newHos)]++;
                    match.curr_length[hos_list.get(newHos)]++;
                    match.length[hos_list.get(newHos)]--;
                    freeCount--;
                    resHosPair[res_list.get(resident)] = hos_list.get(newHos);
                    res_taken[res_list.get(resident)] = true;
                    continue;
                } else if (match.length[hos_list.get(newHos)] == 1) {

                    ind_res_list.set(res_list.get(resident), match.position[hos_list.get(newHos)]);
                    match.free[hos_list.get(newHos)][match.position[hos_list.get(newHos)]] = res_list.get(resident);
                    match.position[hos_list.get(newHos)]++;
                    match.curr_length[hos_list.get(newHos)]++;
                    match.length[hos_list.get(newHos)]--;
                    resHosPair[res_list.get(resident)] = hos_list.get(newHos);
                    res_taken[res_list.get(resident)] = true;
                    freeCount--;
                    continue;
                }
            }
        }


        int found = 0;
        for (int i=0; i<resCount; i++){
            for (int j=0; j<marriage.getHospitalCount(); j++){
                List<Integer> list = Arrays.stream(match.free[j]).boxed().collect(Collectors.toList());
                if (list.contains(i)) {
                    found = 1;
                    marriage.getResidentMatching().set(i, j);
                }
            }
            if (found == 0){
                marriage.getResidentMatching().set(i, -1);
            }
            found = 0;
        }
        return marriage;
    }






//    public Matching stableMarriageGaleShapley_hospitaloptimal(Matching marriage) {
//
//        /* TODO implement this function */
//        boolean result = false;
//        int slots = marriage.totalHospitalSlots();
//        int freeCount = marriage.totalHospitalSlots();
//        int HosCount = marriage.getHospitalCount();
//        int resCount = marriage.getResidentCount();
//        boolean HosFree[] = new boolean[HosCount];
//        boolean res_taken[] = new boolean[resCount];
//        int resHosPair[] = new int[marriage.getResidentCount()];
//        int res_index_cont = 0;
//
//        Spots match = new Spots(marriage);
//        ArrayList<Integer> res_list = new ArrayList<Integer>();
//        for (int i=0; i< resCount;i++){
//            res_list.add(i);
//        }
//        ArrayList<Integer> empty = new ArrayList<Integer>();
//        for (int i=0; i<resCount; i++){
//            empty.add(-1);
//        }
//        marriage.setResidentMatching(empty);
//
//        for (int i=0; i < marriage.totalHospitalSlots(); i++){
//            resHosPair[i] = -1;
//        }
//        int xx = 0;
//        int newHos;
//        for (newHos = (HosCount-1); newHos >= 0; newHos--) {
//            for (int i = 0; i < res_list.size(); i++) {
//                int resident = marriage.getHospitalPreference().get(newHos).get(res_list.get(i));
//
//                if (res_taken[resident] == true)
//                    continue;
//
//                if (match.length[newHos] > 1) {
//                    match.free[newHos][match.position[newHos]] = res_list.get(resident);
//                    match.position[newHos]++;
//                    match.curr_length[newHos]++;
//                    match.length[newHos]--;
//                    freeCount--;
//                    res_taken[resident] = true;
//                    continue;
//                } else if (match.length[newHos] == 1) {
//                    match.free[newHos][match.position[newHos]] = res_list.get(resident);
//                    match.position[newHos]++;
//                    match.curr_length[newHos]++;
//                    match.length[newHos]--;
//                    res_taken[resident] = true;
//                    freeCount--;
//                    continue;
//
//
//                } else {
//                    int lowest_pref = -1;
//                    int patient_num = -1;
//                    int currRes = -1;
//                    for (int patients = 0; patients < match.curr_length[newHos]; patients++) {
//                        currRes = match.free[newHos][patients];
//                        if (currRes == -1)
//                            continue;
//
//                        if (residentDivorce(marriage, newHos, res_list.get(resident), currRes) == false) {
//                            if (lowest_pref == -1){
//                                lowest_pref = currRes;
//                                patient_num = patients;
//                            }
//                            else if (marriage.getHospitalPreference().get(newHos).indexOf(lowest_pref) < marriage.getHospitalPreference().get(newHos).indexOf(currRes)) {
//                                lowest_pref = currRes;
//                                patient_num = patients;
//                            }
//                        }
//                    }
//                    if (lowest_pref != -1) {
//                        res_taken[resident] = true;
//                        res_taken[currRes] = false;
//                        match.free[newHos][patient_num] = res_list.get(resident);
//                        res_list.add(lowest_pref);
//                        continue;
//                    }
//                }
//            }
//        }
//
//
//        int found = 0;
//        for (int i=0; i<resCount; i++){
//            for (int j=0; j<marriage.getHospitalCount(); j++){
//                List<Integer> list = Arrays.stream(match.free[j]).boxed().collect(Collectors.toList());
//                if (list.contains(i)) {
//                    found = 1;
//                    marriage.getResidentMatching().set(i, j);
//                }
//            }
//            if (found == 0){
//                marriage.getResidentMatching().set(i, -1);
//            }
//            found = 0;
//        }
//        return marriage;
//    }
}
