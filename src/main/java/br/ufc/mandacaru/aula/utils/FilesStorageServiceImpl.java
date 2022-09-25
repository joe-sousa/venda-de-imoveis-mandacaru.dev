package br.ufc.mandacaru.aula.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.mandacaru.aula.service.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private final Path root = Paths.get("uploads");
	Path resourcePath;

	@Override
	public void init() {
		try {
			File f = root.toFile();
			if (!f.exists()) {
				Files.createDirectory(root);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(MultipartFile file, String resource, String id) {
		try {
			resourcePath = Paths.get("uploads/" + resource + "/" + id);
			File f = resourcePath.toFile();
			if (!f.exists()) {
				Files.createDirectories(resourcePath);
			}

			Files.copy(file.getInputStream(), this.resourcePath.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Resource load(String resourcea, String id, String filename) {
		try {
			resourcePath = Paths.get("uploads/" + resourcea + "/" + id);
			Path file = resourcePath.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll(String resource, String id) {
		try {
			resourcePath = Paths.get("uploads/" + resource + "/" + id);
			return Files.walk(this.resourcePath, 1).filter(path -> !path.equals(this.resourcePath)).map(this.resourcePath::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}
