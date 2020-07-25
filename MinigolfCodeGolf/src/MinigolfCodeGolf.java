import java.util.HashSet;

public class MinigolfCodeGolf {

	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
		
		Direction opp() {
			switch(this) {
			case NORTH:
				return SOUTH;
			case EAST:
				return WEST;
			case SOUTH:
				return NORTH;
			case WEST:
				return EAST;
			}
			return this;
		}

		Direction clock() {
			switch(this) {
			case NORTH:
				return EAST;
			case EAST:
				return SOUTH;
			case SOUTH:
				return WEST;
			case WEST:
				return NORTH;
			}
			return this;
		}

		Direction cclock() {
			switch(this) {
			case NORTH:
				return WEST;
			case EAST:
				return NORTH;
			case SOUTH:
				return EAST;
			case WEST:
				return SOUTH;
			}
			return this;
		}
		
	}
	
	static String course =
	"   -----+ " + "\n" +
	"  /   X | " + "\n" +
	" | +----+ " + "\n" +
	" | |      " + "\n" +
	" |O|      " + "\n" +
	" +-+      " + "\n" +	
	"          " + "\n";
	
	static int power = 11;
	
	public static void main(String[] args) {
		if(timeToHole(course, Direction.NORTH) == power || 
				timeToHole(course, Direction.EAST) == power || 
				timeToHole(course, Direction.SOUTH) == power || 
				timeToHole(course, Direction.WEST) == power) {
			System.out.println(true);
		} else {
			System.out.println(false);			
		}
	}
	
	private static int timeToHole(String courseString, Direction startDirection) {
		String[] rows = courseString.split("\n");
		
		char[][] course = new char[rows.length][];
		for(int i = 0; i < rows.length; i++) {
			course[i] = rows[i].toCharArray();
		}
		
		PointRotation ball = new PointRotation(0, 0, startDirection);
		int moveCount = 0;
		HashSet<PointRotation> visitedLocations = new HashSet<PointRotation>();
		
		for(int i = 0; i < course.length; i++) {
			for(int j = 0; j < course[i].length; j++) {
				if(course[i][j] == 'X') {
					ball.x = i;
					ball.y = j;
				}
			}
		}
		
		while(course[ball.x][ball.y] != 'O') {
			moveCount++;
			
			switch(ball.direction) {
			case NORTH:
				ball.y--;
				break;
			case EAST:
				ball.x++;
				break;
			case SOUTH:
				ball.y++;
				break;
			case WEST:
				ball.x--;
				break;
			}
			
			switch(course[ball.x][ball.y]) {
			case '-':
			case '|':
			case '+':
				ball.direction = ball.direction.opp();
				break;
			case '/':
				if(ball.direction == Direction.NORTH || ball.direction == Direction.SOUTH) {
					ball.direction = ball.direction.clock();					
				} else {
					ball.direction = ball.direction.cclock();
				}
				break;
			case '\\':
				if(ball.direction == Direction.NORTH || ball.direction == Direction.SOUTH) {
					ball.direction = ball.direction.cclock();					
				} else {
					ball.direction = ball.direction.clock();
				}
				break;
			}
			
			PointRotation currentPR = new PointRotation(ball.x, ball.y, ball.direction);
			if(visitedLocations.contains(currentPR)) {
				return -1;
			} else {
				visitedLocations.add(currentPR);
			}
		}
		
		return moveCount;
	}

}