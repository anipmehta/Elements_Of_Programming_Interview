package airbnb_host_max_accomodation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test
    public void testMaxNights() {
        Solution solution = new Solution();
        for(int i=0; i<Solution.input.length; i++) {
            Assertions.assertEquals(Solution.output[i], solution.maxNights(Solution.input[i]));
        }
    }
}
