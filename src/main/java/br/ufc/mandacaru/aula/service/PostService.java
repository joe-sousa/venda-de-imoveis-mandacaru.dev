package br.ufc.mandacaru.aula.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import br.ufc.mandacaru.aula.model.Post;
import br.ufc.mandacaru.aula.utils.PropertiesGeneral;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class PostService {

	private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
	private static final String BASE_TOKEN = "https://h-auth.portaldedocumentos.com.br/auth/realms/assinador/protocol/openid-connect/token";
	private static final String BASE_PDSIGN = "https://esign-api-pprd.portaldedocumentos.com.br";

	private Gson gson;

	@Autowired
	PropertiesGeneral properties;

	public PostService() {
		gson = new Gson();
	}

	public Post findPostByRequest(int id) {
		OkHttpClient client = new OkHttpClient.Builder().build();
		Request request = new Request.Builder().url(BASE_URL + "/" + id).build();
		Call call = client.newCall(request);

		try {
			Response response = call.execute();

			if (response.code() == 200) {
				Post post = gson.fromJson(response.body().string(), Post.class);
				return post;
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Post> findAllPostsByRequest() {
		OkHttpClient client = new OkHttpClient.Builder().build();

		Request request = new Request.Builder().url(BASE_URL).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return gson.fromJson(response.body().string(), List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Post saveByRequest(Post post) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		String postJson = gson.toJson(post);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(postJson, mediaType);

		Request request = new Request.Builder().url(BASE_URL).post(body).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return gson.fromJson(response.body().string(), Post.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Post updateByRequest(int id, Post post) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		String postJson = gson.toJson(post);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(postJson, mediaType);

		Request request = new Request.Builder().url(BASE_URL + "/" + id).put(body).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return gson.fromJson(response.body().string(), Post.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteByRequest(int id) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		Request request = new Request.Builder().url(BASE_URL + "/" + id).delete().build();

		Call call = client.newCall(request);

		try {
			call.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getToken() {
		OkHttpClient client = new OkHttpClient.Builder().build();
		RequestBody formBody = new FormBody.Builder().add("username", "integracao.ufc@pd.tec.br")
				.add("password", "s9GYMru3#6").add("client_id", "assinador-app").add("grant_type", "password").build();

		Request request = new Request.Builder().url(BASE_TOKEN).post(formBody).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonTree = mapper.readTree(response.body().string());
			properties.setToken(jsonTree.get("access_token").asText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String createProcess() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode process = mapper.createObjectNode();
		process.put("title", "Teste apresentação");
		process.put("requester", mapper.createObjectNode().put("id", "44afea47-2bfa-4380-9dae-e1e2ebe7a64d"));
		process.put("company", mapper.createObjectNode().put("id", "036e8bc8-f964-4969-92c4-d255d258d941"));
		process.put("flow", mapper.createObjectNode().put("defineOrderOfInvolves", true).put("hasExpiration", true)
				.put("expiration", "2022-12-30"));

		ArrayNode array = mapper.createArrayNode();

		// Member1
		ObjectNode member1 = mapper.createObjectNode();

		member1.put("name", "Joel Sousa").put("email", "joe_sousa@ufc.br").put("documentType", "CPF")
				.put("documentCode", "123.432.620-50").put("order", 1).put("type", "SUBSCRIBER")
				.put("representation", mapper.createObjectNode().put("willActAsPhysicalPerson", true)
						.put("willActRepresentingAnyCompany", false));

		ObjectNode actionType = mapper.createObjectNode();
		actionType.put("id", "510b226e-c705-4120-ad9d-4a19633ea3df");

		ObjectNode responsibility = mapper.createObjectNode();
		responsibility.put("id", "50a625b5-213a-4743-ae92-f3732d87f159");

		ObjectNode authenticationType = mapper.createObjectNode();
		authenticationType.put("id", "841c8833-8566-4a9a-be5b-b30839ed138d");

		member1.put("actionType", actionType);
		member1.put("responsibility", responsibility);
		member1.put("authenticationType", authenticationType);

		// Member2
		ObjectNode member2 = mapper.createObjectNode();

		member2.put("name", "Joel de Sousa").put("email", "dioelsousa@gmail.com")
				.put("documentType", "CPF").put("documentCode", "567.832.620-50").put("order", 2)
				.put("type", "SUBSCRIBER").put("representation", mapper.createObjectNode()
						.put("willActAsPhysicalPerson", true).put("willActRepresentingAnyCompany", false));

		member2.put("actionType", actionType);
		member2.put("responsibility", responsibility);
		member2.put("authenticationType", authenticationType);

		array.add(member1);
		array.add(member2);

		process.put("members", array);

		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(process.toPrettyString(), mediaType);

		Request request = new Request.Builder().url(BASE_PDSIGN + "/processes")
				.addHeader("Authorization", "Bearer " + properties.getToken()).post(body).build();

		OkHttpClient client = new OkHttpClient.Builder().build();
		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			JsonNode jsonTree = mapper.readTree(response.body().string());
			properties.setIdProcess(jsonTree.get("id").asText());
			System.out.println(properties.getIdProcess());
			return properties.getIdProcess();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void createFile() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode file = mapper.createObjectNode();
		file.put("extension", "PDF");
		file.put("isPendency", false);
		file.put("name", "meu-arquivo");
		file.put("order", 1);
		file.put("type", "SIGN");

		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(file.toPrettyString(), mediaType);

		Request request = new Request.Builder()
				.url(BASE_PDSIGN + "/processes/" + properties.getIdProcess() + "/documents")
				.addHeader("Authorization", "Bearer " + properties.getToken()).post(body).build();

		OkHttpClient client = new OkHttpClient.Builder().build();
		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			JsonNode jsonTree = mapper.readTree(response.body().string());
			properties.setIdFile(jsonTree.get("id").asText());
			System.out.println(properties.getIdFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void uploadFile(MultipartFile file) {
		RequestBody fileBody;
		try {
			fileBody = RequestBody.create(okhttp3.MediaType.parse(file.getContentType()), file.getBytes());

			MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("file", file.getOriginalFilename(), fileBody) // file param
					.build();

			Request request = new Request.Builder()
					.url(BASE_PDSIGN + "/processes/" + properties.getIdProcess() + "/documents/ "
							+ properties.getIdFile() + "/upload")
					.addHeader("Authorization", "Bearer " + properties.getToken()).post(multipartBody).build();

			OkHttpClient client = new OkHttpClient.Builder().build();
			Call call = client.newCall(request);

			ObjectMapper mapper = new ObjectMapper();
			Response response = call.execute();
			JsonNode jsonTree = mapper.readTree(response.body().string());
			System.out.println(jsonTree.get("id").asText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String checkReady(String process) {
		try {
			Request request = new Request.Builder().url(BASE_PDSIGN + "/processes/" + process)
					.addHeader("Authorization", "Bearer " + properties.getToken()).build();

			OkHttpClient client = new OkHttpClient.Builder().build();
			Call call = client.newCall(request);

			ObjectMapper mapper = new ObjectMapper();
			Response response = call.execute();
			JsonNode jsonTree = mapper.readTree(response.body().string());
			return jsonTree.get("status").asText();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void patchProcess() {
		try {
			while (true) {
				Thread.sleep(5000);

				ObjectMapper mapper = new ObjectMapper();
				ObjectNode object = mapper.createObjectNode();
				object.put("status", "RUNNING");

				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(object.toPrettyString(), mediaType);

				Request request = new Request.Builder().url(BASE_PDSIGN + "/processes/" + properties.getIdProcess())
						.patch(body).addHeader("Authorization", "Bearer " + properties.getToken()).build();

				OkHttpClient client = new OkHttpClient.Builder().build();
				Call call = client.newCall(request);

				Response response = call.execute();
				JsonNode jsonTree = mapper.readTree(response.body().string());

				if (jsonTree.get("status") != null) {
					System.out.println(jsonTree.get("status").asText());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}