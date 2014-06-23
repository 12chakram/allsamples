package biz.neustar.dece.portal.utils;

public class QuestionAndAnswer {
	private String question;
	private String answer;
	private Integer serialNo;

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}

	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}