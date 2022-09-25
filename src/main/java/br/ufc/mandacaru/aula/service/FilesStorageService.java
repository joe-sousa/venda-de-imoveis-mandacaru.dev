package br.ufc.mandacaru.aula.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void init();

	public void save(MultipartFile file, String resource, String id);

	public Resource load(String resource, String id, String filename);

	public void deleteAll();

	public Stream<Path> loadAll(String resource, String id);
}
