package gotham.asset.mgmt.GAM16;

public class Solution1 extends Solution {

    @Override
    public String exceptionMaze() {
        String result = "";
        try {
            result += "A";
            try {
                result += "B";
                if (true) throw new RuntimeException();
                result += "C";
            } catch (NullPointerException e) {
                result += "D";
            } finally {
                result += "E";
            }
            result += "F";
        } catch (RuntimeException e) {
            result += "G";
        } finally {
            result += "H";
        }
        return result;
    }
}

// STEP  |  result
// 1     |  A
// 2     |  AB
// 3     |  RuntimeException -> finally
// 4     |  ABE
// 5     |  ABEGH
// 6     |

