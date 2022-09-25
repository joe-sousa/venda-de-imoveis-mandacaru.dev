package br.ufc.mandacaru.aula.utils;

import org.springframework.stereotype.Component;

@Component
public class PropertiesGeneral {

	private String token;
	private String idProcess;
	private String idFile;

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public String getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(String idProcess) {
		this.idProcess = idProcess;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}