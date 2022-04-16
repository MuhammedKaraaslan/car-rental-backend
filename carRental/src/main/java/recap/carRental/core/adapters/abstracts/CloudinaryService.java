package recap.carRental.core.adapters.abstracts;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	public Map<?, ?> upload(MultipartFile multipartFile);

	public Map<?, ?> delete(String id);

	public File convert(MultipartFile multipartFile);
}
