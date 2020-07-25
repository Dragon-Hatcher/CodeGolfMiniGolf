public class PointRotation {
	public int x;
	public int y;
	public MinigolfCodeGolf.Direction direction;
	
	public PointRotation(int x, int y, MinigolfCodeGolf.Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int hashCode() {
		return x * 4 + y * 400 + direction.ordinal();
	}
	
	public boolean equals(Object obj) {
		PointRotation other = (PointRotation) obj;
		return other.x == x && other.y == y && other.direction == direction;
	}
}
