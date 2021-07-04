package bean;

public class Course {
	private int Cno;
	private String Cname;
	private String Cterm;
	private double Credit;
	private int LearnTime;
	private String CheckChoice;

	public int getCno() {
		return Cno;
	}

	public void setCno(int cno) {
		Cno = cno;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getCterm() {
		return Cterm;
	}

	public void setCterm(String cterm) {
		Cterm = cterm;
	}

	public double getCredit() {
		return Credit;
	}

	public void setCredit(double credit) {
		Credit = credit;
	}

	public int getLearnTime() {
		return LearnTime;
	}

	public void setLearnTime(int learnTime) {
		LearnTime = learnTime;
	}

	public String getCheckChoice() {
		return CheckChoice;
	}

	public void setCheckChoice(String checkChoice) {
		CheckChoice = checkChoice;
	}
}
