package br.ufc.mandacaru.aula.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.ufc.mandacaru.aula.service.FilesStorageService;
import br.ufc.mandacaru.aula.utils.FileInfo;
import br.ufc.mandacaru.aula.utils.ResponseMessage;

@RestController
@RequestMapping(path = "/api/upload")
public class FilesController {
	@Autowired
	FilesStorageService storageService;

	@PostMapping
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, String resource,
			String id) {
		String message = "";
		try {
			storageService.save(file, resource, id);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/{resource}/{id}")
	public ResponseEntity<List<FileInfo>> getListFiles(@PathVariable("resource") String resource,
			@PathVariable("id") String id) {
		List<FileInfo> fileInfos = storageService.loadAll(resource, id).map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "getFile", resource, id, path.getFileName().toString())
					.build().toString();
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{resource}/{id}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable("resource") String resource, @PathVariable("id") String id,
			@PathVariable String filename) {
		Resource file = storageService.load(resource, id, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
