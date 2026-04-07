package gotham.asset.mgmt.GAM16;

public abstract class Solution {

    /**
     * GAM16 - Exception Flow Labyrinth
     *
     * <p>What does this method return?</p>
     * <pre>
     *   public String exceptionMaze() {
     *       String result = "";
     *       try {
     *           result += "A";
     *           try {
     *               result += "B";
     *               if (true) throw new RuntimeException();
     *               result += "C";
     *           } catch (NullPointerException e) {
     *               result += "D";
     *           } finally {
     *               result += "E";
     *           }
     *           result += "F";
     *       } catch (RuntimeException e) {
     *           result += "G";
     *       } finally {
     *           result += "H";
     *       }
     *       return result;
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same string.</p>
     */
    public abstract String exceptionMaze();
}
