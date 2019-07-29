
class PhilipsData {
	String category;
	String className;
	String line;

	public void setCategory(String category) {
		this.category = category;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCategory() {
		return category;
	}

	public String getClassName() {
		return className;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}

class ToolOne extends PhilipsData {
	String type;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}

class ToolTwo extends PhilipsData {
	String rule;
	String content;

	public void setrule(String category) {
		this.rule = category;
	}

	public void setContent(String className) {
		this.content = className;
	}

	public String getrule() {
		return rule;
	}

	public String getContent() {
		return content;
	}
}
