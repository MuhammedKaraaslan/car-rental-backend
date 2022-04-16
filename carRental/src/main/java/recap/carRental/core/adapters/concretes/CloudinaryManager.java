package recap.carRental.core.adapters.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import recap.carRental.core.adapters.abstracts.CloudinaryService;

@Service
public class CloudinaryManager implements CloudinaryService{

	Cloudinary cloudinary;
	
	private Map<String, String> accountValues = new HashMap<>();
	
	public CloudinaryManager() {
		accountValues.put("cloud_name", "dw1ll9kcw");
		accountValues.put("api_key", "182511626935711");
		accountValues.put("api_secret", "NpfZ9k5ohbhSb4ABSYL0smPFqAQ");
		cloudinary = new Cloudinary(accountValues);
	}
	
	@Override
	public Map<?, ?> upload(MultipartFile multipartFile) {
		File file = convert(multipartFile);
		Map<?, ?> result = null;
		try {
			result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		file.delete();
		return result;
	}

	@Override
	public Map<?, ?> delete(String id) {
		Map<?, ?> result = null;
		try {
			result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public File convert(MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			try {
				fileOutputStream.write(multipartFile.getBytes());
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}

}
