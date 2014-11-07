package ar.edu.itba.poo.bytemii.display;

public class Resolution {
	private int width;
	private int height;

	public Resolution(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Resolution(String res) {
		if(!res.matches("^\\d+ (by|x) \\d+$"))
			throw new IllegalArgumentException();
		this.width = Integer.valueOf(res.substring(0, res.indexOf(' ')));
		this.height = Integer.valueOf(res.substring(res.lastIndexOf(' ') + 1));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return width + " by " + height;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(! (o instanceof Resolution)) {
			return false;
		}

		Resolution that = (Resolution) o;

		return height == that.height && width == that.width;

	}

	@Override
	public int hashCode() {
		int result = width;
		result = 31 * result + height;
		return result;
	}
}
