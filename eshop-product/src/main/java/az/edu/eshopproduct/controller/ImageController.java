package az.edu.eshopproduct.controller;

import az.edu.eshopproduct.dto.response.RespStatus;
import az.edu.eshopproduct.service.ImageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @PostMapping("/{productDetailsId}")
    public RespStatus addImages(@RequestParam(value = "files") List<MultipartFile> files, @PathVariable @NotNull(message = "Id is required") Long productDetailsId) throws IOException {
        return imageService.addImages(files,productDetailsId);
    }

    @ResponseStatus(HttpStatus.OK)
   // @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @DeleteMapping("/list/{productDetailsId}")
    public RespStatus deleteImagesByProductDetailsId(@PathVariable @NotNull(message = "Id is required") Long productDetailsId){
        return imageService.deleteImagesByProductDetailsId(productDetailsId);
    }

    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')or hasAuthority('OPERATOR')")
    @DeleteMapping("/{imageId}")
    public RespStatus deleteImage(@PathVariable @NotNull(message = "Id is required") Long imageId){
        return imageService.deleteImage(imageId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/{productDetailsId}")
    public List<ResponseEntity<byte[]>> getImages(@PathVariable @NotNull(message = "Id is required") Long productDetailsId){
        return imageService.getImages(productDetailsId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable @NotNull(message = "Id is required") Long imageId){
        return imageService.getImage(imageId);
    }
}
