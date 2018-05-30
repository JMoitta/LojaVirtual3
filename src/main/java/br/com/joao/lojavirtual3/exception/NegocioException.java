package br.com.joao.lojavirtual3.exception;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class NegocioException extends Exception {

    private static final long serialVersionUID = -5618658079680419266L;
    private Severity severity;
    private String summary;
    private String detail;
    private List<NegocioException> negocioExceptions;

    public NegocioException(String detail) {
        super(detail);
        this.detail = detail;
    }

    public NegocioException(String summary, String detail) {
        super(detail);
        
        this.summary = summary;
        this.detail = detail;
    }
    
    public NegocioException(Severity severity, String summary, String detail) {
        super(detail);
        
        this.severity = severity;
        this.summary = summary;
        this.detail = detail;
    }

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public List<NegocioException> getNegocioExceptions() {
		if(this.negocioExceptions == null) {
			negocioExceptions = new ArrayList<>();
		}
		return negocioExceptions;
	}
	
	public void addNegocioException(NegocioException negocioException) {
		getNegocioExceptions().add(negocioException);
	}

	public void todasAsMensagens() {
		if(getSeverity() != null)
			FacesContext.getCurrentInstance()
				.addMessage(
					null,
					new FacesMessage(
						this.getSeverity(),
						this.getSummary(),
						this.getDetail()));
		
		for(NegocioException negocioException : this.getNegocioExceptions()) {
			FacesContext.getCurrentInstance()
				.addMessage(
					null,
					new FacesMessage(
						negocioException.getSeverity(),
						negocioException.getSummary(),
						negocioException.getDetail()));
		}
	}

    
}