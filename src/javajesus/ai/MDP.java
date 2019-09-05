package javajesus.ai;

/**
 * @author Derek
 * Create a Markov Decision Process to compute optimal policy pi* and assign
 * actions to every tile (state) to create smart combat
 */
public class MDP {
	
	private double[][] up, right, down, left;
	
	// rewards for being in each tile type
	public static final int[] REWARDS = {0, 0, 0, -1, 1};
	
	// weight of long term investment vs short term investment
	// 0 = all short term
	// 1 = all long term
	public static final double GAMMA = 0.9;
	
	public MDP(double[][] up, double[][] right, double[][] down, double[][] left) {
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
	}
	
	/**
	 * State Values Functions are the long term discounted returns
	 * for being in a state of type tile
	 * @return
	 */
	private double[] computeStateValueFunctions(int[] states) {
		double[] values = new double[states.length];
		
		// up right down left
		int[] actions = {1, 2, 3, 4};
		
		int iterations = 0;
		
		while (iterations++ < 30) {
			double[] temp = new double[states.length];
			
			for (int s = 0; s < values.length; s++) {
				
				double max = 0;
				for (int action: actions) {
					double sum = computeSum(s, action, values);
					if (sum > max) {
						max = sum;
					}
				}
				
				temp[s] = REWARDS[states[s]] + GAMMA * max;
			}
			
			for (int i = 0; i < temp.length; i++) {
				values[i] = temp[i];
			}
		}
		
		return values;
	}
	
	private double computeSum(int state, int action, double[] current) {
		
		double sum = 0;
		for (int s = 0; s < current.length; s++) {
			
			double[][] transition = up;
			if (action == 2) {
				transition = right;
			} else if (action == 3) {
				transition = down;
			} else if (action == 4) {
				transition = left;
			}
			
			sum += transition[state][s] * current[s];
		}
		
		return sum;
	}
	
	public int[] computeGreedyPolicy(int[] states) {
		
		double[][] actionValues = new double[states.length][4];
		
		double[] V = computeStateValueFunctions(states);
		
		for (int s = 0; s < actionValues.length; s++) {
			for (int a = 1; a <= 4; a++) {
				actionValues[s][a-1] = REWARDS[states[s]] + GAMMA * computeSum(s, a, V);
			}
		}
		
		int[] values = new int[states.length];
		
		for (int i = 0; i < values.length; i++) {
			double max = 0;
			int action = 0;
			for (int a = 1; a <= 4; a++) {
				if (actionValues[i][a-1] > max) {
					max = actionValues[i][a-1];
					action = a;
				}
			}
			
			values[i] = action;
		}
		
		return values;
	}

}
