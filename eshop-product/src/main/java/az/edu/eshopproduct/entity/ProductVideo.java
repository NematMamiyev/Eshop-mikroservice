package az.edu.eshopproduct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Table(name = "product_video")
@Setter
@Getter
@DynamicInsert
@NoArgsConstructor
public class ProductVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] data;
    @Column(nullable = false, length = 200)
    private String fileName;
    private String fileType;
    @ManyToOne
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;
    public ProductVideo(String fileName,String fileType,byte[] data,ProductDetails productDetails){
        this.fileName=fileName;
        this.fileType=fileType;
        this.data=data;
        this.productDetails=productDetails;
    }
}
