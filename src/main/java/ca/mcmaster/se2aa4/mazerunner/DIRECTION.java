package ca.mcmaster.se2aa4.mazerunner;

public enum DIRECTION {
        NORTH(new int[]{0,-1}),
        EAST(new int[]{1,0}),
        SOUTH(new int[]{0,1}),
        WEST(new int[]{-1,0});
        private final int[] value;
        DIRECTION(int[] pair){
            this.value = pair;
        }
        public int[] getValue() {
            return value;
        }
}
