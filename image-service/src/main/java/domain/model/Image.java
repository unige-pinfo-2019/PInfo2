package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

import lombok.Data;

@Entity
@Table(name="IMAGES")
@Data
public class Image implements Serializable {

	private static final long serialVersionUID = -7607287692132931725L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IMAGE_ID")
	private Long id;
	
	@Column(name="IMAGE_BIN")
	@NotNull
	@Lob
	@FormParam("file")
	private byte[] data;
	
	@Column(name="IMAGE_SIZE")
	@NotNull
	@FormParam("size")
	private Long size;

}
