package com.webSecurity.webSecurityProject.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	public ResponseEntity<?> uploadFile(MultipartFile file){
		String originName=( file).getOriginalFilename();
  	  String fileExtention = originName.substring(originName.lastIndexOf(".")+1);
  	  
  	  if ((fileExtention.equalsIgnoreCase("jpg")||fileExtention.equalsIgnoreCase("jpeg")||
  			  fileExtention.equalsIgnoreCase("png"))&&
  			  ((file).getSize()<10000000)&&
  			  (( file).getContentType().equals("image/jpeg")||( file).getContentType().equals("image/jpg")||
  					  ( file).getContentType().equals("image/png"))) {
  		  
  		  
  		  try {
  			  
  			    try (InputStream is = file.getInputStream()) {
  			        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(is)) {
  			            Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
  			            if (iterator == null || !iterator.hasNext()) {
  			                //throw new RuntimeException("Image file format not supported by ImageIO: " );
  			                return ResponseEntity.internalServerError().body("Image file format not supported :(");
  			            }


  			            //We are just looking for the first reader compatible:
  			            ImageReader reader = iterator.next();
  			            reader.setInput(imageInputStream);

  			            int numPage = reader.getNumImages(true);

  			            //it uses to put new image files
  			            String name = file.getOriginalFilename().replace(".", "_"); 
  			            String Path_Directory=new ClassPathResource("static/images/").getFile().getAbsolutePath();
  			            IntStream.range(0, numPage).forEach(v -> {
  			                try {
  			                    final BufferedImage tiff = reader.read(v);
  			                    ImageIO.write(tiff, fileExtention.toLowerCase(), new File(Path_Directory +File.separator+ name + v + "."+fileExtention));
  			                } catch (IOException e) {
  			                    e.printStackTrace();
  			                }
  			            });
  			        }
  			    }
				
		          return ResponseEntity.ok().body("image successfully uploaded :)");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		          return ResponseEntity.internalServerError().body("upload failed :(");
			}
		} else {
	          return ResponseEntity.badRequest().body("we accept only jpg, jpeg and png. Sorry can't upload :( ");
		}
	}
	
}
