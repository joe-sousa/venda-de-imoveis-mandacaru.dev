package br.ufc.mandacaru.aula.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.repository.ReportSolicitationRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportSolicitationService {

	@Autowired
	private ReportSolicitationRepository reportSolicitationRepository;

	public String generateReport(int id) {
		try {

			List<Advertisement> advertisement = reportSolicitationRepository.getAdById(id);

			String reportPath = "/home/user/Área de Trabalho/TOOLKIT MANDACARU/aula_springboot/src/";

			// Compilação do Jasper report do .jrxml para .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "//ad.jrxml");

			//Pega a informação da lista de anúncio pelo id
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(advertisement);

			// Adiciona parâmetros
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("$F{advertisement_id}", "id");
			parameters.put("$F{title}.toUpperCase()", "title");
			parameters.put("$F{price}", "price");
			parameters.put("$F{address}", "address");
			parameters.put("$F{note}", "note");

			// Alimenta o anúncio
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			// Exporta o anúncio para um arquivo PDF 
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "anuncio.pdf");

			System.out.println("Done");

			return "Anúncio gerado em PDF com sucesso @path= " + reportPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro--> verifique o arquivo de log";
		}
	}
}